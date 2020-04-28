package Scheduler.Controllers;

import Scheduler.Main;
import Scheduler.Models.*;
import Scheduler.Repository.FormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CustomerFormController extends FormController {

    private Customer customer;
    private Address address;
    private City city;
    private Country country;

    @FXML public Label lb_name;
    @FXML public Label lb_address;
    @FXML public Label lb_unit;
    @FXML public Label lb_city;
    @FXML public Label lb_postal;
    @FXML public Label lb_phone;
    @FXML public Label lb_state;
    @FXML public Label lb_country;

    @FXML public TextField tf_name;
    @FXML public TextField tf_address;
    @FXML public TextField tf_unit;
    @FXML public TextField tf_city;
    @FXML public TextField tf_postal;
    @FXML public TextField tf_phone;
    @FXML public TextField tf_state;
    @FXML public TextField tf_country;

    @Override
    public void initialize() {
        super.initialize();
        this.customer = new Customer(0, "", 0, false, Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.address = new Address(0, "", "", 0, "", "", Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.city = new City(0, "", 0, Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.country = new Country(0, "", Calendar.getInstance(), "", Calendar.getInstance(), "");
    }

    @Override protected void populateForm() {}

    @Override protected void listenWriting(TextField tf) {
        try {
            switch (tf.getId()) {
                case "Name":
                    break;

                case "Address": // address2
                    break;

                case "Unit": // address2
                    break;

                case "Postal": // postalCode
                    break;

                case "Phone": // phone
                    break;

                case "City":
                    break;

                case "Country":
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override protected boolean validate() {
        return false;
    }

    @Override public void setRecord(Object record) {}

    @Override protected void applyLocale() {}

    public void onBtnSaveClick(ActionEvent actionEvent) {}
}
