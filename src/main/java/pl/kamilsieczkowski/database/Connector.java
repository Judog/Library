package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.observators.Observator;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet executeQuery;
    private Observator connectionObservator;

    public Observator getConnectionObservator() {
        return connectionObservator;
    }

    public static final Logger LOG = LogManager.getLogger(Connector.class);

    public Connector() {
        this.con = getDatabaseConnection();
        this.connectionObservator = new Observator();
    }

    public ResultSet downloadFromDatabase(String enteredQuery) {
        try {
            this.pst = con.prepareStatement(enteredQuery);
            this.executeQuery = pst.executeQuery();
        } catch (SQLException e) {
            LOG.error("can't download from database",e);
        }
        return executeQuery;
    }

    public Connection getDatabaseConnection() {
        try {
            this.con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
        } catch (SQLException e) {
            connectionObservator.setObservatatedProcessNotExecuted(false);
            LOG.error("Can't get database connection",e);
        }
        return con;
    }

    public void closeConnection() {
        try {
            executeQuery.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            LOG.error("Can't close connection",e);
        }
    }
}
