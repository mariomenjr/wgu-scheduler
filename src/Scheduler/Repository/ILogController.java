package Scheduler.Repository;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

public interface ILogController {
    void fillData() throws Exception;
    void onTypeSearch(KeyEvent keyEvent);
    void onAddClick(ActionEvent actionEvent);
    void onEditClick(ActionEvent actionEvent);
    void onRemoveClick(ActionEvent actionEvent);
}
