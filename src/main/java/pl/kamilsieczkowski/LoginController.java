package pl.kamilsieczkowski;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            loginButton.setOnAction(x->{
                System.out.println("Login");
            });

    }
}
