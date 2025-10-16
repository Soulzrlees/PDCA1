package gui;


import databases.DBInitialiser;
import databases.DBManager;
import databases.DBOperation;
import entity.Player;
import entity.PlayerStats;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterfaceAction implements ActionListener {

    private final LoginInterface loginInterface;
    private DBManager dbManagerAccounts;
    private DBManager dbManagerStats;
    private DBOperation dbOperationAccounts;
    private DBOperation dbOperationStats;
    private DBInitialiser dbInitialiser;

    public LoginInterfaceAction(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        this.dbInitialiser = new DBInitialiser();

        
        this.dbManagerAccounts = new DBManager(dbInitialiser.getAccountsDB_URL(), null, null);
        this.dbManagerStats = new DBManager(dbInitialiser.getStatsDB_URL(), null, null);
        this.dbOperationAccounts = new DBOperation(dbManagerAccounts);
        this.dbOperationStats = new DBOperation(dbManagerStats);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginInterface.getButton1()){
            String result = loginInterface.getTextField1().getText();

            if (result.equals("")) {
                System.out.println("Nothing to login");
                JOptionPane.showMessageDialog(null, "Please enter a name.", "User has not entered a name!", JOptionPane.ERROR_MESSAGE);
            } 
            else if(!dbOperationAccounts.playerExists(result)){
                System.out.println("Player does not exist");
                JOptionPane.showMessageDialog(null, "There is not accound with that username.", "User has not entered a valid username!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                System.out.println("Logged in");
                Player player = dbOperationAccounts.getPlayer(result);
                PlayerStats playerStats = dbOperationStats.getPlayerStats(result);
                //Testing purposes
                dbOperationAccounts.displayAccounts();
                dbOperationStats.displayStats();
                new MainInterface(player, playerStats);
            }
        }
        if(e.getSource() == loginInterface.getButton2()){
            String nameResult = loginInterface.getTextField2().getText();
            String classResult = loginInterface.getSelectedComboBox();

            if (nameResult.equals("")){
                System.out.println("Nothing to login");
                JOptionPane.showMessageDialog(null, "Please enter a name.", "User has not entered a name!", JOptionPane.ERROR_MESSAGE);
            } else if (dbOperationAccounts.playerExists(nameResult)) {
                System.out.println("Player already exists.");
                JOptionPane.showMessageDialog(null, "Username already exists.", "User has entered name already in use!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                dbOperationAccounts.addPlayer(nameResult, 1, 10, 100, classResult);
                dbOperationStats.addPlayerStats(nameResult, 0, 0, 0);
                Player player = dbOperationAccounts.getPlayer(nameResult);
                PlayerStats playerStats = dbOperationStats.getPlayerStats(nameResult);
                //Testing purposes
                dbOperationAccounts.displayAccounts();
                dbOperationStats.displayStats();
                new MainInterface(player, playerStats);
            }


        }
    }
}
