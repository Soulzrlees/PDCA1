/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import entity.Player;
import entity.PlayerStats;
import javax.swing.*;
import java.awt.*;
import javax.swing.JProgressBar;

/**
 *
 * @author fatehbhular
 */
public class RewardsPanel extends JPanel{
    public JPanel rewardsPanel;
    private final Player player;
    private int experienceGained;
    private int goldGained;
    private int goldLost;
    private boolean playerWon;
    private Image backgroundImage;
    private JLabel titleLabel, experienceLabel, goldLabel, levelLabel;
    private JProgressBar experienceBar;
    
    public RewardsPanel(Player player, boolean playerWon) {
        this.player = player;
        this.playerWon = playerWon;
        
        calculateRewards();
        createPanel();
    }
    
    private void calculateRewards() {
        if (playerWon) {
            experienceGained = 100;
            goldGained = 10;
            goldLost = 0;
        } else {
            experienceGained = 0;
            goldGained = 0;
            goldLost = 20;
        }
    }
    
    private JLabel createLabel(String text, int x, int y, int width) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, 30);
        return label;
    }
    
    private void createPanel() {
        backgroundImage = new ImageIcon("images/rewardsBackground.png").getImage();
        String titleString = playerWon ? "Victory!!!" : "Defeat...";
        
        titleLabel = createLabel(titleString, 200, 80, 300);
        experienceLabel = createLabel("Experience gained: " + experienceGained, 200, 150, 400);
        goldLabel = createLabel(playerWon ? "Gold gained: " + goldGained : "Gold lost: " + goldLost, 200, 190, 400);
        levelLabel = createLabel("Current Level: " + player.getLevel(), 200, 230, 400);
        
        experienceBar = new JProgressBar(0, player.getExperienceNeeded());
        experienceBar.setValue(player.getExp());
        experienceBar.setStringPainted(true);
        experienceBar.setBounds(200, 300, 300, 25);
        experienceBar.setForeground(Color.ORANGE);
        
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(250, 330, 200, 40);
        continueButton.setFocusPainted(false);
        
        add(titleLabel);
        add(experienceLabel);
        add(goldLabel);
        add(levelLabel);
        add(experienceBar);
        add(continueButton);
        
        setLayout(null);
    }
    
    public void addContinueListener(java.awt.event.ActionListener listener) {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton button && "Continue".equals(button.getText())) {
                button.addActionListener(listener);
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int width = getWidth();
        int height = getHeight();
        
        g.drawImage(backgroundImage, 0, 0, width, height, this);
    }
}
