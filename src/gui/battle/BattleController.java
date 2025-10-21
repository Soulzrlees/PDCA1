/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import databases.DBManager;
import entity.Player;
import entity.Enemy;
import entity.PlayerStats;
import gui.main.MainInterface;
import main.Battle;
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
    
    private Battle battle;
    private Player player;
    private PlayerStats playerStats;
    private Enemy enemy;
    private DBManager dbManagerAccounts;
    private DBOperation dbOperationAccounts;
    private DBInitialiser dbInitialiser;
    
    public BattleController(Player player, Enemy enemy) {
            this.player = player;
            this.enemy = enemy;
            this.battle = new Battle(player, enemy, null);
            this.dbInitialiser = new DBInitialiser();
            this.dbManagerAccounts = new DBManager(dbInitialiser.getAccountsDB_URL(), null, null);
            this.dbOperationAccounts = new DBOperation(dbManagerAccounts);

    }
    
    // public wrappers to be called from GUI buttons
    public void playerAttack() {
        PlayerAction playerAction = new PlayerAction();
        playerAction.attack(battle.getEnemy(), battle.getPlayer(), battle.getRound());
        nextTurn();
    }

    public void playerHeal() {
        PlayerAction playerAction = new PlayerAction();
        playerAction.heal(battle.getPlayer());
        nextTurn();
    }

    public void playerMoveForward() {
        PlayerAction playerAction = new PlayerAction();
        playerAction.moveForward(battle.getEnemy(), battle.getPlayer());
        nextTurn();
    }

    public void playerMoveBackward() {
        PlayerAction playerAction = new PlayerAction();
        playerAction.moveBackward(battle.getEnemy(), battle.getPlayer());
        nextTurn();
    }

    // keep nextTurn logic but make it callable from this class
    private void nextTurn(){
        updateGUI();
        if(battle.getEnemy().getHealth() <= 0){ //If the enemy health is 0 than add gold, add exp and check if level should increase
            player.addGold(20);
            player.addExp(100);
            player.addLevel();
            dbOperationAccounts.updatePlayer(player);
            JOptionPane.showMessageDialog(null, "You defeated the enemy!");
            BattleEnd();
        }
        else if(battle.getPlayer().getHealth() <= 0){ //If the player health is 0 than reduce gold
            player.removeGold(20);
            dbOperationAccounts.updatePlayer(player);
            JOptionPane.showMessageDialog(null, "You died!");
            BattleEnd();
        }
        else{
            battle.getEnemy().Action(battle.getPlayer(), battle.getEnemyAction(), battle.getRound());
            // after enemy action, update GUI and re-check end conditions
            updateGUI();
            if(battle.getEnemy().getHealth() <= 0){
                player.addGold(20);
                player.addExp(100);
                player.addLevel();
                dbOperationAccounts.updatePlayer(player);
                JOptionPane.showMessageDialog(null, "You defeated the enemy!");
                BattleEnd();
            } else if(battle.getPlayer().getHealth() <= 0){
                player.removeGold(20);
                dbOperationAccounts.updatePlayer(player);
                JOptionPane.showMessageDialog(null, "You died!");
                BattleEnd();
            }
        }
    }
    
    private void updateGUI() {
        // TODO: update health bars / logs / positions using healthPanel, screenPanel, logPanel
        // (left as TODO because the panels currently do not expose update APIs)
    }


    private void BattleEnd(){
        new MainInterface(player, player.getPlayerStats());
    }
}
