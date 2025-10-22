package gui.login;


import gui.main.MainInterface;
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

        //Initialize the database 
        this.dbManagerAccounts = new DBManager(dbInitialiser.getAccountsDB_URL(), null, null);
        this.dbManagerStats = new DBManager(dbInitialiser.getStatsDB_URL(), null, null);
        this.dbOperationAccounts = new DBOperation(dbManagerAccounts);
        this.dbOperationStats = new DBOperation(dbManagerStats);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginInterface.getButton1()){ //If the Login button is chosen
            String result = loginInterface.getTextField1().getText();

            if (result.equals("")) { //If the textfield is null than error message would pop up
                System.out.println("Nothing to login");
                JOptionPane.showMessageDialog(null, "Please enter a name.", "User has not entered a name!", JOptionPane.ERROR_MESSAGE);
            } 
            else if(!dbOperationAccounts.playerExists(result)){ //If the player you are trying to log is not in the database a error message would pop up
                System.out.println("Player does not exist");
                JOptionPane.showMessageDialog(null, "There is not accound with that username.", "User has not entered a valid username!", JOptionPane.ERROR_MESSAGE);
            }
            else { //If the player name exist
                System.out.println("Logged in");
                Player player = dbOperationAccounts.getPlayer(result);
                PlayerStats playerStats = dbOperationStats.getPlayerStats(result);
                player.setPlayerStats(playerStats);
                
                //setting the base stats of the player with the skillpoints
                player.setbaseDmg(player.getbaseDmg() + playerStats.getDamageSkillPoints());
                player.setMaxHealth(player.getMaxHealth()+ playerStats.getHealthSkillPoints());
                player.setAttackRange(player.getAttackRange() + playerStats.getRangeSkillPoints());
                player.setHealth(player.getMaxHealth());
                
                //Testing purposes
                System.out.println(player);
                System.out.println(playerStats);
                loginInterface.dispose();
                new MainInterface(player, playerStats);
            }
        }
        if(e.getSource() == loginInterface.getButton2()){ //If the new account creation button is chosen
            String nameResult = loginInterface.getTextField2().getText();
            String classResult = loginInterface.getSelectedComboBox();

            if (nameResult.equals("")){ //If the textfield is null than error message would pop up
                System.out.println("Nothing to login");
                JOptionPane.showMessageDialog(null, "Please enter a name.", "User has not entered a name!", JOptionPane.ERROR_MESSAGE);
            } else if (dbOperationAccounts.playerExists(nameResult)) { //If the player you are trying to log is inthe database a error message would pop up
                System.out.println("Player already exists.");
                JOptionPane.showMessageDialog(null, "Username already exists.", "User has entered name already in use!", JOptionPane.ERROR_MESSAGE);
            }
            else { //If there is no such existing player name
                dbOperationAccounts.addPlayer(nameResult, 1, 0, 0, classResult);
                dbOperationStats.addPlayerStats(nameResult, 0, 0, 0);
                Player player = dbOperationAccounts.getPlayer(nameResult);
                PlayerStats playerStats = dbOperationStats.getPlayerStats(nameResult);
                System.out.println(player);
                System.out.println(playerStats);
                loginInterface.dispose();
                new MainInterface(player, playerStats);
            }

        }
    }
}
