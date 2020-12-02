package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.dataInitializer.Initializer;
import pl.kamilsieczkowski.utils.User;

import java.util.List;

public class Login {
    private Initializer initializer;

    public Login(Initializer initializer) {
        this.initializer = initializer;
    }

    public boolean isLoginSuccessful(String login, String password) {
        List<User> usersList = initializer.initializeUsersList();
        User user = new User(login, password, null);
        return isUserAndPasswordCorrect(usersList, user);
    }

    private boolean isUserAndPasswordCorrect(List<User> usersList, User user) {
        return usersList.contains(user);
    }
}
