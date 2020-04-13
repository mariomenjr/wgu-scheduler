package Scheduler.Controllers;

import Scheduler.Controllers.Tabs.AppointmentsLogTab;
import Scheduler.Controllers.Tabs.CustomersLogTab;
import Scheduler.Repository.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class HubController extends BaseController {

    @FXML
    public Tab tab_appointments;
    @FXML
    public Tab tab_customers;

    @Override
    protected void applyLocale() {}

    @Override
    public void initialize() throws Exception {
        super.initialize();
        
        // TODO: Make this to be executed by loading or tab-selection behavior
        AppointmentsLogTab appointmentsTab = new AppointmentsLogTab();
        appointmentsTab.renderComponentIn(this.tab_appointments);

        // TODO: Make this to be executed by loading or tab-selection behavior
        CustomersLogTab customersTab = new CustomersLogTab();
        customersTab.renderComponentIn(this.tab_customers);
    }
}
