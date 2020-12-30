package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.database.UsersRepository;
import pl.kamilsieczkowski.utils.User;

import java.sql.SQLException;

public class Login {
    public boolean isLoginSuccessful(String login, String password) throws SQLException {
        User user = new User(login, password, null);
        UsersRepository usersRepository = new UsersRepository();
        User databaseUser = usersRepository.connectUsersDatabase(login);
        return isUserAndPasswordCorrect(databaseUser, user);
    }

    private boolean isUserAndPasswordCorrect(User databaseUser, User user) {
        return user.equals(databaseUser);
    }
}
