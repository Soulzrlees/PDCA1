/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import entity.Player;
import entity.Enemy;
import main.Battle;

/**
 *
 * @author Shawn lee
 */
public class BattleController {
    
    private Battle battle;
    private BattleHealthBarPanel healthPanel;
    private BattleButtonsPanel buttonsPanel;
    private BattleLogPanel logPanel;
    private BattleInventoryPanel inventoryPanel;
    private BattleScreenPanel screenPanel;
    private Player player;
    private Enemy enemy;
    
    public BattleController(Player player, Enemy enemy, BattleHealthBarPanel healthPanel, BattleButtonsPanel buttonsPanel, 
            BattleLogPanel logPanel, BattleInventoryPanel inventoryPanel, BattleScreenPanel screenPanel){
            this.player = player;
            this.enemy = enemy;
            this.battle = new Battle(player, enemy, null);
            this.healthPanel = healthPanel;
            this.buttonsPanel = buttonsPanel;
            this.logPanel = logPanel;
            this.inventoryPanel = inventoryPanel;
            this.screenPanel = screenPanel;
    }
    
    public void nextTurn(){
        updateGUI();
        if(battle.getEnemy().getHealth() <= 0){
            
        }
        else if(battle.getPlayer().getHealth() <= 0){
            
        }
        else{
            battle.getEnemy().Action(battle.getPlayer(), battle.getEnemyAction(), battle.getRound());
        }
    }
    
    private void updateGUI() {
        
    }
}
