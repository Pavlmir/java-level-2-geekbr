package geekbrains.lesson4;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    private TextArea textArea;

    @FXML
    private TextField msgField;

    public void sendMsg() {
        textArea.setText(textArea.getText() + ((textArea.getText().equals("")) ? "" : "\n") + msgField.getText());
        msgField.setText("");
    }

    @FXML
    private void event(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            sendMsg();
        }
    }
}
