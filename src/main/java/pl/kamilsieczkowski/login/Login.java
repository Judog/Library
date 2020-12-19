package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.utils.User;

import java.sql.SQLException;

public class Login {
    public boolean isLoginSuccessful(String login, String password) throws SQLException {
        User user = new User(login, password, null);
        Connector connector = new Connector();
        User databaseUser = connector.connectUsersDatabase(login);
        return isUserAndPasswordCorrect(databaseUser, user);
    }

    private boolean isUserAndPasswordCorrect(User databaseUser, User user) {
        return user.equals(databaseUser);
    }
}
