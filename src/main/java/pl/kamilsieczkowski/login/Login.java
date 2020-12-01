package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.dataInitializer.Initializer;
import pl.kamilsieczkowski.utils.User;


import java.util.ArrayList;
import java.util.List;

public class Login {
    private Initializer initializer;

    public Login(Initializer initializer) {
        this.initializer = initializer;
    }

    public boolean checkLoginStatus(String login, String password) {
        List<User> usersList = initializer.initializeUsersList();
        User user = new User(login, password, null);
        return loginStatus(usersList, user);
    }

    private boolean loginStatus(List<User> usersList, User user) {
        return usersList.contains(user);
    }
}
