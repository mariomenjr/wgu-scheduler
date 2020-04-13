package Scheduler.Controllers.Modals;

import Scheduler.Controllers.LoginController;
import Scheduler.Repository.BaseModal;

public class LoginModal extends BaseModal<LoginController> {

    protected String getViewPath() {
        return "../../Views/Login.fxml";
    };
}
