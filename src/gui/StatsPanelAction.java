/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Player;
import entity.PlayerStats;

/**
 *
 * @author Shawn lee
 */
public class StatsPanelAction implements ActionListener {

    private final StatsPanel statsPanel;
    private Player player;
    private PlayerStats playerStats;

    public StatsPanelAction(StatsPanel statsPanel, Player player, PlayerStats playerStats) {
        this.statsPanel = statsPanel;
        this.player = player;
        this.playerStats = playerStats;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (player.getskillPoints() > 0) {  // ✅ Only spend if available
            if (source == statsPanel.getdamageButton()) {
                playerStats.setDamageSkillPoints(playerStats.getDamageSkillPoints() + 1);
                player.removeSkillPoints(1);
            } 
            else if (source == statsPanel.gethealthButton()) {
                playerStats.setHealthSkillPoints(playerStats.getHealthSkillPoints() + 1);
                player.removeSkillPoints(1);
            } 
            else if (source == statsPanel.getRangeButton()) {
                playerStats.setRangeSkillPoints(playerStats.getRangeSkillPoints() + 1);
                player.removeSkillPoints(1);
            }
        } else {
            System.out.println("No skill points left!");
        }

        // ✅ Refresh display
        statsPanel.updatePlayerStats();
    }
}
