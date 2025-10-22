package gui.battle;

import java.awt.*;
import javax.swing.*;

import entity.PlayerStats;
import entity.Entity;
import entity.Player;
import entity.Enemy;

public class BattleScreenPanel {
    private Image playerDisplay, enemyDisplay;
    private Player player;
    private PlayerStats playerStats;
    private Enemy enemy;
    
    public BattleScreenPanel(Player player, PlayerStats playerStats) {
        this.player = player;
        this.playerStats = playerStats;
        enemy = Enemy.createEnemy(player);
    }

    public void createBattleScreen(JFrame frame, Player player, PlayerStats playerStats) {
        JPanel battleScreenPanel = new JPanel();
        battleScreenPanel.setPreferredSize(new Dimension(100, 100));
        frame.add(battleScreenPanel, BorderLayout.CENTER);
        createBackground(frame);
    }

    public static void loadCharacters(JFrame frame, Player player, Enemy enemy) {
        String playerImage = player.getPlayerImage(player);
        String enemyImage = enemy.getEnemyImage(enemy);
        Image playerDisplay = new ImageIcon(playerImage).getImage();
        Image EnemyDisplay = new ImageIcon(enemyImage).getImage();
    }

    public void createBackground(JFrame frame) {
        Enemy enemy = Enemy.createEnemy(player);
        loadCharacters(frame, player, enemy);
        class BattlePanel extends JPanel {
            private final Image backgroundImage = new ImageIcon("images/battle_background.png").getImage();
            private final Image playerImage = new ImageIcon(player.getPlayerImage(player)).getImage();
            private final Image enemyImage = new ImageIcon(enemy.getEnemyImage(enemy)).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);
                g.drawImage(playerImage, 0, 100, 40, 100, this);
                g.drawImage(enemyImage, 80, 0, 40, 70, this);
            }
        }
        BattlePanel battledPanel = new BattlePanel();
        battledPanel.setLayout(null);
        frame.add(battledPanel,BorderLayout.CENTER);
    }

}
