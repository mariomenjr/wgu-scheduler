package Scheduler.Repository;

import Scheduler.Dao.Database;
import javafx.fxml.FXML;

import java.sql.ResultSet;

public abstract class BaseController {

    abstract protected void applyLocale();

    protected String getControllerName() {
        String[] split = this.getClass().getName().split("\\.");
        return split[split.length - 1].replaceAll("Controller", "");
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println(this.getControllerName().concat(" initialized."));
        this.applyLocale();
    }
}
