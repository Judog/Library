package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.dto.UserDTO;
import pl.kamilsieczkowski.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    public static final Logger LOG = LogManager.getLogger(UsersRepository.class);
    private final Connector connector;
    private final String QUERY_USER = "SELECT * FROM library_users.users WHERE username='";
    private final String QUERY_CHECK_USERS = "SELECT username FROM library_users.users;";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PRIVILEGE = "privilege";
    private boolean isExist;

    public UsersRepository(Connector connector) {
        this.connector = connector;
    }

    public User connectUsersDatabase(String enteredLogin) {
        String username = "";
        String password = "";
        String privilege = "";
        ResultSet resultSet;
        try {
            StringBuilder enteredQuery = new StringBuilder(QUERY_USER).append(enteredLogin).append("';"); // select row of entered login, from users database
            resultSet = connector.downloadFromDatabase(enteredQuery.toString());
            resultSet.next();// go to next (first) row
            username = resultSet.getString(USERNAME);
            password = resultSet.getString(PASSWORD);
            privilege = resultSet.getString(PRIVILEGE);
            UserDTO.setUser(new User(username, password, privilege));// Set instance in dto
        } catch (SQLException e) {
            LOG.error("Can't get a result", e);
        } finally {
            connector.closeConnection();
        }
        return new User(username, password, privilege);
    }

    public boolean isConnected() {
        return connector.isConnected();
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

    public boolean isLoginSuccessful(String login, String password) {
        User user = new User(login, password, null);
        User databaseUser = connectUsersDatabase(login);
        return isUserAndPasswordCorrect(databaseUser, user);
    }

    private boolean isUserAndPasswordCorrect(User databaseUser, User user) {
        return user.equals(databaseUser);
    }
}
