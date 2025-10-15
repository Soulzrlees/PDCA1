/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 *
 * @author fatehbhular
 */
public class DBInitialiser {
    private static final String AccountsDB_URL = "jdbc:derby:ACCOUNTDATABASE_Ebd;create=true";
    private static final String StatsDB_URL = "jdbc:derby:STATSDATABASE_Ebd;create=true";
    
    public static void main(String[] args) {
        System.out.println("Initialising Database...");
        initialiseAccountsDB();
        initialiseStatsDB();
        System.out.println("Initialisation complete!");
        
        try {
            DriverManager.getConnection(AccountsDB_URL + ";shutdown=true");
        } catch (SQLException e) {
            if (e.getErrorCode() == 50000) {
                System.out.println("Accounts DB shut down successfully.");
            }
        }

        try {
            DriverManager.getConnection(StatsDB_URL + ";shutdown=true");
        } catch (SQLException e) {
            if (e.getErrorCode() == 50000) {
                System.out.println("Stats DB shut down successfully.");
            }
        }
    }
    
    public static void initialiseAccountsDB() {
        DBManager dbManager = new DBManager(AccountsDB_URL, null, null);
        
        try {
            Connection conn = dbManager.getConnection();
            Statement statement = conn.createStatement();
            
            String sql = """
                         CREATE TABLE ACCOUNTS (
                            USERNAME VARCHAR(30) PRIMARY KEY, 
                            LEVEL INT, 
                            EXP INT, 
                            GOLD INT, 
                            CLASS VARCHAR(20)
                         )
                         """;
            statement.executeUpdate(sql);
            conn.commit();
            System.out.println("Table created successfully!");
            statement.close();
        } catch (SQLException e) {
            System.err.println("Accounts table was not created: " + e.getMessage());
        } finally {
            dbManager.close();
        }
    }
    
    public static void initialiseStatsDB() {
        DBManager dbManager = new DBManager(StatsDB_URL, null, null);
        
        try {
            Connection conn = dbManager.getConnection();
            Statement statement = conn.createStatement();
            
            String sql = """
                         CREATE TABLE STATS (
                            USERNAME VARCHAR(30) PRIMARY KEY,
                            DAMAGE_POINTS INT, 
                            HEALTH_POINTS INT,
                            RANGE_POINTS INT
                         )
                         """;
            statement.executeUpdate(sql);
            conn.commit();
            System.out.println("Table created successfully!");
            statement.close();
        } catch (SQLException e) {
            System.err.println("Stats table was not created: " + e.getMessage());
        } finally {
            dbManager.close();
        }
    }

    public String getAccountsDB_URL() {
        return AccountsDB_URL;
    }
    
    public String getStatsDB_URL() {
        return StatsDB_URL;
    }
}
