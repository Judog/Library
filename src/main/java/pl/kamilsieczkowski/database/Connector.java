package pl.kamilsieczkowski.database;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {

    public ResultSet downloadFromDatabase(String enteredQuery) throws SQLException {
        Connection con = connectIntoDatabase();
        PreparedStatement pst = con.prepareStatement(enteredQuery);
        return pst.executeQuery();
    }

    public Connection connectIntoDatabase() throws SQLException {
        return DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
    }
}
