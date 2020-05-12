package Scheduler.Controllers;

import Scheduler.Controllers.Modals.AppointmentFormModal;
import Scheduler.Dao.AppointmentManager;
import Scheduler.Dao.Database;
import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Repository.BaseController;
import Scheduler.Repository.ILogController;
import Scheduler.Utils.MessageBox;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AppointmentsLogController extends BaseController implements ILogController {

    private ObservableList<Appointment> observableList;
    private FilteredList<Appointment> filteredList;

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
    protected void applyLocale() {
        try {
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
            this.observableList = new AppointmentManager().select();
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
