package Scheduler.Controllers;

import Scheduler.Controllers.Tabs.AppointmentsLogTab;
import Scheduler.Controllers.Tabs.CustomersLogTab;
import Scheduler.Main;
import Scheduler.Repository.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
    protected void applyLocale() {
        try {
            this.tab_appointments.setText(Main.t("ui_tab_appointment_title"));
            this.tab_customers.setText(Main.t("ui_tab_customer_title"));
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        try {
            // Edge case: first tab needs to be inserted manually
            setAppointmentsLogTab(new AppointmentsLogTab());
            getAppointmentsLogTab().renderAt(tab_appointments);
            getAppointmentsLogTab().refresh();

            this.tab_hub_pane.getSelectionModel().selectedItemProperty().addListener(
                    (ov, t, t1) -> {
                        try {
                            boolean isTabEmpty = t1.getContent() == null;
                            if (t1.equals(this.tab_appointments)) {
                                if (isTabEmpty) {
                                    this.setAppointmentsLogTab(new AppointmentsLogTab());
                                    this.getAppointmentsLogTab().renderAt(this.tab_appointments);
                                } else
                                    this.getAppointmentsLogTab().refresh();
                            }
                            else if (t1.equals(this.tab_customers)) {
                                if (isTabEmpty) {
                                    this.setCustomersLogTab(new CustomersLogTab());
                                    this.getCustomersLogTab().renderAt(this.tab_customers);
                                } else
                                    this.getCustomersLogTab().refresh();
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

    public CustomersLogTab getCustomersLogTab() {
        return this._customersLogTab;
    }

    public void setCustomersLogTab(CustomersLogTab _customersLogTab) {
        this._customersLogTab = _customersLogTab;
    }

    public AppointmentsLogTab getAppointmentsLogTab() {
        return this._appointmentsLogTab;
    }

    public void setAppointmentsLogTab(AppointmentsLogTab _appointmentsLogTab) {
        this._appointmentsLogTab = _appointmentsLogTab;
    }
}
