package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersRepository {
    private final Connector connector;
    private final String QUERY_USER = "SELECT * FROM library_users.users WHERE username='";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PRIVILEGE = "privilege";
    public boolean isLoginExist;

    public Connector getConnector() {
        return connector;
    }

    public UsersRepository(Connector connector) {
        this.connector = connector;
    }

    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        this.isLoginExist = true;
        String enteredQuery = QUERY_USER + enteredLogin + "';"; // select row of entered login, from users database
        ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
        resultSet.next();// go to next (first) row
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        String privilege = resultSet.getString(PRIVILEGE);
        connector.closeConnection();
        checkIsLoginExist(username, enteredLogin);
        return new User(username, password, privilege);
    }

    private void checkIsLoginExist(String userName, String enteredLogin) {
        this.isLoginExist = userName.equals(enteredLogin);
    }
}
