package Scheduler.Controllers.Components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppointmentsLogComponent {

    public void renderComponentIn(Tab pane) throws IOException {

        URL urlResource = this.getClass().getResource("../../Views/AppointmentsLog.fxml");
        FXMLLoader loader = new FXMLLoader(urlResource);
        final Parent template = loader.load();

        pane.setContent(new Scene(template, 400, 200).getRoot());
    }
}
