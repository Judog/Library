package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Observator loginObservator;
    public static final Logger LOG = LogManager.getLogger(UsersRepository.class);


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
        try {
            StringBuilder enteredQuery = new StringBuilder(QUERY_USER).append(enteredLogin).append("';"); // select row of entered login, from users database
            ResultSet resultSet = connector.downloadFromDatabase(enteredQuery.toString());
            resultSet.next();// go to next (first) row
            username = resultSet.getString(USERNAME);
            password = resultSet.getString(PASSWORD);
            privilege = resultSet.getString(PRIVILEGE);
            checkIsLoginExist(resultSet);
        } catch (SQLException e) {
            LOG.error("Can't get a result", e);
        } finally {
            connector.closeConnection();
        }
        return new User(username, password, privilege);
    }

    public Observator getLoginObservator() {
        return loginObservator;
    }

    private void checkIsLoginExist(ResultSet resultSet) throws SQLException {
        loginObservator.setObservatatedProcessNotExecuted(resultSet.wasNull());
    }
}
