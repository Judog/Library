package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.login.Login;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.LOG;
import static pl.kamilsieczkowski.constants.Constants.SOURCE_LIBRARY_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class LoginController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label userFieldLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWindowText();

        loginButton.setOnAction(login -> {
            checkUserAndPassword(new Login(), new Window());
        });
    }

    private void setWindowText() {
        passwordLabel.setText(PASSWORD);
        userFieldLabel.setText(USER);
        loginStatus.setText(LOGIN_STATUS);
        loginButton.setText(LOGIN);
    }

    private void checkUserAndPassword(Login loginObject, Window window) {
        try {
            if (loginObject.isLoginSuccessful(this.loginField.getText(), this.passwordField.getText())) {
                window.openNewWindow(SOURCE_LIBRARY_WINDOW, LOGGED_IN);
                window.closeWindow(this.pane);
            } else {
                loginStatus.setText(LOGIN_FAILED);
            }
        } catch (SQLException e) {
            LOG.error(SQL_EXCEPTION + " Login Controller");
        }
    }
}
