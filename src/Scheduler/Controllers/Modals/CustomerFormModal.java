package Scheduler.Controllers.Modals;

        import Scheduler.Controllers.AppointmentFormController;
        import Scheduler.Repository.BaseModal;

public class CustomerFormModal extends BaseModal<AppointmentFormController> {
    @Override
    public String getViewPath() { return "../../Views/CustomerForm.fxml"; };

}
