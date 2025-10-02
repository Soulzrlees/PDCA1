/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import databases.DBManager;
/**
 *
 * @author fatehbhular
 */
public class DBTesting {
    public static void main(String[] args) {
        String basePath = "/Users/fatehbhular/NetBeansProjects/P12_23217987_23204035/";
        
        DBManager accountDatabase = new DBManager("jdbc:derby:" + basePath + "ACCOUNTDATABASE_Ebd;create=true", "pdc", "pdc");
        DBManager statsDatabase = new DBManager("jdbc:derby:" + basePath + "STATSDATABASE_Ebd;create=true", "pdc", "pdc");
        
        accountDatabase.close();
        statsDatabase.close();
    }
}
