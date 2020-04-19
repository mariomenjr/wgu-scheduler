package Scheduler.Controllers.Tabs;

import Scheduler.Controllers.CustomersLogController;
import Scheduler.Repository.BaseTab;

public class CustomersLogTab extends BaseTab<CustomersLogController> {
    public String getViewPath() {
        return "../../Views/CustomersLog.fxml";
    }

    @Override
    public void refresh() throws Exception {
       this.getController().fillData();
    }
}
