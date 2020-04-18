package Scheduler.Repository;

import javafx.scene.input.KeyEvent;

public interface ILog {
    void fillData() throws Exception;
    void onTypeSearch(KeyEvent keyEvent);
}
