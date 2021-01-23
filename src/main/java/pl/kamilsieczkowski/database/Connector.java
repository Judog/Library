package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;
import static pl.kamilsieczkowski.constants.Texts.SQL_EXCEPTION;

public class Connector {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet executeQuery;
    public boolean isConnectedToDatabase;

    public static final Logger LOG = LogManager.getLogger(Connector.class);

    public Connector() {
        this.con = getDatabaseConnection();
    }

    public ResultSet downloadFromDatabase(String enteredQuery) {
        try {
            this.pst = con.prepareStatement(enteredQuery);
            this.executeQuery = pst.executeQuery();
        } catch (SQLException e) {
            LOG.error(SQL_EXCEPTION + " Connector downloadFromDatabase");
        }
        return executeQuery;
    }

    public Connection getDatabaseConnection() {
        this.isConnectedToDatabase = true;
        try {
            this.con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
        } catch (SQLException e) {
            LOG.error(SQL_EXCEPTION + " Connector connectIntoDatabase");
            this.isConnectedToDatabase = false;
        }
        return con;
    }

    public void closeConnection() {
        String CLOSE_CONNECTION = "Connector closeConnection";
        try {
            executeQuery.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            LoggerMessageAtExceptionInCloseConnection(CLOSE_CONNECTION);
        }
    }

    private void LoggerMessageAtExceptionInCloseConnection(String CLOSE_CONNECTION) {
        LOG.error(SQL_EXCEPTION + CLOSE_CONNECTION);
    }
}
