package Scheduler.Controllers;

import Scheduler.Controllers.Components.AppointmentsLogComponent;
import Scheduler.Repository.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class HubController extends BaseController {

    @FXML
    public Tab tab_appointments;
    @FXML
    public AnchorPane tab_pane_appointments;

    @Override
    protected void applyLocale() {}

    @Override
    public void initialize() throws Exception {
        super.initialize();

        System.out.println("Meh");

        AppointmentsLogComponent comp = new AppointmentsLogComponent();
        comp.renderComponentIn(this.tab_appointments);
    }
}
