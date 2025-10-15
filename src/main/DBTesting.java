/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import databases.DBManager;
import databases.DBOperation;
import entity.Player;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.File;

public class DBTesting {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + File.separator;
        
        String accountDBPath = basePath + "ACCOUNTDATABASE_Ebd";
        String statsDBPath = basePath + "STATSDATABASE_Ebd";
        
        String accountURL = "";
        String statsURL = "";
        
        boolean accountDBExists = new File(accountDBPath).exists();
        boolean statsDBExists = new File(statsDBPath).exists();
        
        if (accountDBExists) {
            accountURL = "jdbc:derby:" + accountDBPath;
        } else {
            accountURL = "jdbc:derby:" + accountDBPath + ";create=true";
        }
        
        if (statsDBExists) {
            statsURL = "jdbc:derby:" + statsDBPath;
        } else {
            statsURL = "jdbc:derby:" + statsDBPath + ";create=true";
        }
        
        DBManager accountDatabase = new DBManager(accountURL, null, null);
        DBManager statsDatabase = new DBManager(statsURL, null, null);
        
        try {
            DBOperation operation = new DBOperation(accountDatabase);
            Player player = new Player("Shawn", 20, 5, 100, "mage");
            operation.updatePlayer(player);
        } finally {
            accountDatabase.close();
            statsDatabase.close();
            
            try {
                DriverManager.getConnection("jdbc:derby:" + basePath + "ACCOUNTDATABASE_Ebd;shutdown=true");
            } catch (SQLException e) {
                if (e.getErrorCode() == 50000) {
                    System.out.println("Accounts DB shut down successfully.");
                }
            }
            
            try {
                DriverManager.getConnection("jdbc:derby:" + basePath + "STATSDATABASE_Ebd;shutdown=true");
            } catch (SQLException e) {
                if (e.getErrorCode() == 50000) {
                    System.out.println("Stats DB shut down successfully.");
                }
            }
        }
    }
}
