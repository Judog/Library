package pl.kamilsieczkowski.login;

import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.database.UsersRepository;
import pl.kamilsieczkowski.model.User;

import java.sql.SQLException;

public class Login {
    private UsersRepository usersRepository;

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
        if (databaseUser.getLogin().equals(null)) {
            return false;
        } else {
            return user.equals(databaseUser);
        }
    }
}
