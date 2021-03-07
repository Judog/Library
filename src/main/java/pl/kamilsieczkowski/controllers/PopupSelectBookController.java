package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Texts.*;

public class PopupSelectBookController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Button okButton;
    @FXML
    private Label textLabel;
    Window window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWindowText();
        createWindowInstance();
        okButton.setOnAction(login -> window.closeWindow(this.pane));
    }

    private void setWindowText() {
        textLabel.setText(SELECT_BOOK);
        okButton.setText(OK);
    }

    private void createWindowInstance() {
        window = new Window();
    }

}
