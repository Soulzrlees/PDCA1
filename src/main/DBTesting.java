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

public class DBTesting {
    public static void main(String[] args) {
        String basePath = "/Users/fatehbhular/NetBeansProjects/P12_23217987_23204035/";
        
        DBManager accountDatabase = new DBManager("jdbc:derby:" + basePath + "ACCOUNTDATABASE_Ebd;create=true", null, null);
        DBManager statsDatabase = new DBManager("jdbc:derby:" + basePath + "STATSDATABASE_Ebd;create=true", null, null);
        
        try {
            DBOperation operation = new DBOperation(accountDatabase);
            operation.addPlayer("Shawn", 10, 0, 100, "melee");
            operation.DisplayDB();
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
