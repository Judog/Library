package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.utils.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.QUERY_USER;

public class UsersRepository extends Connector {
    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        String enteredQuery = QUERY_USER + enteredLogin + "'"; // select row of entered login, from users database
        User user = new User(enteredLogin, null, null);
        ResultSet resultSet = downloadFromDatabase(enteredQuery);
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String privilege = resultSet.getString("privilege");
            user = new User(username, password, privilege);
        }
        return user;
    }
}
