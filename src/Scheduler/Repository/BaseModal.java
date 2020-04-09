package Scheduler.Repository;

import Scheduler.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public abstract class BaseModal<C> {

    private C _controller;
    private int _screenWidth = 400;
    private int _screenHeight = 200;
    private Stage _screenInstance;

    public Stage getScreenInstance() {
        return this._screenInstance;
    }

    protected String getPathPrefix() {
        return "";
    };

    public Stage openScreen(String path) throws Exception {
        Stage primaryStage = Main.getMainStage();

        URL urlResource = this.getClass().getResource(this.getPathPrefix().concat(path));
        final Parent template = new FXMLLoader(urlResource).load();

        // New window (Stage)
        this._screenInstance = new Stage();
        this._screenInstance.setTitle(path);
        this._screenInstance.setScene(new Scene(template, this._screenWidth, this._screenHeight));

        // Specifies the modality for new window.
        this._screenInstance.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        this._screenInstance.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        // this._screenInstance.setX(primaryStage.getX() + 200);
        // this._screenInstance.setY(primaryStage.getY() + 100);
        this._screenInstance.setResizable(false);

        this._screenInstance.show();

        return this._screenInstance;
    }

    public void setController(FXMLLoader loader) {
        this._controller = loader.getController();
    };
}
