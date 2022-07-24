package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * The DB Connection database class.
 */
public class DBConnection {

    /**
     * Provide the connection protocol.
     */
    private static final String protocol = "jdbc";
    /**
     * Describe the database vendor.
     */
    private static final String vendor = ":mysql:";
    /**
     * Provide the database host location.
     */
    private static final String location = "//localhost/";
    /**
     * Provide the database name.
     */
    private static final String databaseName = "client_schedule";
    /**
     * Build the jdbc Url.
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /**
     * Set the database driver.
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**
     * Hard coded database user value.
     */
    private static final String userName = "sqlUser"; // Username
    /**
     * Hard Coded Password (no-no outside here)
     */
    private static String password = "Passw0rd!"; // Password
    /**
     * Connection interface
     */
    private static Connection connection = null;  // Connection Interface
//    private static PreparedStatement preparedStatement;


    /**
     * Create the connection to the database.
     * run once per session
     */
    public static void makeConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException | SQLException e) {
//            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Close the connection.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * use an existing connection
     * @return the connection object.
     */
    public static Connection getConnection() {
        return connection;
    }

}
