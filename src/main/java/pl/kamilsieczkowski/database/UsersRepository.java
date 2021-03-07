package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.dto.UserDTO;
import pl.kamilsieczkowski.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class UsersRepository {
    public static final Logger LOG = LogManager.getLogger(UsersRepository.class);
    private final Connector connector;
    private final String QUERY_USER = "SELECT * FROM library_users.users WHERE username='";

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
            connector.preparedStatementClose();
            connector.executeQueryClose();
            connector.closeConnection();
        }
        return new User(username, password, privilege);
    }

    public boolean isConnected() {
        return connector.isConnected();
    }


}
