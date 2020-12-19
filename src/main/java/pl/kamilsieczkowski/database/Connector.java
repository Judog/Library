package pl.kamilsieczkowski.database;

import pl.kamilsieczkowski.utils.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {
    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        String query = "SELECT * FROM library_users.users WHERE username='";
        String enteredQuery = query + enteredLogin + "'"; // select row of entered login, from users database
        User user = new User(enteredLogin, null, null);
        Connection con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
        PreparedStatement pst = con.prepareStatement(enteredQuery);
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String privilege = resultSet.getString("privilege");
            user = new User(username, password, privilege);
        }

        return user;
    }
}
