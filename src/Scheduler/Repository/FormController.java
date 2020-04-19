package Scheduler.Repository;

import Scheduler.Main;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public abstract class FormController extends BaseController{

    abstract protected void listenWriting(TextField tf);
    abstract protected boolean validate();

    public void onTextFieldWrite(KeyEvent keyEvent) {
        try {
            TextField tf = (TextField) keyEvent.getSource();
            this.listenWriting(tf);
        } catch(Exception e) {
            Main.consoleStack(e);
        }
    }
}
