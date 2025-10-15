/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

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
        setLayout(null); 
        setBackground(Color.GRAY);

        // Create buttons
        damageButton = createButton("images/SkillPoints.png");
        healthButton = createButton("images/SkillPoints.png");
        rangeButton = createButton("images/SkillPoints.png");

       //Create Labels
        nameLabel = createLabel("__________" + player.getName() +"__________");
        expLabel = createLabel("Exp: " + player.getExp());
        levelLabel = createLabel("Level: " + player.getLevel());
        goldLabel = createLabel("Gold: " + player.getGold());
        classLabel = createLabel("Class: " + player.getClasses());

        damageLabel = createLabel("Damage: " + player.getbaseDmg() + "[" + playerStats.getDamageSkillPoints() + "]");
        hpLabel = createLabel("HP: " + player.getMaxHealth() + "[" + playerStats.getHealthSkillPoints() + "]");
        rangeLabel = createLabel("Range: " + player.getAttackRange() + "[" + playerStats.getRangeSkillPoints() + "]");
        skillPointsLeft = createLabel("SkillpointsLeft: " + player. getskillPoints());
        
        add(nameLabel);
        add(expLabel);
        add(levelLabel);
        add(goldLabel);
        add(classLabel);
        add(damageLabel);
        add(hpLabel);
        add(rangeLabel);
        add(skillPointsLeft);
        
        add(damageButton);
        add(healthButton);
        add(rangeButton);

        // Initialize the Position of widgets
        updateBounds();
    }
    
    //Updates where the position of the widgets would be based on the width and height of the users screen
    public void updateBounds() {
        int panelWidth = (int) (backgroundPanel.getWidth() * 0.3);
        int panelHeight = (int) (backgroundPanel.getHeight() * 0.6);
        int x = backgroundPanel.getWidth() - panelWidth - 50;
        int y = backgroundPanel.getHeight() / 5;

        setBounds(x, y, panelWidth, panelHeight);

        int lineHeight = 70; // space between labels

        //set the Position of all widgets on the stats panel
        nameLabel.setBounds(50, 0, panelWidth, 30);
        expLabel.setBounds(50, lineHeight * 1, panelWidth, 30);
        levelLabel.setBounds(50, lineHeight * 2, panelWidth, 30);
        goldLabel.setBounds(50, lineHeight * 3, panelWidth, 30);
        classLabel.setBounds(50, lineHeight * 4, panelWidth, 30);

        damageLabel.setBounds(50, lineHeight * 5, 250, 25);
        hpLabel.setBounds(50, lineHeight * 6, 250, 25);
        rangeLabel.setBounds(50, lineHeight * 7, 250, 25);
        skillPointsLeft.setBounds(50, lineHeight* 8, 400, 25);

        damageButton.setBounds(300, lineHeight * 5, 32, 32);
        healthButton.setBounds(300, lineHeight * 6, 32, 32);
        rangeButton.setBounds(300, lineHeight * 7, 32, 32);
    }
    
     //Setup base specifications of the labels on the stats panel
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        return label;
    }

    //Setup base specifications of the buttons on the stats panel
    private JButton createButton(String iconPath) {
        ImageIcon icon = (iconPath != null) ? new ImageIcon(iconPath) : null;
        JButton button = new JButton(icon);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        return button;
    }
    
    //Updates the label when user interracts with the stats panel
    public void updatePlayerStats() {
        // Update basic info
        nameLabel.setText("__________" + player.getName() + "__________");
        expLabel.setText("Exp: " + player.getExp());
        levelLabel.setText("Level: " + player.getLevel());
        goldLabel.setText("Gold: " + player.getGold());
        classLabel.setText("Class: " + player.getClasses());

        // Update stats with skill points from PlayerStats
        damageLabel.setText("Damage: " + player.getbaseDmg() + "[" + playerStats.getDamageSkillPoints() + "]");
        hpLabel.setText("HP: " + player.getMaxHealth() + "[" + playerStats.getHealthSkillPoints() + "]");
        rangeLabel.setText("Range: " + player.getAttackRange() + "[" + playerStats.getRangeSkillPoints() + "]");

        skillPointsLeft.setText("SkillpointsLeft: " + player.getskillPoints());

        // Refresh the panel to show updated values
        revalidate();
        repaint();
    }
    
    
    //Get methods for the button
    public JButton getdamageButton() { return damageButton; }
    public JButton gethealthButton() { return healthButton; }
    public JButton getRangeButton() { return rangeButton; }
    
    
}
