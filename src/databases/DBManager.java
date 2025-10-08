/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fatehbhular
 */
public class DBManager {
    private Connection conn;
    
    public DBManager(String jdbcURL, String username, String password) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(jdbcURL, username, password);
            conn.setAutoCommit(false);
            System.out.println("Connected to: " + jdbcURL);
        } catch (ClassNotFoundException e) {
            System.err.println("Derby driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void close() {
        try {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    try {
                        conn.rollback(); // Rollback if commit fails
                    } catch (SQLException e) {
                        System.err.println("Commit failed, rolling back: " + e.getMessage());
                    }
                }
                conn.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
