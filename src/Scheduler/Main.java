package Scheduler;

import Scheduler.Controllers.LoginController;
import Scheduler.Dao.Database;
import Scheduler.Utils.LoginModal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {

    private static Stage _mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this._mainStage = primaryStage;

        Stage loginScreen = new LoginModal().openScreen("Views/Login.fxml");
        loginScreen.setTitle("Welcome! / ¡Bienvenido!");
        loginScreen.centerOnScreen();
    }

    public static Stage getMainStage() {
        return _mainStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
