package Scheduler.Controllers;

import Scheduler.Main;
import Scheduler.Repository.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends BaseController  {

    @FXML
    public Label lbl_username;
    @FXML
    public TextField tf_username;
    @FXML
    public Label lbl_password;
    @FXML
    public TextField tf_password;
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
        super.applyLocale();

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

    @Override
    public String getControllerName() {
        return "Login";
    }
}
