package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.utils.User;

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
        User user = new User(enteredLogin, null, null);
        ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String privilege = resultSet.getString("privilege");
            user = new User(username, password, privilege);
        }
        return user;
    }
}
