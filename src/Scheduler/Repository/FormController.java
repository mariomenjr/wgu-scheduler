package Scheduler.Repository;

import Scheduler.Main;
import Scheduler.Models.Appointment;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public abstract class FormController extends BaseController {

    abstract protected void populateForm();
    abstract protected void listenWriting(TextField tf);
    abstract protected boolean validate();

    abstract public void setRecord(Object record);

    public void onTextFieldWrite(KeyEvent keyEvent) {
        try {
            TextField tf = (TextField) keyEvent.getSource();
            this.listenWriting(tf);
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }
}
