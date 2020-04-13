package Scheduler.Repository;

import Scheduler.Dao.Database;
import Scheduler.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.ResultSet;

public abstract class BaseController {

    abstract protected void applyLocale();

    private Stage _stage;

    protected String getControllerName() {
        String[] split = this.getClass().getName().split("\\.");
        return split[split.length - 1].replaceAll("Controller", "");
    }

    @FXML
    public void initialize() throws Exception {
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
