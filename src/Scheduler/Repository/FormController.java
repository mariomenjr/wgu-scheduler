package Scheduler.Repository;

import Scheduler.Main;
import Scheduler.Models.Appointment;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public abstract class FormController extends BaseController {

    abstract protected void setOnClose(Callback callback);
    abstract protected void populateForm();
    abstract protected void listenWriting(TextField tf);
    abstract protected boolean validate();
    abstract protected boolean getIsNew();

    abstract public void setRecord(Object record);

    public void onTextFieldWrite(KeyEvent keyEvent) {
        try {
            TextField tf = (TextField) keyEvent.getSource();
            this.listenWriting(tf);
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }

    public void onBtnCancel(ActionEvent actionEvent) {
        this.getStage().close();
    }

}
