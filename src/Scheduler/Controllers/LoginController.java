package Scheduler.Controllers;

import Scheduler.Controllers.Modals.HubModal;
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
    public Button btn_login;
    @FXML
    public Button btn_exit;

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    protected void applyLocale() {
        try {
            this.lbl_username.setText(Main.t("ui_login_lbl_username"));
            this.lbl_password.setText(Main.t("ui_login_lbl_password"));
            this.btn_login.setText(Main.t("ui_login_btn_login"));
            this.btn_exit.setText(Main.t("ui_login_btn_exit"));
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }
    }

    public void onEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) this.btn_login.fireEvent(new ActionEvent());
    }

    public void onButtonClick(ActionEvent actionEvent) {
        Button eventSource = (Button)actionEvent.getSource();
        if (this.btn_login.equals(eventSource)) {
            try {
                String where = "userName = '" + this.tf_username.getText() + "' AND password = '" + String.valueOf(this.tf_password.getText().hashCode()) + "'";
                ObservableList<User> users = new UserManager().select(where);

                // Login or not
                if (users.size() == 1)
                {
                    Main.log("Logged in!");

                    HubModal hubModal = new HubModal();
                    Stage hubWindow = hubModal.openScreen(Main.getMainStage());
                    hubWindow.setWidth(800);
                    hubWindow.setHeight(500);
                    hubWindow.centerOnScreen();

                    this.getStage().hide();
                } else
                    MessageBox.showWarning(Main.t("alert_logging_failed_header"), Main.t("alert_logging_failed_message"));
            } catch(Exception e) {
                Main.consoleStack(e);
            }
        } else {
            // if (this.btn_exit.equals(eventSource))
            Main.log("Goodbye!");
            System.exit(0);
        }
    }
}
