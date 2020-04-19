package Scheduler.Repository;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Callback;

public interface IPickerController {
    void fillData(Callback callback) throws Exception;
}
