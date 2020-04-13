package Scheduler.Controllers;

import Scheduler.Controllers.Modals.HubModal;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController extends BaseController {

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

    public void onEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) this.btn_login.fireEvent(new ActionEvent());
    }

    public void onButtonClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, Exception {
        Button eventSource = (Button)actionEvent.getSource();
        if (this.btn_login.equals(eventSource)) {

            UserManager um = new UserManager();
            ObservableList<User> users = um.select();

            // Login or not
            if (users.filtered(user -> user.getUserName().equals(this.tf_username.getText()) && user.getPassword().equals(String.valueOf(this.tf_password.getText().hashCode()))).size() == 1)
            {
                Main.log("Logged in!");

                HubModal hubModal = new HubModal();
                Stage hubWindow = hubModal.openScreen();
                hubWindow.setWidth(800);
                hubWindow.setHeight(500);
                hubWindow.centerOnScreen();

                this.getStage().hide();
            } else
                MessageBox.showWarning(Main.t("alert_logging_failed_header"), Main.t("alert_logging_failed_message"));
        } else {
            // if (this.btn_exit.equals(eventSource))
            Main.log("Goodbye!");
            System.exit(0);
        }
    }
}
