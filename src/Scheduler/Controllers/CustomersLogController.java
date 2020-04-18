package Scheduler.Controllers;

import Scheduler.Dao.CustomerManager;
import Scheduler.Main;
import Scheduler.Models.Customer;
import Scheduler.Repository.BaseController;
import Scheduler.Repository.ILog;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class CustomersLogController extends BaseController implements ILog {

    private FilteredList<Customer> filteredList;

    @FXML
    public TableView<Customer> tv_customers;

    @Override
    protected void applyLocale() {}

    public void fillData() throws Exception {
        this.filteredList = new FilteredList<Customer>(new CustomerManager().select(), p -> true);

        this.tv_customers.getColumns().forEach(column -> column.setCellValueFactory(new PropertyValueFactory<>(column.getId())));
        this.tv_customers.setItems(this.filteredList);
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
    public void initialize() {
        super.initialize();
        try {
            this.fillData();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }
}
