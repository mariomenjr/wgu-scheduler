package Scheduler.Controllers.Tabs;

import Scheduler.Controllers.AppointmentsLogController;
import Scheduler.Repository.BaseComponent;

public class AppointmentsLogTab extends BaseComponent<AppointmentsLogController> {
    protected String getViewPath() {
        return "../../Views/AppointmentsLog.fxml";
    };
}
