package Scheduler.Controllers;

import Scheduler.Dao.*;
import Scheduler.Main;
import Scheduler.Models.*;
import Scheduler.Repository.FormController;
import Scheduler.Utils.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CustomerFormController extends FormController {

    private Customer customer;
    private Address address;
    private City city;
    private Country country;
    private boolean isNew = true;
    private Callback onCloseCustom = (n) -> { return null; };

    @FXML public Label lb_header;
    @FXML public Label lb_name;
    @FXML public Label lb_address;
    @FXML public Label lb_unit;
    @FXML public Label lb_city;
    @FXML public Label lb_postal;
    @FXML public Label lb_phone;
    @FXML public Label lb_country;

    @FXML public TextField tf_name;
    @FXML public TextField tf_address;
    @FXML public TextField tf_unit;
    @FXML public TextField tf_city;
    @FXML public TextField tf_postal;
    @FXML public TextField tf_phone;
    @FXML public TextField tf_country;

    @FXML public Button btn_save;
    @FXML public Button btn_cancel;

    @Override protected void applyLocale() {
        try {
            /*
            ui_customer_form_lb_header_update=Update Customer
            ui_customer_form_btn_update=Update
             */
            this.lb_header.setText(Main.t("ui_customer_form_lb_header_new"));
            this.lb_name.setText(Main.t("ui_customer_form_lb_name"));
            this.lb_address.setText(Main.t("ui_customer_form_lb_address"));
            this.lb_unit.setText(Main.t("ui_customer_form_lb_unit"));
            this.lb_city.setText(Main.t("ui_customer_form_lb_city"));
            this.lb_postal.setText(Main.t("ui_customer_form_lb_postal"));
            this.lb_phone.setText(Main.t("ui_customer_form_lb_phone"));
            this.lb_country.setText(Main.t("ufalsei_customer_form_lb_country"));

            this.btn_save.setText(Main.t("ui_customer_form_btn_save"));
            this.btn_cancel.setText(Main.t("ui_customer_form_btn_cancel"));
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize() {
        super.initialize();
        this.customer = new Customer(0, "", 0, true, Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.address = new Address(0, "", "", 0, "", "", Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.city = new City(0, "", 0, Calendar.getInstance(), "", Calendar.getInstance(), "");
        this.country = new Country(0, "", Calendar.getInstance(), "", Calendar.getInstance(), "");

        this.lb_header.setText("New Customer");
    }

    @Override protected void listenWriting(TextField tf) {
        try {
            switch (tf.getId()) {
                case "Name":
                    this.customer.setCustomerName(tf.getText());
                    break;

                case "Address": // address2
                    this.address.setAddress(tf.getText());
                    break;

                case "Unit": // address2
                    this.address.setAddress2(tf.getText());
                    break;

                case "Postal": // postalCode
                    this.address.setPostalCode(tf.getText());
                    break;

                case "Phone": // phone
                    this.address.setPhone(tf.getText());
                    break;

                case "City":
                    this.city.setCity(tf.getText());
                    break;

                case "Country":
                    this.country.setCountry(tf.getText());
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override protected boolean validate() {
        boolean isInvalid = true;
        try {
            isInvalid = this.tf_name.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Customer name\""
                    .concat("|Make sure you are assigning a customer name"));

            isInvalid = this.tf_address.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Address\""
                    .concat("|Make sure you are assigning an address"));

            isInvalid = this.tf_city.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"City\""
                    .concat("|Make sure you are assigning a city"));

            isInvalid = this.tf_postal.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Postal code\""
                    .concat("|Make sure you are assigning a postal code"));

            isInvalid = this.tf_phone.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Phone\""
                    .concat("|Make sure you are assigning a phone"));

            isInvalid = this.tf_country.getText().isEmpty();
            if (isInvalid)
                throw new Exception(
                    "Invalid value for \"Country\""
                    .concat("|Make sure you are assigning a country"));
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

    @Override
    protected void setOnClose(Callback callback) {
        this.onCloseCustom = callback;
    }

    @Override protected void populateForm() {
        this.tf_name.setText(this.customer.getCustomerName());
        this.tf_address.setText(this.address.getAddress());
        this.tf_unit.setText(this.address.getAddress2());
        this.tf_postal.setText(this.address.getPostalCode());
        this.tf_phone.setText(this.address.getPhone());
        this.tf_city.setText(this.city.getCity());
        this.tf_country.setText(this.country.getCountry());
    }

    @Override public void setRecord(Object record) {
        this.customer = (Customer) record;
        this.isNew = false;

        try {
            this.address = new AddressManager().select("addressId = ".concat("'"+ this.customer.getAddressId() +"'")).get(0);
            this.city = new CityManager().select("cityId =".concat("'" + address.getCityId() + "'")).get(0);
            this.country = new CountryManager().select("countryId = ".concat("'" + city.getCountryId() + "'")).get(0);

            this.populateForm();

            this.lb_header.setText(Main.t("ui_customer_form_lb_header_update"));
            this.btn_save.setText(Main.t("ui_customer_form_btn_update"));
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onBtnSaveClick(ActionEvent actionEvent) {
        try {
            if (!this.validate()) return;

            if (this.getIsNew()) {
                Country country = new CountryManager().insert(this.country);

                this.city.setCountryId(country.getCountryId());
                City city = new CityManager().insert(this.city);

                this.address.setCityId(city.getCityId());
                Address address = new AddressManager().insert(this.address);

                this.customer.setAddressId(address.getAddressId());
                new CustomerManager().insert(this.customer);

                MessageBox.showInformation("\"".concat(this.customer.getCustomerName()).concat("\" has been created!"), "Created on ".concat(this.customer.getCreateDate().getTime().toLocaleString()));
            } else {
                new CountryManager().update(this.country);
                new CityManager().update(this.city);
                new AddressManager().update(this.address);
                new CustomerManager().update(this.customer);

                MessageBox.showInformation("\"".concat(this.customer.getCustomerName()).concat("\" has been updated!"), "Created on ".concat(this.customer.getCreateDate().getTime().toLocaleString()));
            }
            this.getStage().close();
            this.onCloseCustom.call(null);
        } catch (Exception e) {
            MessageBox.showInformation("\"".concat(this.customer.getCustomerName()).concat("\" failed to be created!"), "Due to: ".concat(e.getMessage()));
            Main.consoleStack(e);
        }
    }
}
