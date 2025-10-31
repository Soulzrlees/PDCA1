/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import action.EnemyAction;
import entity.Player;
import entity.Enemy;
import entity.PlayerStats;
import gui.main.MainInterface;
import databases.DBInitialiser;
import databases.DBManager;
import databases.DBOperation;
import action.PlayerAction;

import javax.swing.*;

/**
 *
 * @author Shawn lee
 */
public class BattleController {

    private Player player;
    private PlayerStats playerStats;
    private Enemy enemy;
    private DBManager dbManagerAccounts;
    private DBOperation dbOperationAccounts;
    private DBInitialiser dbInitialiser;
    private BattleInterface battleInterface;
    private BattleLogPanel battleLogPanel;
    private boolean battleWon;

    // Track rounds
    private int round = 1;
    
    //Track healing count
    private int healCount = 0;
    private final int maxHealCount = 2;

    //Tracking base stats
    private int originalBaseDamage;
    private int originalAttackRange;
    
    public BattleController(Player player, Enemy enemy, BattleInterface battleInterface) {
        this.player = player;
        this.enemy = enemy;
        this.battleInterface = battleInterface;

        this.dbInitialiser = new DBInitialiser();
        this.dbManagerAccounts = new DBManager(dbInitialiser.getAccountsDB_URL(), null, null);
        this.dbOperationAccounts = new DBOperation(dbManagerAccounts);

        this.battleLogPanel = battleInterface.getLogPanel();
        this.originalAttackRange = player.getAttackRange();
        this.originalBaseDamage = player.getbaseDmg();
    }

    // Player action called from GUI
    
    //The method for the gui to call the attack method
    public void playerAttack() {
        PlayerAction playerAction = new PlayerAction(battleLogPanel);
        playerAction.attack(enemy, player, round);
        battleInterface.getScreenPanel().refreshPositions();
        nextTurn();
    }
    
    //Player action (Heal) 
    public void playerHeal() {
        if(healCount >= maxHealCount){//Checks if the user has healed more than 2 times
            JOptionPane.showMessageDialog(null, "Heals used up!");
            return;
        }
        PlayerAction playerAction = new PlayerAction(battleLogPanel);
        playerAction.heal(player);
        healCount++;
        battleInterface.getScreenPanel().refreshPositions();
        nextTurn();
    }

    //This method is for the gui to call for the player to move forwad
    public void playerMoveForward() {
        PlayerAction playerAction = new PlayerAction(battleLogPanel);
        playerAction.moveForward(enemy, player);
        battleInterface.getScreenPanel().refreshPositions();
        System.out.println(player.getPosition() + " " + enemy.getPosition());
        nextTurn();
    }

    //This method is for the gui to call for the player to move backwards
    public void playerMoveBackward() {
        PlayerAction playerAction = new PlayerAction(battleLogPanel);
        playerAction.moveBackward(enemy, player);
        battleInterface.getScreenPanel().refreshPositions();
        System.out.println(player.getPosition() + " " + enemy.getPosition());
        nextTurn();
    }

    // Handles the enemy turn and updates the GUI
    private void nextTurn() {
        updateGUI();
        // Check if enemy or player is already defeated
        if (enemy.getHealth() <= 0) {
            Victory();
            return;
        } else if (player.getHealth() <= 0) {
            Defeat();
            return;
        }

        // Enemy acts immediately after player
        EnemyAction enemyAction = new EnemyAction(battleLogPanel);
        enemy.Action(player, enemyAction, round);

        updateGUI();
        // Re-check after enemy turn
        if (enemy.getHealth() <= 0) {
            Victory();
            battleWon = true;
        } else if (player.getHealth() <= 0) {
            Defeat();
            battleWon = false;
        }

        // Increment round after both player and enemy actions
        round++;
    }

    // Update health bars in GUI
    private void updateGUI() {
        battleInterface.UpdateHealthBar(
            player.getHealth(),
            player.getMaxHealth(),
            enemy.getHealth(),
            enemy.getMaxHealth()
        );
    }

    // Handles battle victory 
    private void Victory() {
        player.addGold(20);
        player.addExp(100);
        player.addLevel();
        dbOperationAccounts.updatePlayer(player);
        battleInterface.getButtonPanel().DisableButton();
        
        //launch the reward panel
        RewardsPanel rewards = new RewardsPanel(player, true);
        JFrame rewardsFrame = new JFrame("Rewards");
        rewardsFrame.setContentPane(rewards);
        rewardsFrame.setSize(700, 500);
        rewardsFrame.setLocationRelativeTo(null);
        rewardsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rewardsFrame.setUndecorated(true);
        rewardsFrame.setVisible(true);

        rewards.addContinueListener(e -> {
            rewardsFrame.dispose();   // Close rewards frame
            endBattle();              // Now end the battle and go back to main interface
        });
    }


    //Haddles the battle when player is defeated
    private void Defeat() {
        player.removeGold(20);
        dbOperationAccounts.updatePlayer(player);
        battleInterface.getButtonPanel().DisableButton();
        
        //launch the reward panel
        RewardsPanel rewards = new RewardsPanel(player, false);
        JFrame rewardsFrame = new JFrame("Rewards");
        rewardsFrame.setContentPane(rewards);
        rewardsFrame.setSize(700, 500);
        rewardsFrame.setLocationRelativeTo(null);
        rewardsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rewardsFrame.setUndecorated(true);
        rewardsFrame.setVisible(true);

        rewards.addContinueListener(e -> {
            rewardsFrame.dispose();
            endBattle();
        });
    }

    // Ends battle and returns to main interface
    private void endBattle() {
        battleInterface.dispose();
          //Resets player health to max and reset players base damage to the original, and range, entity positions
        player.setHealth(player.getMaxHealth());
        round = 1;
        healCount = 0;
        player.setbaseDmg(originalBaseDamage);
        player.setAttackRange(originalAttackRange);
        player.setPosition(10);
        enemy.setPosition(10);

        new MainInterface(player, player.getPlayerStats());
    }
}
