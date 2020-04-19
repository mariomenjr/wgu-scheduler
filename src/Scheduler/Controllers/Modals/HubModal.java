package Scheduler.Controllers.Modals;

import Scheduler.Controllers.HubController;
import Scheduler.Repository.BaseModal;

public class HubModal extends BaseModal<HubController> {
    @Override
    public String getViewPath() {
        return "../../Views/Hub.fxml";
    }

}
