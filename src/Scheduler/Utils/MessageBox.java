package Scheduler.Utils;

import javafx.scene.control.Alert;
import javafx.util.Callback;

public class MessageBox {

    private static Alert _show(String header, String message, Alert.AlertType type) {
        Alert messageBox = new Alert(type);

        messageBox.setHeaderText(header);
        messageBox.setContentText(message);
        messageBox.show();

        return messageBox;
    }

    public static Alert showInformation(String header, String message) {
        return _show(header,message, Alert.AlertType.INFORMATION);
    }

    public static Alert showWarning(String header, String message) {
        return _show(header,message, Alert.AlertType.WARNING);
    }

    public static Alert askConfirmation(String header, String message, Callback callback) {
        Alert box = _show(header, message, Alert.AlertType.CONFIRMATION);
        box.resultProperty().addListener(
            (observableValue, buttonType, button) -> {
                if (button.getButtonData().isCancelButton()) return;
                callback.call(null);
            }
        );
        return box;
    }

    public static Alert showError(String header, String message) {
        return _show(header,message, Alert.AlertType.ERROR);
    }
}
