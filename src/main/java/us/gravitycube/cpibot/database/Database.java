package us.gravitycube.cpibot.database;

import us.gravitycube.cpibot.utils.LogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by origi on 12/16/2017.
 */
public class Database {
    public String ipAddress;
    public String port;
    public String username;
    public String password;
    public String databaseName;

    public Connection conn;

    public Database (String ipAddress, String port, String username, String password, String databaseName) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LogUtils.format("MYSQL ERROR", "Cannot find JDBC Driver!");
            e.printStackTrace();
            return false;
        }

        LogUtils.debug("MYSQL","JDBC Driver Registered!");

        try {
            // Connect to database
            LogUtils.log("jdbc:mysql://" + ipAddress + ":" + port + "/" + databaseName + "");
            conn = DriverManager
                    .getConnection("jdbc:mysql://" + ipAddress + ":" + port + "/" + databaseName + "", username, password); //user=sqluser&password=sqluserpw
        } catch (SQLException e) {
            LogUtils.format("MYSQL ERROR", "Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }

        if (conn != null) {
            LogUtils.debug("MYSQL","Connected to database " + databaseName + "!");
            return true;
        } else {
            LogUtils.format("MYSQL ERROR","Failed to make a connection to " + databaseName + "!");
            return false;
        }
    }


}
