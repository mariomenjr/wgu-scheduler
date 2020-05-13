package Scheduler.Controllers;

import Scheduler.Controllers.Modals.ListPickerModal;
import Scheduler.Dao.AppointmentManager;
import Scheduler.Dao.CustomerManager;
import Scheduler.Dao.UserManager;
import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Models.Customer;
import Scheduler.Models.User;
import Scheduler.Repository.FormController;
import Scheduler.Utils.DateTime;
import Scheduler.Utils.MessageBox;
import Scheduler.Utils.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AppointmentFormController extends FormController  {


    private Appointment appointment;
    private Customer selectedCustomer;
    private User selectedUser;
    private boolean isNew = true;
    private Callback onCloseCustom = (n) -> { return null; };

    @FXML
    public Label lb_header;
    @FXML
    public Label lb_customer;
    @FXML
    public Label lb_user;
    @FXML
    public Label lb_title;
    @FXML
    public Label lb_description;
    @FXML
    public Label lb_location;
    @FXML
    public Label lb_contact;
    @FXML
    public Label lb_type;
    @FXML
    public Label lb_url;
    @FXML
    public Label lb_start;
    @FXML
    public Label lb_end;
    @FXML
    public Button btn_save;
    @FXML
    public Button btn_cancel;
    @FXML
    public Button btn_choose_customer;
    @FXML
    public Label lb_selected_customer;
    @FXML
    public Button btn_choose_user;
    @FXML
    public Label lb_selected_user;
    @FXML
    public TextField tf_title;
    @FXML
    public TextField tf_description;
    @FXML
    public TextField tf_location;
    @FXML
    public TextField tf_contact;
    @FXML
    public TextField tf_type;
    @FXML
    public TextField tf_url;
    @FXML
    public DatePicker dp_start;
    @FXML
    public ComboBox tp_start;
    @FXML
    public DatePicker dp_end;
    @FXML
    public ComboBox tp_end;

    ObservableList<String> times = FXCollections.observableArrayList();

    @Override
    public void initialize() {
        super.initialize();
        this.appointment = new Appointment(0, 1, 1, "", "", "", "", "", "",
                new GregorianCalendar(), new GregorianCalendar(), new GregorianCalendar(), "", new GregorianCalendar(), "");
        this.fillTimes();
        this.lb_header.setText("New Appointment");
    }

    @Override
    protected void applyLocale() {
        try {
            this.lb_header.setText(Main.t("ui_appointment_form_lb_header_new"));
            this.lb_customer.setText(Main.t("ui_appointment_form_lb_customer"));
            this.lb_user.setText(Main.t("ui_appointment_form_lb_user"));
            this.lb_title.setText(Main.t("ui_appointment_form_lb_title"));
            this.lb_description.setText(Main.t("ui_appointment_form_lb_description"));
            this.lb_location.setText(Main.t("ui_appointment_form_lb_location"));
            this.lb_contact.setText(Main.t("ui_appointment_form_lb_contact"));
            this.lb_type.setText(Main.t("ui_appointment_form_lb_type"));
            this.lb_url.setText(Main.t("ui_appointment_form_lb_url"));
            this.lb_start.setText(Main.t("ui_appointment_form_lb_start"));
            this.lb_end.setText(Main.t("ui_appointment_form_lb_end"));
            this.lb_selected_customer.setText(Main.t("ui_appointment_form_lb_selected_customer"));
            this.lb_selected_user.setText(Main.t("ui_appointment_form_lb_selected_user"));

            this.btn_choose_customer.setText(Main.t("ui_list_picker_btn_choose"));
            this.btn_choose_user.setText(Main.t("ui_list_picker_btn_choose"));

            this.btn_save.setText(Main.t("ui_appointment_form_btn_save"));
            this.btn_cancel.setText(Main.t("ui_appointment_form_btn_cancel"));
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    protected void listenWriting(TextField tf) {
        try {
            int getterIndex = -1, setterIndex = -1;

            Method[] methods = this.appointment.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().endsWith("get" + tf.getId())) getterIndex = i;
                if (methods[i].getName().endsWith("set" + tf.getId())) setterIndex = i;
            }

            methods[setterIndex].invoke(this.appointment, tf.getText());
            methods[getterIndex].invoke(this.appointment);
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    protected boolean validate() {
        boolean isInvalid = true;
        try {
            isInvalid = (this.selectedUser == null || this.selectedCustomer == null);
            if (isInvalid)
                throw new Exception(
                    "Invalid value for ".concat(this.selectedCustomer == null ? "Customer":"User")
                    .concat("|Make sure you are selecting a customer and user"));

            isInvalid = this.tf_title.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Title\""
                    .concat("|Make sure you are assigning a title"));

            isInvalid = (this.dp_start.getValue().isAfter( this.dp_end.getValue()));
            if (isInvalid)
                throw new Exception(
                    "Invalid value for Start and End date"
                            .concat("|Make sure you End time is greater than Start date")
                );

            isInvalid = (this.dp_start.getValue() == null || this.dp_end.getValue() == null);
            if (isInvalid)
                throw new Exception(
                    "Invalid value for ".concat(this.dp_start.getValue() == null ? "\"Starts on\"":"\"Ends on\"")
                    .concat("|Make sure you are selecting an start and end date"));

            isInvalid = this.tp_start.getValue() == null || this.tp_end.getValue() == null;
            if (isInvalid)
                throw new Exception(
                    "Invalid value for ".concat(this.tp_start.getValue() == null ? "\"Starts on\"":"\"Ends on\"")
                    .concat("|Make sure you are selecting an start and end time")
                );

            isInvalid = this.tp_start.getSelectionModel().getSelectedIndex() > this.tp_end.getSelectionModel().getSelectedIndex();
            if (isInvalid)
                throw new Exception(
                        "Invalid value for Start and End time"
                                .concat("|Make sure you End time is greater than Start time")
                );
        } catch (Exception e) {
            String[] messages = e.getMessage().split("\\|");
            MessageBox.showWarning(messages[0], messages[1]);

            Main.consoleStack(e);
        }
        return !isInvalid;
    }

    @Override
    protected boolean getIsNew() {
        return this.isNew;
    }

    public DateTime resolveDateAndTime(DatePicker pickedDate, String pickedTime) {
        LocalDate dateOn = pickedDate.getValue();
        LocalTime timeOn = LocalTime.now();

        String[] split;

        split = pickedTime.split(" ");
        boolean isMorning = split[1] == "AM";

        split = split[0].split(":");
        int hour = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);

        timeOn = timeOn.withHour(isMorning ? hour + 12:hour).withMinute(mins);

        Calendar calendar = Calendar.getInstance();
        calendar.set(dateOn.getYear(), dateOn.getMonthValue()-1, dateOn.getDayOfMonth(),
                timeOn.getHour(), timeOn.getMinute(), timeOn.getSecond());

        DateTime datetime = new DateTime();
        datetime.setTime(calendar.getTime());

        return datetime;
    }

    public void onBtnSaveClick(ActionEvent actionEvent) {
        try {
            if (!this.validate()) return;

            this.appointment.setCustomerId(this.selectedCustomer.getCustomerId());
            this.appointment.setUserId(this.selectedUser.getUserId());

            this.appointment.setStart(this.resolveDateAndTime(this.dp_start, (String) this.tp_start.getValue()));
            this.appointment.setEnd(this.resolveDateAndTime(this.dp_end, (String) this.tp_end.getValue()));

            if (this.getIsNew()) {
                AppointmentManager am = new AppointmentManager();
                if (
                    am.select(
                        "start <= '"
                                .concat(Parser.CalendarToString(this.appointment.getStart()))
                                .concat("' AND end >= '")
                                .concat(Parser.CalendarToString(this.appointment.getStart()))
                                .concat("' AND userId = ")
                                .concat(Integer.toString(this.appointment.getUserId()))
                    ).size() > 0
                ) {
                    MessageBox.showWarning("Overlapped appointment", "Please select another time");
                    return;
                } else {
                    am.insert(this.appointment);
                    MessageBox.showInformation("\"".concat(this.appointment.getTitle()).concat("\" has been created!"), "It'll take place on ".concat(this.appointment.getStart().getTime().toLocaleString()));
                }
            } else {
                new AppointmentManager().update(this.appointment);
                MessageBox.showInformation("\"".concat(this.appointment.getTitle()).concat("\" has been updated!"), "It'll take place on ".concat(this.appointment.getStart().getTime().toLocaleString()));
            }

            this.getStage().close();
            this.onCloseCustom.call(null);
        } catch (Exception e) {
            MessageBox.showInformation("\"".concat(this.appointment.getTitle()).concat("\" failed to be created!"), "Due to: ".concat(e.getMessage()));
            Main.consoleStack(e);
        }
    }

    public void onBtnChooseCustomerClick() {
        try {
            ListPickerModal lm = new ListPickerModal();
            Stage lmModal = lm.openScreen(this.getStage());

            lmModal.setWidth(500);
            lmModal.setHeight(300);
            lmModal.centerOnScreen();

            // Fill TableView with Customer list
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().fillData((Callback<TableView<Customer> , Object>) tableView -> {
                tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerName"));
                tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("createDate"));
                try {
                    tableView.setItems(new FilteredList<>(new CustomerManager().select()));
                } catch (Exception e) {
                    Main.consoleStack(e);
                }
                return null;
            });

            // Make Table View filterable
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().setOnSearchAction((Callback<Object[], Object>) controls -> {
                TableView<Customer> tableView = (TableView<Customer>) controls[0];
                TextField textField = (TextField) controls[1];

                textField.setOnKeyReleased(keyEvent -> {
                    FilteredList<Customer> fc = (FilteredList<Customer>) tableView.getItems();
                    fc.setPredicate(row -> (textField.getText() == null || textField.getText().isEmpty()) || row.getCustomerName().toLowerCase().contains(textField.getText().toLowerCase()));
                    tableView.setItems(fc);
                });
                return null;
            });

            // Choose a row
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().setOnChooseAction((Callback<Object[], Object>) controls -> {
                TableView<Customer> tableView = (TableView<Customer>) controls[0];
                Button button = (Button) controls[1];
                ListPickerController lpc = (ListPickerController) controls[2];

                button.setOnMouseClicked(mouseEvent -> {
                    int i = tableView.getSelectionModel().getSelectedIndex();
                    if (i < 0)
                        MessageBox.showWarning("Unable to choose", "No Row has been selected");
                    else {
                        this.selectedCustomer = tableView.getItems().get(i);
                        this.lb_selected_customer.setText(selectedCustomer.getCustomerName());

                        lpc.getStage().close();
                    }
                });
                return null;
            });
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onBtnChooseUserClick() {
        try {
            ListPickerModal lm = new ListPickerModal();
            Stage lmModal = lm.openScreen(this.getStage());

            lmModal.setWidth(500);
            lmModal.setHeight(300);
            lmModal.centerOnScreen();

            // Fill TableView with Customer list
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().fillData((Callback<TableView<User> , Object>) tableView -> {
                tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userName"));
                tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("createDate"));
                try {
                    tableView.setItems(new FilteredList<>(new UserManager().select()));
                } catch (Exception e) {
                    Main.consoleStack(e);
                }
                return null;
            });

            // Make Table View filterable
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().setOnSearchAction((Callback<Object[], Object>) controls -> {
                TableView<User> tableView = (TableView<User>) controls[0];
                TextField textField = (TextField) controls[1];

                textField.setOnKeyReleased(keyEvent -> {
                    FilteredList<User> fc = (FilteredList<User>) tableView.getItems();
                    fc.setPredicate(row -> (textField.getText() == null || textField.getText().isEmpty()) || row.getUserName().toLowerCase().contains(textField.getText().toLowerCase()));
                    tableView.setItems(fc);
                });
                return null;
            });

            // Choose a row
            // This lambda expression give us the ability to re-use the List Picker
            lm.getController().setOnChooseAction((Callback<Object[], Object>) controls -> {
                TableView<User> tableView = (TableView<User>) controls[0];
                Button button = (Button) controls[1];
                ListPickerController lpc = (ListPickerController) controls[2];

                button.setOnMouseClicked(mouseEvent -> {
                    int i = tableView.getSelectionModel().getSelectedIndex();
                    if (i < 0)
                        MessageBox.showWarning("Unable to choose", "No Row has been selected");
                    else {
                        this.selectedUser = tableView.getItems().get(i);
                        this.lb_selected_user.setText(selectedUser.getUserName());

                        lpc.getStage().close();
                    }
                });
                return null;
            });
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    protected void setOnClose(Callback callback) {
        this.onCloseCustom = callback;
    }

    @Override
    protected void populateForm() {
        this.tf_title.setText(this.appointment.getTitle());
        this.tf_description.setText(this.appointment.getDescription());
        this.tf_contact.setText(this.appointment.getContact());
        this.tf_location.setText(this.appointment.getLocation());
        this.tf_type.setText(this.appointment.getType());
        this.tf_url.setText(this.appointment.getUrl());

        try {
            // These two assignment can be improved
            ObservableList<User> user = new UserManager().select("userId = ".concat(Integer.toString(this.appointment.getUserId())));
            this.selectedUser = user.get(0);
            this.lb_selected_user.setText(this.selectedUser.getUserName());

            ObservableList<Customer> customer = new CustomerManager().select("customerId = ".concat(Integer.toString(this.appointment.getCustomerId())));
            this.selectedCustomer = customer.get(0);
            this.lb_selected_customer.setText(this.selectedCustomer.getCustomerName());
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }

        DateTime sdt = this.appointment.getStart();
        DateTime edt = this.appointment.getEnd();

        LocalDateTime sldt = LocalDateTime.ofInstant(sdt.toInstant(), sdt.getTimeZone().toZoneId());
        LocalDateTime eldt = LocalDateTime.ofInstant(edt.toInstant(), edt.getTimeZone().toZoneId());

        // System.out.println();
        this.dp_start.setValue(sldt.toLocalDate());
        this.dp_end.setValue(eldt.toLocalDate());

        for (int i = 0; i < this.tp_start.getItems().size(); i++) {
            if (this.tp_start.getItems().get(i).toString().equals(sldt.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"))))
                this.tp_start.setValue(this.tp_start.getItems().get(i));
        }

        for (int i = 0; i < this.tp_end.getItems().size(); i++) {
            if (this.tp_end.getItems().get(i).toString().equals(eldt.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"))))
                this.tp_end.setValue(this.tp_end.getItems().get(i));
        }
    }

    @Override
    public void setRecord(Object record) {
        this.appointment = (Appointment) record;
        this.populateForm();
        this.isNew = false;

        try {
            this.lb_header.setText(Main.t("ui_appointment_form_lb_header_update"));
            this.btn_save.setText(Main.t("ui_appointment_form_btn_update"));
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void fillTimes() {
        // Only show business hours
        for (int i = 9; i <= 16; i++) {
            boolean isMorning = i < 12;
            int hour = isMorning ? i:(i == 12 ? i:i - 12);

            times.add(Integer.toString(hour) + ":00 " + (isMorning ? "AM":"PM"));
            times.add(Integer.toString(hour) + ":30 " + (isMorning ? "AM":"PM"));
        }
        this.tp_start.getItems().addAll(times);
        this.tp_end.getItems().addAll(times);
    }
}
