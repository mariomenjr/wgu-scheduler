package Scheduler.Controllers.Tabs;

import Scheduler.Controllers.CustomersLogController;
import Scheduler.Repository.BaseComponent;

public class CustomersLogTab extends BaseComponent<CustomersLogController> {
    protected String getViewPath() {
        return "../../Views/CustomersLog.fxml";
    };
}
