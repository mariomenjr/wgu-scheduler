package Scheduler.Controllers;

import Scheduler.Dao.AppointmentManager;
import Scheduler.Dao.CustomerManager;
import Scheduler.Main;
import Scheduler.Models.Appointment;
import Scheduler.Models.Customer;
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

public class CustomersLogController extends BaseController implements ILogController {

    private ObservableList<Customer> observableList;
    private FilteredList<Customer> filteredList;

    @FXML
    public TableView<Customer> tv_customers;
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
            this.btn_add.setText(Main.t("ui_tab_customer_btn_add"));
            this.btn_edit.setText(Main.t("ui_tab_customer_btn_edit"));
            this.btn_remove.setText(Main.t("ui_tab_customer_btn_remove"));
        } catch(Exception e){
            Main.consoleStack(e);
        }
    }

    @Override
    public void fillData() {
        try {
            this.observableList = new CustomerManager().select();
            this.filteredList = new FilteredList<Customer>(observableList, p -> true);

            this.tv_customers.getColumns().forEach(column -> column.setCellValueFactory(new PropertyValueFactory<>(column.getId())));
            this.tv_customers.setItems(this.filteredList);

            this.tf_search.setText("");
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onTypeSearch(KeyEvent keyEvent) {
        try {
            TextField tf = (TextField) keyEvent.getSource();

            this.filteredList.setPredicate(row -> (tf.getText() == null || tf.getText().isEmpty()) || row.getCustomerName().toLowerCase().contains(tf.getText().toLowerCase()));
            this.tv_customers.refresh();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onAddClick(ActionEvent actionEvent) {}

    @Override
    public void onEditClick(ActionEvent actionEvent) {}

    @Override
    public void initialize() {
        super.initialize();
        try {
            this.fillData();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void onRemoveClick(ActionEvent actionEvent) {
        try {
            int i = this.tv_customers.getSelectionModel().getSelectedIndex();
            if (i < 0)
                MessageBox.showWarning("Unable to remove", "No Row has been selected");
//            else
//                this.observableList.remove(i);
            else {
                Customer cu = this.observableList.get(i);
                MessageBox.askConfirmation(
                    "Do you want to remove \"".concat(cu.getCustomerName()).concat("\"?"),
                    "It's been customer since ".concat(cu.getCreateDate().getTime().toString()),
                    o -> {
                        try {
                            new CustomerManager().delete(cu);
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
