package Scheduler.Controllers;

import Scheduler.Controllers.Modals.AppointmentFormModal;
import Scheduler.Dao.AppointmentManager;
import Scheduler.Dao.UserManager;
import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Models.User;
import Scheduler.Repository.BaseController;
import Scheduler.Repository.ILogController;
import Scheduler.Utils.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AppointmentsLogController extends BaseController implements ILogController {


    private ObservableList<Appointment> observableList;
    private FilteredList<Appointment> filteredList;

    private Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private Integer currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    private Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);

    @FXML
    public Label lbl_current;
    @FXML
    public Button btn_next;
    @FXML
    public Button btn_prev;
    @FXML
    public ComboBox cb_period_selector;
    @FXML
    public Button btn_time_per_month;
    @FXML
    public Button btn_types_by_month;
    @FXML
    public Button btn_schedule_consultant;
    @FXML
    public TableView<Appointment> tv_appointments;
    @FXML
    public TextField tf_search;
    @FXML
    public Label lb_search;
    @FXML
    public Button btn_add;
    @FXML
    public Button btn_edit;
    @FXML
    public Button btn_remove;

    @Override
    public void initialize() {
        try {
            this.cb_period_selector.getItems().add(Main.t("ui_tab_appointment_cb_by_month"));
            this.cb_period_selector.getItems().add(Main.t("ui_tab_appointment_cb_by_week"));

            new AppointmentManager().select().forEach(appointment -> {
                long minutes = Duration.between(LocalDateTime.now(), appointment.getStart().toZonedDateTime().toLocalDateTime()).toMinutes();
                if (minutes > 0 && minutes < 15)
                    MessageBox.showInformation("`".concat(appointment.getTitle().concat("` will start within ").concat(Long.toString(minutes)).concat(" minute(s)")), "@ ".concat(appointment.getStart().toString()));
            });
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onComboBoxChange(ActionEvent actionEvent)
    {
        try {
            System.out.println(this.currentYear);
            this.btn_next.setDisable(false);
            this.btn_prev.setDisable(false);

            this.fillData();
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onNextOrPrevClick(ActionEvent actionEvent)
    {
        Button b = (Button) actionEvent.getSource();
        try {
            this.btn_prev.setDisable(true);
            if (b.getText().equals(">")) {
                if (this.cb_period_selector.getSelectionModel().getSelectedIndex() == 0)
                    this.currentMonth = this.currentMonth + 1;
                else
                    this.currentWeek = this.currentWeek + 1;
            } else if (b.getText().equals("<")) {
                if (this.cb_period_selector.getSelectionModel().getSelectedIndex() == 0)
                    this.currentMonth = this.currentMonth - 1;
                else
                    this.currentWeek = this.currentWeek - 1;
            }

            if (this.currentMonth == 12)
            {
                this.currentMonth = 0;
                this.currentYear = this.currentYear + 1;
            } else if (this.currentMonth == -1)
            {
                this.currentMonth = 11;
                this.currentYear = this.currentYear - 1;
            }

            if (this.currentWeek == 53)
            {
                this.currentWeek = 1;
                this.currentYear = this.currentYear + 1;
            } else if (this.currentMonth == 0)
            {
                this.currentMonth = 1;
                this.currentYear = this.currentYear - 1;
            }

            this.fillData();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
        this.btn_prev.setDisable(false);
    }

    public void onBtnTypesByMonthClick(ActionEvent actionEvent)
    {
        try{
            Map<Integer, Map<String, Integer>> dict = new HashMap<Integer, Map<String, Integer>>();
            ObservableList<Appointment> ap = new AppointmentManager().select();

            for (Appointment appointment : ap) {
                Integer month = appointment.getStart().getTime().getMonth()+1;

                if (!dict.containsKey(month)) dict.put(month, new HashMap<String, Integer>());
                if (!dict.get(month).containsKey(appointment.getType())) dict.get(month).put(appointment.getType(), 0);

                dict.get(month).put(appointment.getType(), dict.get(month).get(appointment.getType())+1);
            }

            FileWriter fw = new FileWriter("typesByMonth.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Integer integer : dict.keySet()) {
                try {
                    pw.println(Month.values()[integer]);
                    for (String s : dict.get(integer).keySet()) {
                        pw.println("    ".concat(s));
                    }
                } catch (Exception e) {
                    Main.consoleStack(e);
                }
            }
            pw.close();

            MessageBox.showInformation("Report ".concat("typesByMonth.txt completed!"),"");
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onBtnScheduleByConsultant(ActionEvent actionEvent)
    {
        try{
            Map<Integer, ObservableList<Appointment>> dict = new HashMap<Integer, ObservableList<Appointment>>();
            ObservableList<Appointment> ap = new AppointmentManager().select();

            for (Appointment appointment : ap) {
                Integer userId = appointment.getUserId();

                if (!dict.containsKey(userId)) dict.put(userId, FXCollections.observableArrayList());
                dict.get(userId).add(appointment);
            }

            FileWriter fw = new FileWriter("scheduleByConsultant.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Integer integer : dict.keySet()) {
                try {
                    ObservableList<User> u = new UserManager().select("userId = ".concat(integer.toString()));
                    if (u.size() == 1) {
                        pw.println(u.get(0).getUserName());
                        dict.get(integer).forEach(appointment -> pw.println("    ".concat(appointment.getStart().toString()).concat(" to ").concat(appointment.getEnd().toString())));
                    }
                } catch (Exception e) {
                    Main.consoleStack(e);
                }
            }
            pw.close();

            MessageBox.showInformation("Report ".concat("scheduleByConsultant.txt completed!"),"");
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onBtnTimePerMonth(ActionEvent actionEvent)
    {
        try{
            // long minutes = Duration.between(LocalDateTime.now(), appointment.getStart().toZonedDateTime().toLocalDateTime()).toMinutes();
            Map<Integer, Long> dict = new HashMap<Integer, Long>();
            ObservableList<Appointment> ap = new AppointmentManager().select();

            for (Appointment appointment : ap) {
                Integer month = appointment.getStart().getTime().getMonth()+1;

                if (!dict.containsKey(month)) dict.put(month, new Long(0));

                Long hours = Duration.between(appointment.getStart().toZonedDateTime().toLocalDateTime(), appointment.getEnd().toZonedDateTime().toLocalDateTime()).toHours();
                dict.put(month, dict.get(month) + hours);
            }

            FileWriter fw = new FileWriter("timePerMonthInHours.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Integer integer : dict.keySet()) {
                try {
                    pw.println(Month.values()[integer].toString().concat(": ").concat(dict.get(integer).toString()));
                } catch (Exception e) {
                    Main.consoleStack(e);
                }
            }
            pw.close();

            MessageBox.showInformation("Report ".concat("timePerMonthInHours.txt completed!"),"");
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    protected void applyLocale() {
        try {
            this.btn_types_by_month.setText(Main.t("ui_tab_appointment_btn_report1"));
            this.btn_schedule_consultant.setText(Main.t("ui_tab_appointment_btn_report2"));
            this.btn_time_per_month.setText(Main.t("ui_tab_appointment_btn_report3"));
            this.lb_search.setText(Main.t("ui_tab_appointment_lbl_search"));
            this.btn_add.setText(Main.t("ui_tab_appointment_btn_add"));
            this.btn_edit.setText(Main.t("ui_tab_appointment_btn_edit"));
            this.btn_remove.setText(Main.t("ui_tab_appointment_btn_remove"));
        } catch(Exception e){
            Main.consoleStack(e);
        }
    }

    @Override
    public void fillData() {
        try {
            if (this.cb_period_selector.getSelectionModel().getSelectedItem() == null)
                this.observableList = new AppointmentManager().select();
            else if ((this.cb_period_selector.getSelectionModel().getSelectedIndex() == 0)) {
                this.lbl_current.setText(Integer.toString(this.currentMonth + 1));
                this.observableList = new AppointmentManager().select("YEAR(start) = ".concat(this.currentYear.toString()) + " AND MONTH(start) = ".concat(Integer.toString(this.currentMonth + 1)));
            } else if ((this.cb_period_selector.getSelectionModel().getSelectedIndex() == 1)) {
                this.lbl_current.setText(Integer.toString(this.currentWeek - 1));
                this.observableList = new AppointmentManager().select("YEAR(start) = ".concat(this.currentYear.toString()) + " AND WEEK(start) = ".concat(Integer.toString(this.currentWeek - 1)));
            }

            this.filteredList = new FilteredList<Appointment>(observableList, p -> true);

            this.tv_appointments.getColumns().forEach(column -> column.setCellValueFactory(new PropertyValueFactory<>(column.getId())));
            this.tv_appointments.setItems(this.filteredList);

            this.tf_search.setText("");

        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onTypeSearch(KeyEvent keyEvent) {
        try {
            TextField tf = (TextField) keyEvent.getSource();

            this.filteredList.setPredicate(row -> (tf.getText() == null || tf.getText().isEmpty()) || row.getTitle().toLowerCase().contains(tf.getText().toLowerCase()));
            this.tv_appointments.refresh();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onAddClick(ActionEvent actionEvent) {
        try {
            AppointmentFormModal apm = new AppointmentFormModal();
            Stage appointmentFormWindow = apm.openScreen(this.getStage());

            appointmentFormWindow.setWidth(400);
            appointmentFormWindow.setHeight(600);
            appointmentFormWindow.centerOnScreen();

            apm.getController().setOnClose(n -> {
                this.fillData();
                return null;
            });
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onEditClick(ActionEvent actionEvent) {
        try {
            int i = this.tv_appointments.getSelectionModel().getSelectedIndex();
            if (i < 0)
                MessageBox.showWarning("Unable to edit", "No Row has been selected");
            else {
                AppointmentFormModal apm = new AppointmentFormModal();
                Stage appointmentFormWindow = apm.openScreen(this.getStage());

                apm.getController().setRecord(this.observableList.get(i));

                appointmentFormWindow.setWidth(400);
                appointmentFormWindow.setHeight(600);
                appointmentFormWindow.centerOnScreen();

                apm.getController().setOnClose(n -> {
                    this.fillData();
                    return null;
                });
            }
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onRemoveClick(ActionEvent actionEvent) {
        try {
            int i = this.tv_appointments.getSelectionModel().getSelectedIndex();
            if (i < 0)
                MessageBox.showWarning("Unable to remove", "No Row has been selected");
            else {
                Appointment ap = this.observableList.get(i);
                MessageBox.askConfirmation(
                    "Do you want to remove \"".concat(ap.getTitle()).concat("\"?"),
                    "It's scheduled on ".concat(ap.getStart().getTime().toString()),
                    o -> {
                        try {
                            new AppointmentManager().delete(ap);
                            return this.observableList.remove(i);
                        } catch (Exception e) {
                            Main.consoleStack(e);
                        }
                        return null;
                    }
                );
            }
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }
}
