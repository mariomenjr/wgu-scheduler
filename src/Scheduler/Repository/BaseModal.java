package Scheduler.Repository;

import Scheduler.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public abstract class BaseModal<C> implements IComponent<C> {

    private Stage _modal;
    private C _controller;

    private int _screenWidth = 400;
    private int _screenHeight = 200;

    public Stage openScreen() throws Exception {
        Stage primaryStage = Main.getMainStage();

        URL urlResource = this.getClass().getResource(this.getViewPath());
        FXMLLoader loader = new FXMLLoader(urlResource);
        final Parent template = loader.load();

        // New window (Stage)
        this._modal = new Stage();
        this._modal.setScene(new Scene(template, this._screenWidth, this._screenHeight));

        this._modal.initModality(Modality.WINDOW_MODAL);
        this._modal.initOwner(primaryStage);
        this._modal.setResizable(false);
        this._modal.show();

        this.setController(loader);

        return this._modal;
    }

    public Stage getStage() {
        return this._modal;
    }

    @Override
    abstract public String getViewPath();

    @Override
    public void setController(FXMLLoader loader) {
        this._controller = loader.getController();
        try {
            ((BaseController)this._controller).setStage(this.getStage());
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }
    };

    @Override
    public C getController() {
        return this._controller;
    }
}
