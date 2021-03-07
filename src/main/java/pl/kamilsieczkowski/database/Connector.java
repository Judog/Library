package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet executeQuery;
    private boolean isConnected;

    public static final Logger LOG = LogManager.getLogger(Connector.class);

    public Connector() {
        this.con = getDatabaseConnection();
    }

    public ResultSet downloadFromDatabase(String enteredQuery) {
        try {
            this.pst = con.prepareStatement(enteredQuery);
            this.executeQuery = pst.executeQuery();
        } catch (SQLException e) {
            LOG.error("can't download from database", e);
        }
        return executeQuery;
    }

    public Connection getDatabaseConnection() {
        try {
            this.con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
            isConnected = true;
        } catch (SQLException e) {
            isConnected = false;
            LOG.error("Can't get database connection", e);
        }
        return con;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void closeConnection() {
        try {
            executeQuery.close();
        } catch (SQLException e) {
            LOG.error("Can't close execute Query", e);
        }
        try {
            pst.close();
        } catch (SQLException e) {
            LOG.error("Can't close preparedStatement", e);
        }
        try {
            con.close();
            isConnected = false;
        } catch (SQLException e) {
            LOG.error("Can't close connection", e);
        }
    }
}