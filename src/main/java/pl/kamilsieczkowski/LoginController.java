package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.kamilsieczkowski.dataInitializer.Initializer;
import pl.kamilsieczkowski.utils.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(x -> {
            checkLoginStatus();
        });
    }

    private void checkLoginStatus() {
        boolean canLogin = false;
        Initializer initializer = new Initializer();
        for (User user : initializer.initializeUsersList()) {
            boolean loginCorrect = loginField.getText().equals(user.getLogin());
            boolean passwordCorrect = passwordField.getText().equals(user.getPassword());
            ArrayList<Boolean> usersCheckList = new ArrayList<>();
            usersCheckList.add((isLoginAndPasswordCorrect(loginCorrect, passwordCorrect)));
            canLogin = checkUsersDatabase(canLogin, usersCheckList);
        }
        //prints true when users login and password is correct, if not prints false.
        System.out.println(canLogin);
    }

    private boolean checkUsersDatabase(boolean login, ArrayList<Boolean> userCheckList) {
        for (Boolean correct : userCheckList) {
            if (correct) {
                login = true;
            }
        }
        return login;
    }

    private boolean isLoginAndPasswordCorrect(boolean loginCorrect, boolean passwordCorrect) {
        if (loginCorrect && passwordCorrect) {
            return true;
        } else {
            return false;
        }
    }
}
