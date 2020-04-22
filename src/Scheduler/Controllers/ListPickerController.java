package Scheduler.Controllers;

import Scheduler.Main;
import Scheduler.Repository.BaseController;
import Scheduler.Repository.IPickerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ListPickerController extends BaseController implements IPickerController {
    
    @FXML public TextField tf_search;
    @FXML public TableView<Object> tv_picker;
    @FXML public Button btn_choose;

    @Override protected void applyLocale() {}

    @Override public void fillData(Callback callback) throws Exception {
        try {
            callback.call(this.tv_picker);
        } catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void setOnSearchAction(Callback callback) throws Exception {
        try {
            Object[] controls = {this.tv_picker, this.tf_search};
            callback.call(controls);
        }catch (Exception e) {
            Main.consoleStack(e);
        }
    }

    public void setOnChooseAction(Callback callback) throws Exception {
        try {
            Object[] controls = {this.tv_picker, this.btn_choose, this};
            callback.call(controls);
        }catch (Exception e) {
            Main.consoleStack(e);
        }
    }
}
