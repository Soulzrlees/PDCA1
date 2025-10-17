/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
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
        // Debug: show that this method is being triggered, and from where

        if(player.getskillPoints() > 0){
            if (e.getSource().equals(statsPanel.getDamageButton())) {
                playerStats.setDamageSkillPoints(playerStats.getDamageSkillPoints() + 1);
                player.removeSkillPoints(1);
            } 
            if(e.getSource().equals(statsPanel.getHealthButton())){
                playerStats.setHealthSkillPoints(playerStats.getHealthSkillPoints() + 1);
                player.removeSkillPoints(1);
            }
            if(e.getSource().equals(statsPanel.getRangeButton())){
                playerStats.setRangeSkillPoints(playerStats.getRangeSkillPoints() + 1);
                player.removeSkillPoints(1);
            }
        }
        dbOperationStats.updatePlayerStats(playerStats);
        statsPanel.updatePlayerStats() ;
    }
}
