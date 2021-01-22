package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersRepository {
    private Connector connector;

    public UsersRepository(Connector connector) {
        this.connector = connector;
    }

    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        String queryUser = "SELECT * FROM library_users.users WHERE username='";
        String enteredQuery = queryUser + enteredLogin + "'"; // select row of entered login, from users database
        ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
        resultSet.next();// go to next (first) row
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String privilege = resultSet.getString("privilege");
        connector.closeConnection();
        return new User(username, password, privilege);
    }
}
