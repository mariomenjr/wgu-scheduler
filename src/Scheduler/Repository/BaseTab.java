package Scheduler.Repository;

import Scheduler.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;

public abstract class BaseTab<C> implements IComponent<C> {

    abstract public void refresh() throws Exception;

    private C _controller;

    public Scene renderAt(Tab pane) throws IOException {
        URL urlResource = this.getClass().getResource(this.getViewPath());
        FXMLLoader loader = new FXMLLoader(urlResource);
        final Parent template = loader.load();

        Scene component = new Scene(template, pane.getTabPane().getTabMaxWidth(), pane.getTabPane().getTabMaxHeight());
        pane.setContent(component.getRoot());

        this.setController(loader);

        return component;
    }

    @Override
    abstract public String getViewPath();

    @Override
    public C getController() {
        return this._controller;
    }

    @Override
    public void setController(FXMLLoader loader) {
        this._controller = loader.getController();
        try {
            // ((BaseController)this._controller).setStage(this.getStage());
        } catch (Exception ex) {
            Main.consoleStack(ex);
        }
    };
}
