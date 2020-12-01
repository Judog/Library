package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPopupController implements Initializable {


    @FXML
    private Button okButton;
    @FXML
    private Pane popupPane;
    @FXML
    private Label loginStatusText;


    public void generatePopup() throws IOException {
        Pane root = new Pane();
        root.setPrefSize(150, 150);
        Parent content = FXMLLoader.load(getClass().getResource("/loginPopup.fxml"));
        Scene scene = new Scene(content);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }

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

