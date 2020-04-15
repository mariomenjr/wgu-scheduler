package Scheduler.Controllers;

import Scheduler.Controllers.Tabs.AppointmentsLogTab;
import Scheduler.Controllers.Tabs.CustomersLogTab;
import Scheduler.Main;
import Scheduler.Repository.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class HubController extends BaseController {

    @FXML
    public TabPane tab_hub_pane;
    @FXML
    public Tab tab_appointments;
    @FXML
    public Tab tab_customers;

    private CustomersLogTab _customersLogTab;
    private AppointmentsLogTab _appointmentsLogTab;

    @Override
    protected void applyLocale() {}

    @Override
    public void initialize() {
        super.initialize();


        try {
            // Edge case: first tab needs to be inserted manually
            set_appointmentsLogTab(new AppointmentsLogTab());
            get_appointmentsLogTab().renderAt(tab_appointments);

            this.tab_hub_pane.getSelectionModel().selectedItemProperty().addListener(
                    (ov, t, t1) -> {
                        try {
                            boolean isTabEmpty = t1.getContent() == null;
                            if (t1.equals(this.tab_appointments)) {
                                if (isTabEmpty) {
                                    this.set_appointmentsLogTab(new AppointmentsLogTab());
                                    this.get_appointmentsLogTab().renderAt(this.tab_appointments);
                                } else
                                    this.get_appointmentsLogTab().refresh();
                            }
                            else if (t1.equals(this.tab_customers)) {
                                if (isTabEmpty) {
                                    this.set_customersLogTab(new CustomersLogTab());
                                    this.get_customersLogTab().renderAt(this.tab_customers);
                                } else
                                    this.get_customersLogTab().refresh();
                            }
                        } catch (Exception e) {
                            Main.consoleStack(e);
                        }
                    }
            );
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public CustomersLogTab get_customersLogTab() {
        return _customersLogTab;
    }

    public void set_customersLogTab(CustomersLogTab _customersLogTab) {
        this._customersLogTab = _customersLogTab;
    }

    public AppointmentsLogTab get_appointmentsLogTab() {
        return _appointmentsLogTab;
    }

    public void set_appointmentsLogTab(AppointmentsLogTab _appointmentsLogTab) {
        this._appointmentsLogTab = _appointmentsLogTab;
    }
}
