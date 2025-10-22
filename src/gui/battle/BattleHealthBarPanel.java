package gui.battle;

import entity.Enemy;
import entity.Player;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class BattleHealthBarPanel {
    private JProgressBar playerHealthBar;
    private JProgressBar enemyHealthBar;
    
    private Player player;
    private Enemy enemy;
    
    public BattleHealthBarPanel(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
    }
    
    public void createHealthBarPanel(JFrame frame, Player player, Enemy enemy) {
        JPanel healthBarPanel = new JPanel();
        healthBarPanel.setLayout(new BoxLayout(healthBarPanel, BoxLayout.X_AXIS));
        healthBarPanel.setPreferredSize(new Dimension(800, 100));
        healthBarPanel.setBackground(Color.decode("#1B3C53"));
        frame.add(healthBarPanel, BorderLayout.NORTH);

        healthBarPanel.add(Box.createVerticalGlue());

        // Create Health Bars
        playerHealthBar = new JProgressBar();
        enemyHealthBar = new JProgressBar();

        // Set Health Bar dimensions
        Dimension barSize = new Dimension(350, 25);
        playerHealthBar.setPreferredSize(barSize);
        playerHealthBar.setMaximumSize(barSize);
        enemyHealthBar.setPreferredSize(barSize);
        enemyHealthBar.setMaximumSize(barSize);

        // Set visuals of Health Bars
        createVisuals(playerHealthBar);
        createVisuals(enemyHealthBar);

        // Add health bars to panel
        healthBarPanel.add(Box.createHorizontalGlue());
        healthBarPanel.add(playerHealthBar);
        healthBarPanel.add(Box.createRigidArea(new Dimension(500, 0)));
        healthBarPanel.add(enemyHealthBar);
        healthBarPanel.add(Box.createHorizontalGlue());

        // Set size of Health Bars
        playerHealthBar.setPreferredSize(new Dimension(350, 25));
        playerHealthBar.setMaximumSize(new Dimension(350, 25));
        enemyHealthBar.setPreferredSize(new Dimension(350, 25));
        enemyHealthBar.setMaximumSize(new Dimension(350, 25));
        
        //set the visual health bar value
        updatePlayerHealth(player.getHealth(), player.getMaxHealth());
        updateEnemyHealth(enemy.getHealth(), enemy.getMaxHealth());
    }

    private void createVisuals(JProgressBar healthBar) {
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.decode("#457d14"));
        healthBar.setBackground(Color.RED);
        healthBar.setFocusable(false);
        healthBar.setBorder(BorderFactory.createEmptyBorder());
        healthBar.setOpaque(true);
        
        //Set custom font to the healthbar
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
            is.close();
            healthBar.setFont(customFont);
        } catch (Exception e) {
            healthBar.setFont(new Font("Monospaced", Font.PLAIN, 25));
        }
    }
   
    //Update method to change the visual of the players health bar
    public void updatePlayerHealth(int current, int max) {
        playerHealthBar.setMaximum(max);
        playerHealthBar.setValue(current);
        playerHealthBar.setString("[" + player.getName() + "] " + current + "/" + max);
    }
    
    //Update method to change the visual of the Enemy health bar
    public void updateEnemyHealth(int current, int max) {
        enemyHealthBar.setMaximum(max);
        enemyHealthBar.setValue(current);
        enemyHealthBar.setString("[" + enemy.getName() + "] "  + current + "/" + max);
    }
}
