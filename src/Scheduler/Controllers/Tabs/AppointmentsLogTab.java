package Scheduler.Controllers.Tabs;

import Scheduler.Controllers.AppointmentsLogController;
import Scheduler.Repository.BaseTab;

public class AppointmentsLogTab extends BaseTab<AppointmentsLogController> {
    public String getViewPath() {
        return "../../Views/AppointmentsLog.fxml";
    }

    @Override
    public void refresh() throws Exception {
        this.getController().fillData();
    }
}
