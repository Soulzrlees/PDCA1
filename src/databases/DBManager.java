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
    private final String dbURL;
    private final String user;
    private final String password;
    private Connection conn;

    public DBManager(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        connect();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(dbURL, user, password);
            conn.setAutoCommit(false); // we manually commit transactions
            System.out.println("Connected to: " + dbURL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connection check failed: " + e.getMessage(), e);
        }
        return conn;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed: " + dbURL);
            }
        } catch (SQLException e) {
            System.err.println("Failed to close connection: " + e.getMessage());
        }
    }

    public void commit() {
        try {
            if (conn != null && !conn.isClosed()) conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Commit failed: " + e.getMessage(), e);
        }
    }

    public void rollback() {
        try {
            if (conn != null && !conn.isClosed()) conn.rollback();
        } catch (SQLException e) {
            System.err.println("Rollback failed: " + e.getMessage());
        }
    }
}
