package pl.kamilsieczkowski.database;


import pl.kamilsieczkowski.utils.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {

    ResultSet connectIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
        PreparedStatement pst = con.prepareStatement(enteredQuery);
        return pst.executeQuery();
    }
}
