package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.model.User;
import pl.kamilsieczkowski.observators.Observator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    private final Connector connector;
    private final String QUERY_USER = "SELECT * FROM library_users.users WHERE username='";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PRIVILEGE = "privilege";
    private Observator loginObservator;

    public Connector getConnector() {
        return connector;
    }

    public UsersRepository(Connector connector) {
        this.connector = connector;
        this.loginObservator = new Observator();
    }

    public User connectUsersDatabase(String enteredLogin) {
        String username = "";
        String password = "";
        String privilege = "";
        ResultSet resultSet = null;
        try {
            StringBuilder enteredQuery = new StringBuilder(QUERY_USER).append(enteredLogin).append("';"); // select row of entered login, from users database
            resultSet = connector.downloadFromDatabase(enteredQuery.toString());
            resultSet.next();// go to next (first) row
            username = resultSet.getString(USERNAME);
            password = resultSet.getString(PASSWORD);
            privilege = resultSet.getString(PRIVILEGE);
            checkIsLoginExist(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connector.closeConnection();
        }
        return new User(username, password, privilege);
    }

    public Observator getLoginObservator() {
        return loginObservator;
    }

    private void checkIsLoginExist(ResultSet resultSet) throws SQLException {
        loginObservator.setObservatatedProcessExecuted(resultSet.wasNull());
    }
}
