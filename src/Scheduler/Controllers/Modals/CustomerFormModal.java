package Scheduler.Controllers.Modals;

import Scheduler.Controllers.CustomerFormController;
import Scheduler.Repository.BaseModal;

public class CustomerFormModal extends BaseModal<CustomerFormController> {
    @Override
    public String getViewPath() { return "../../Views/CustomerForm.fxml"; };

}
