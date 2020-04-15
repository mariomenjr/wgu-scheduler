package Scheduler.Repository;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;

public abstract class BaseComponent<C> {

    abstract protected String getViewPath();

    public Scene renderAt(Tab pane) throws IOException {
        URL urlResource = this.getClass().getResource(this.getViewPath());
        FXMLLoader loader = new FXMLLoader(urlResource);
        final Parent template = loader.load();

        Scene component = new Scene(template, pane.getTabPane().getTabMaxWidth(), pane.getTabPane().getTabMaxHeight());
        pane.setContent(component.getRoot());

        return component;
    }

    public void refresh() {
        System.out.println("Refresh " + this.getClass().getName());
    };
}
