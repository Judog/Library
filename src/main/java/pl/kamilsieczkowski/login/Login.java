package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.database.UsersRepository;
import pl.kamilsieczkowski.model.User;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class Login {
    private final UsersRepository usersRepository;

    public Login() {
        this.usersRepository = new UsersRepository(new Connector());
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public boolean isLoginSuccessful(String login, String password) throws SQLException {
        User user = new User(login, password, null);
        User databaseUser = usersRepository.connectUsersDatabase(login);
        return isUserAndPasswordCorrect(databaseUser, user);
    }

    private boolean isUserAndPasswordCorrect(User databaseUser, User user) {
            return user.equals(databaseUser);
    }
}
