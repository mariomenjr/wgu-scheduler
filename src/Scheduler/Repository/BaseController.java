package Scheduler.Repository;

import Scheduler.Dao.Database;
import javafx.fxml.FXML;

public abstract class BaseController {

    public String getControllerName() {
        return "Base";
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println(this.getControllerName().concat(" initialized."));
        this.applyLocale();
    }

    protected void applyLocale() {}
}
