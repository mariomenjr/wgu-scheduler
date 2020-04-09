package Scheduler.Repository;

import Scheduler.Dao.Database;
import javafx.fxml.FXML;

public class BaseController {

    protected String getControllerName() {
        return "Base";
    }

    @FXML
    public void initialize() throws Exception {
        System.out.println(this.getControllerName().concat(" initialized."));

        Database.connect();
        Database.disconnect();
    }
}
