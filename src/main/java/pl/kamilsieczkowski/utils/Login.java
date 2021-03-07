package pl.kamilsieczkowski.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.database.UsersRepository;
import pl.kamilsieczkowski.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.USERNAME;

public class Login {
    private final String QUERY_CHECK_USERS = "SELECT username FROM library_users.users;";
    private Connector connector;
    private UsersRepository usersRepository;
    private boolean isExist;

    public static final Logger LOG = LogManager.getLogger(Login.class);

    public Login() {
        this.connector = new Connector();
        this.usersRepository = new UsersRepository(connector);
    }

    public boolean isLoginSuccessful(String login, String password) {
        User user = new User(login, password, null);
        User databaseUser = usersRepository.connectUsersDatabase(login);
        return isUserAndPasswordCorrect(databaseUser, user);
    }

    private boolean isUserAndPasswordCorrect(User databaseUser, User user) {
        return user.equals(databaseUser);
    }

    public boolean checkIsLoginExist(String user) {
        isExist = false;
        ResultSet resultSet = connector.downloadFromDatabase(QUERY_CHECK_USERS);
        try {
            while (resultSet.next()) {
                String username = resultSet.getString(USERNAME);
                isExist = ifUserEquals(user, username);
            }
        } catch (SQLException e) {
            LOG.error("Can't get a result", e);
        }
        return isExist;
    }

    private boolean ifUserEquals(String user, String username) {
        if (username.equals(user)) {
            isExist = true;
        }
        return isExist;
    }
}
