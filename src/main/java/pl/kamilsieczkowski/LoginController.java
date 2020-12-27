package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.kamilsieczkowski.login.Login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Texts.*;

public class LoginController implements Initializable {
    @FXML
    private Pane window;
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
        Login loginObject = new Login();
        loginButton.setOnAction(login -> {
            try {
                checkUserAndPassword(loginObject);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void setWindowText() {
        passwordLabel.setText(PASSWORD);
        userFieldLabel.setText(USER);
        loginStatus.setText(LOGIN_STATUS);
        loginButton.setText(LOGIN);
    }

    private void checkUserAndPassword(Login loginObject) throws IOException, SQLException {
        if (loginObject.isLoginSuccessful(loginField.getText(), passwordField.getText())) {
            newWindow();
        } else {
            loginStatus.setText(LOGIN_FAILED);
        }
    }

    private void newWindow() throws IOException {
        window = new Pane();
        Stage stage = new Stage();
        Parent content = FXMLLoader.load(getClass().getResource("/loginPopup.fxml"));
        Scene scene = new Scene(content);
        stage.setTitle(LOGGED_IN);
        stage.setScene(scene);
        stage.show();
    }
}
