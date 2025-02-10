package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DB class handles establishing a connection to the MySQL database 
 * used in the Bank Management System. It contains a single static method, Con(),
 * which initializes and returns a database connection.
 * 
 * <p>This class assumes the MySQL JDBC Driver is available and the database 
 * credentials are correct.</p>
 * 
 * @author Ntlakanipho Mgaguli
 * @version 1.0
 * @since 2024
 */
public class DB {

    /**
     * Establishes a connection to the MySQL database using the specified
     * database URL, username, and password.
     * 
     * @return a Connection object to the MySQL database if successful; 
     *         null if a connection could not be established.
     */
    public static Connection Con() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

                                                   // Establish the connection with database URL, username, and password
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "0703");
            return con;
        }  
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } 
        catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
            return null;
        }
    }
}
