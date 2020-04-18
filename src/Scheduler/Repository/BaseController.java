package Scheduler.Repository;

import Scheduler.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public abstract class BaseController {

    abstract protected void applyLocale();

    private Stage _stage;

    protected String getControllerName() {
        String[] split = this.getClass().getName().split("\\.");
        return split[split.length - 1].replaceAll("Controller", "");
    }

    @FXML
    public void initialize() {
        Main.log(this.getControllerName().concat(" initialized."));
        this.applyLocale();
    }

    public void setStage(Stage stage) {
        this._stage = stage;
    }

    public Stage getStage() {
        return this._stage;
    }
}
