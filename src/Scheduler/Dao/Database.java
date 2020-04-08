package Scheduler.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mario Menjívar
 * */
public class Database {

    private static final String dbName = "";
    private static final String userName = "";
    private static final String password = "";
    private static final String hostName = "";
    private static final String driver = "com.mysql.jdbc.Driver";
    public static Connection conn;

    public static void connect() throws ClassNotFoundException, SQLException, Exception {
        Class.forName(driver);
        String connString = "jdbc:mysql://" + hostName + "/" + dbName;
        conn = (Connection) DriverManager.getConnection(connString, userName, password);

        System.out.println("Successfully connected to " + dbName + "@" + hostName);
    }

    public static void disconnect() throws ClassNotFoundException, SQLException, Exception {
        conn.close();
        System.out.println("Disconnected from " + dbName + "@" + hostName);
    }

}
