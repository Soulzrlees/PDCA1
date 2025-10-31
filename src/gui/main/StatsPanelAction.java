/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Player;
import entity.PlayerStats;

import databases.DBInitialiser;
import databases.DBManager;
import databases.DBOperation;
/**
 *
 * @author Shawn lee
 */
public class StatsPanelAction implements ActionListener {

    private final StatsPanel statsPanel;
    private Player player;
    private PlayerStats playerStats;
    private DBManager dbManagerStats;
    private DBOperation dbOperationStats;
    private DBInitialiser dbInitialiser;

    public StatsPanelAction(StatsPanel statsPanel, Player player, PlayerStats playerStats) {
        this.statsPanel = statsPanel;
        this.player = player;
        this.playerStats = playerStats;
        
        this.dbInitialiser = new DBInitialiser();
        this.dbManagerStats = new DBManager(dbInitialiser.getStatsDB_URL(), null, null);
        this.dbOperationStats = new DBOperation(dbManagerStats);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     
        if(player.getskillPoints() > 0){ //Check to see if the player has skill points available to spend
            if (e.getSource().equals(statsPanel.getDamageButton())) { //If this button is clicked the DamageSkillPoint variable would increase and remove a generic skillpoint 
                player.skillpointIncreaseDamage();
                playerStats.setDamageSkillPoints(playerStats.getDamageSkillPoints() + 1);
            } 
            if(e.getSource().equals(statsPanel.getHealthButton())){ //If this button is clicked the HealthSkillPoint variable would increase and remove a generic skillpoint 
                player.skillpointIncreaseHealth();
                player.setHealth(player.getMaxHealth());
                playerStats.setHealthSkillPoints(playerStats.getHealthSkillPoints() + 1);
            }
            if(e.getSource().equals(statsPanel.getRangeButton())){ //If this button is clicked the RangeSkillPoint variable would increase and remove a generic skillpoint 
                player.skillpointIncreaseRange();
                if(player.getAttackRange() < 8){
                    playerStats.setRangeSkillPoints(playerStats.getRangeSkillPoints() + 1);
                }
            }
            //Update the playerStats on the database
            //Refresh the stats panel text
            dbOperationStats.updatePlayerStats(playerStats);
            statsPanel.updatePlayerStats() ;
        }
    }
}
