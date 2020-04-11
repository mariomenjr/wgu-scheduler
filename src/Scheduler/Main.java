package Scheduler;

import Scheduler.Controllers.LoginController;
import Scheduler.Dao.Database;
import Scheduler.Utils.LoginModal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Main extends Application {

    private static Stage _mainStage;
    private static ResourceBundle _localeBundle;

    private static void _loadLocale() throws Exception {
        try {
            Main._localeBundle = ResourceBundle.getBundle("Scheduler/Locales/locale", Locale.GERMANY);
            System.out.println(Main.t("cli_locale_loaded"));
        } catch (Exception ex) {
            throw new Exception( "[" + Locale.getDefault() + "] locale not supported");
        }
    }

    private static void _loadLogin() throws Exception {
        Stage loginScreen = new LoginModal().openScreen("Views/Login.fxml");
        loginScreen.setTitle(Main.t("ui_login_window_title"));
        loginScreen.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) {
        this._mainStage = primaryStage;

        try {
            this._loadLocale();
            this._loadLogin();
        } catch (Exception ex) {
            consoleStack(ex);
            System.exit(1);
        }
    }

    public static String t(String prop) throws Exception {
        try {
            return _localeBundle.getString(prop);
        } catch (Exception ex) {
            throw new Exception("[" + prop + "] prop could not be found in current locale");
        }
    }

    public static void consoleStack(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement trace = stackTrace.length > 1 ? stackTrace[1]:stackTrace[0];

        System.out.println("\tLine " + trace.getLineNumber() + " @ " + trace.getFileName() + ": " + ex.getMessage());
    }

    public static Stage getMainStage() {
        return _mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
