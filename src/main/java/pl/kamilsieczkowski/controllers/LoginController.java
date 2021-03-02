package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.database.UsersRepository;
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
                checkUserAndPassword(new UsersRepository(new Connector()), new Window()));
    }

    private void setWindowText() {
        passwordLabel.setText(PASSWORD);
        userFieldLabel.setText(USER);
        loginStatus.setText(LOGIN_STATUS);
        loginButton.setText(LOGIN);
    }

    private void checkUserAndPassword(UsersRepository usersRepository, Window window) {
        if (!usersRepository.isConnected()) {
            setLoginStatusLabel(CANT_CONNECT);
        } else if (!usersRepository.checkIsLoginExist(this.loginField.getText())) {
            setLoginStatusLabel(LOGIN_DOES_NOT_EXIST);
        } else if (usersRepository.isLoginSuccessful(this.loginField.getText(), this.passwordField.getText())) {
            window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
        } else {
            loginStatus.setText(LOGIN_FAILED);
        }
    }

    private void setLoginStatusLabel(String text) {
        loginStatus.setText(text);
    }
}
