package Scheduler.Repository;

import javafx.fxml.FXMLLoader;

public interface IComponent<C> {

    // Getter and Setter for Controller
    C getController();
    void setController(FXMLLoader loader);

    String getViewPath();
}
