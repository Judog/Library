package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.login.Login;
import pl.kamilsieczkowski.observators.Observator;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.util.ResourceBundle;

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
        loginButton.setOnAction(login ->
                checkUserAndPassword(new Login(), new Window()));
    }

    private void setWindowText() {
        passwordLabel.setText(PASSWORD);
        userFieldLabel.setText(USER);
        loginStatus.setText(LOGIN_STATUS);
        loginButton.setText(LOGIN);
    }

    private void checkUserAndPassword(Login loginObject, Window window) {
        if (loginObject.isLoginSuccessful(this.loginField.getText(), this.passwordField.getText())) {
            window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
        } else if (isNotLogged(getLoginObservator(loginObject))) {
            setLoginStatusLabel(LOGIN_DOES_NOT_EXIST);
        } else if (isNotConnectedToDatabase(loginObject)) {
            setLoginStatusLabel(CANT_CONNECT);
        } else {
            loginStatus.setText(LOGIN_FAILED);
        }
    }

    private boolean isNotLogged(Observator loginObservator) {
        return loginObservator.isObservatatedProcessNotExecuted();
    }

    private Observator getLoginObservator(Login loginObject) {
        return loginObject
                .getUsersRepository()
                .getLoginObservator();
    }

    private void setLoginStatusLabel(String text) {
        loginStatus.setText(text);
    }

    private boolean isNotConnectedToDatabase(Login loginObject) {
        return loginObject.getUsersRepository()
                .getConnector()
                .getConnectionObservator()
                .isObservatatedProcessNotExecuted();
    }
}
