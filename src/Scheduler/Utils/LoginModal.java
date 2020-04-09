package Scheduler.Utils;

import Scheduler.Controllers.LoginController;
import Scheduler.Repository.BaseModal;

public class LoginModal extends BaseModal<LoginController> {

    protected String getPathPrefix() {
        return "../";
    };
}
