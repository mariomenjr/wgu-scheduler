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

    abstract protected String getViewPath();

    public Stage openScreen() throws Exception {
        Stage primaryStage = Main.getMainStage();

        URL urlResource = this.getClass().getResource(this.getViewPath());
        FXMLLoader loader = new FXMLLoader(urlResource);
        final Parent template = loader.load();

        // New window (Stage)
        this._screenInstance = new Stage();
        this._screenInstance.setScene(new Scene(template, this._screenWidth, this._screenHeight));

        this._screenInstance.initModality(Modality.WINDOW_MODAL);
        this._screenInstance.initOwner(primaryStage);
        this._screenInstance.setResizable(false);
        this._screenInstance.show();

        this.setController(loader);

        return this._screenInstance;
    }

    public Stage getStage() {
        return this._screenInstance;
    }

    public void setController(FXMLLoader loader) {
        this._controller = loader.getController();
        try {
            ((BaseController)this._controller).setStage(this.getStage());
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }
    };

    public C getController() {
        return this._controller;
    }
}
