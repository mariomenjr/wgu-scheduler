package Scheduler.Controllers.Modals;

import Scheduler.Controllers.AppointmentFormController;
import Scheduler.Repository.BaseModal;

public class AppointmentFormModal extends BaseModal<AppointmentFormController> {
    @Override
    public String getViewPath() { return "../../Views/AppointmentForm.fxml"; };

}
