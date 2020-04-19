package Scheduler.Controllers.Modals;

import Scheduler.Controllers.HubController;
import Scheduler.Controllers.ListPickerController;
import Scheduler.Repository.BaseModal;

public class ListPickerModal extends BaseModal<ListPickerController> {
    @Override
    public String getViewPath() { return "../../Views/ListPicker.fxml"; };
}
