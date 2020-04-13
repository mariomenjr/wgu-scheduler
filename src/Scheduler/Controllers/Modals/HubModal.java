package Scheduler.Controllers.Modals;

import Scheduler.Controllers.HubController;
import Scheduler.Repository.BaseModal;

public class HubModal extends BaseModal<HubController> {

    protected String getViewPath() {
        return "../../Views/Hub.fxml";
    };
}
