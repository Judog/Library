package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;


public class LoginPopupController implements Initializable {
    @FXML
    private Button okButton;
    @FXML
    private Pane popupPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okButton.setOnAction((ActionEvent event) -> closeWindow());
    }

    private void closeWindow() {
        getStage().close();
    }

    private Stage getStage() {
        return (Stage) popupPane.getScene().getWindow();
    }
}

