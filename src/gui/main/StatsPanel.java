/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.main;

import javax.swing.*;
import java.awt.*;
import entity.Player;
import entity.PlayerStats;
/**
 *
 * @author Shawn lee
 */
public class StatsPanel extends JPanel {

    public Player player;
    private PlayerStats playerStats;
    private final JPanel backgroundPanel;
    private final JButton damageButton, healthButton, rangeButton;
    private final JLabel nameLabel, expLabel, levelLabel, goldLabel, classLabel;
    private final JLabel damageLabel, hpLabel, rangeLabel, skillPointsLeft;

    public StatsPanel(JPanel backgroundPanel, Player player, PlayerStats stats) {
        this.playerStats = stats;
        this.player = player;
        this.backgroundPanel = backgroundPanel;
        setLayout(new GridBagLayout()); 
        setBackground(Color.GRAY);

        //Create labels
        nameLabel = createLabel(player.getName(), 0, 0, 2);
        expLabel = createLabel("Exp: " + player.getExp(), 0, 1, 1);
        levelLabel = createLabel("level:" + player.getLevel(), 0, 2, 1);
        goldLabel = createLabel("Class: " + player.getGold(), 0, 3, 1);
        classLabel = createLabel("Class: " + player.getClasses(), 0, 4, 1);
        
        damageLabel = createLabel("BaseDamage: " + player.getbaseDmg() + " [+" + stats.getDamageSkillPoints() + "]",  0, 5, 1);
        hpLabel = createLabel("BaseHealth: " + player.getMaxHealth() + " [+" + stats.getHealthSkillPoints() + "]",  0, 6, 1);
        rangeLabel = createLabel("BaseRange: " + player.getAttackRange() + " [+" + stats.getRangeSkillPoints() + "]",  0, 7, 1);
        skillPointsLeft  = createLabel("Skillpoints Left: " + (player.getskillPoints()), 0, 8, 1);
        
        // Create buttons
        damageButton = createButton("images/SkillPoints.png", 1, 5, 1);
        healthButton = createButton("images/SkillPoints.png", 1, 6, 1);
        rangeButton = createButton("images/SkillPoints.png", 1 , 7, 1);
        
    }
   
     //Setup base specifications of the labels on the stats panel
    private JLabel createLabel(String text, int x, int y, int gridWidth) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 10, 25, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(label, gbc);
        
        return label;
    }
    
    //Setup base specifications of the buttons on the stats panel
    private JButton createButton(String iconPath,  int x, int y, int gridWidth) {
        ImageIcon icon = (iconPath != null) ? new ImageIcon(iconPath) : null;
        JButton button = new JButton(icon);
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(button, gbc);
        return button;
    }
    
    public void updatePlayerStats() {
    // Update basic info
    nameLabel.setText(player.getName());
    expLabel.setText("Exp: " + player.getExp());
    levelLabel.setText("Level: " + player.getLevel());
    goldLabel.setText("Gold: " + player.getGold());
    classLabel.setText("Class: " + player.getClasses());

    // Update stats with skill points from PlayerStats
    damageLabel.setText("BaseDamage: " + player.getbaseDmg() + " [+" + playerStats.getDamageSkillPoints() + "]");
    hpLabel.setText("BaseHealth: " + player.getMaxHealth() + " [+" + playerStats.getHealthSkillPoints() + "]");
    rangeLabel.setText("BaseRange: " + player.getAttackRange() + " [+" + playerStats.getRangeSkillPoints() + "]");

    skillPointsLeft.setText("Skillpoints Left: " + player.getskillPoints());

    // Refresh the panel to show updated values
    revalidate();
    repaint();
}
    
    //Get methods for the button
    public JButton getDamageButton() { return damageButton; }
    public JButton getHealthButton() { return healthButton; }
    public JButton getRangeButton() { return rangeButton; }
    
    
}
