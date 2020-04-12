package Scheduler.Controllers;

import Scheduler.Dao.Database;
import Scheduler.Dao.UserManager;
import Scheduler.Main;
import Scheduler.Models.User;
import Scheduler.Repository.BaseController;
import Scheduler.Utils.MessageBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class LoginController extends BaseController  {

    @FXML
    public Label lbl_username;
    @FXML
    public TextField tf_username;
    @FXML
    public Label lbl_password;
    @FXML
    public PasswordField tf_password;
    @FXML
    public Label lbl_location;
    @FXML
    public ComboBox dd_location;
    @FXML
    public Button btn_login;
    @FXML
    public Button btn_exit;

    @Override
    public void initialize() throws Exception {
        super.initialize();
    }

    @Override
    protected void applyLocale() {
        try {
            this.lbl_username.setText(Main.t("ui_login_lbl_username"));
            this.lbl_password.setText(Main.t("ui_login_lbl_password"));
            this.lbl_location.setText(Main.t("ui_login_lbl_location"));
            this.btn_login.setText(Main.t("ui_login_btn_login"));
            this.btn_exit.setText(Main.t("ui_login_btn_exit"));
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }
    }

    public void onButtonClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, Exception {
        Button eventSource = (Button)actionEvent.getSource();
        if (this.btn_login.equals(eventSource)) {

            if (Database.isConnected) Database.connect();

            UserManager um = new UserManager();
            ObservableList<User> users = um.select();

            // Login or not
            if (users.filtered(user -> user.getUserName().equals(this.tf_username.getText()) && user.getPassword().equals(String.valueOf(this.tf_password.getText().hashCode()))).size() == 1)
                System.out.println("Logged in!");
            else
                MessageBox.showWarning("Not Authorized", "Check your credentials!");
        } else {
            // if (this.btn_exit.equals(eventSource))
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }
}
