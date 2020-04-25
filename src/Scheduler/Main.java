package Scheduler;

import Scheduler.Controllers.Modals.LoginModal;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Main extends Application {

    private static Stage _mainStage;
    private static ResourceBundle _localeBundle;

    private static void _loadLocale() throws Exception {
        try {
            Main._localeBundle = ResourceBundle.getBundle("Scheduler/Locales/locale", Locale.getDefault());
            System.out.println(Main.t("cli_locale_loaded"));
        } catch (Exception ex) {
            throw new Exception( "[" + Locale.getDefault() + "] locale not supported");
        }
    }

    private static void _renderLogin() throws Exception {
        Stage loginScreen = new LoginModal().openScreen(Main.getMainStage());

        loginScreen.setTitle(Main.t("ui_login_window_title"));
        loginScreen.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) {
        this._mainStage = primaryStage;

        try {
            this._loadLocale();
            this._renderLogin();
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

    public static void log(String message) {
        System.out.println(message);
    }

    public static void consoleStack(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement trace = stackTrace.length > 1 ? stackTrace[1]:stackTrace[0];

        log("\tLine " + trace.getLineNumber() + " @ " + trace.getFileName() + ": " + ex.getMessage());
    }

    public static Stage getMainStage() {
        return _mainStage;
    }

    public static TimeZone getRefTimeZone() { return TimeZone.getTimeZone("Etc/GMT"); }

    public static void main(String[] args) {
        launch(args);
    }
}
