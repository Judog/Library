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

    public UsersRepository(Connector connector) {
        this.connector = connector;
    }

    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        String enteredQuery = QUERY_USER + enteredLogin + "';"; // select row of entered login, from users database
        ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
        resultSet.next();// go to next (first) row
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        String privilege = resultSet.getString(PRIVILEGE);
        connector.closeConnection();
        return new User(username, password, privilege);
    }
   // public boolean checkThatUsersExist(String enteredLogin){
     //   String enteredQuery = QUERY_USER + enteredLogin + "';";
     //   ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
     //   resultSet.next();
      //  String username = resultSet.getString(USERNAME);
      //  if (username.equals(null)){

    //    }if (enteredLogin.equals())
  //  }
}
