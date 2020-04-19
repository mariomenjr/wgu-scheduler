package Scheduler.Utils;

import javafx.scene.control.Alert;

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

    public static Alert showError(String header, String message) {
        return _show(header,message, Alert.AlertType.ERROR);
    }
}
