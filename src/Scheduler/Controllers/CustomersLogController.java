package Scheduler.Controllers;

import Scheduler.Dao.CustomerManager;
import Scheduler.Dao.Database;
import Scheduler.Main;
import Scheduler.Models.Customer;
import Scheduler.Repository.BaseController;
import javafx.collections.ObservableList;

public class CustomersLogController extends BaseController {
    @Override
    protected void applyLocale() {}

    @Override
    public void initialize() {
        super.initialize();

        try {
            CustomerManager cm = new CustomerManager();
            ObservableList<Customer> rs = cm.select();
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }
}
