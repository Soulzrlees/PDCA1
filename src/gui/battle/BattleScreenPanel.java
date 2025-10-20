package gui.battle;

import java.awt.*;
import javax.swing.*;

import entity.PlayerStats;
import main.Battle;
import entity.Entity;
import entity.Player;
import entity.Enemy;

public class BattleScreenPanel {
    Image playerDisplay, EnemyDisplay;

    public void createBattleScreen(JFrame frame) {
        JPanel battleScreenPanel = new JPanel();
        battleScreenPanel.setPreferredSize(new Dimension(100, 100));
        frame.add(battleScreenPanel, BorderLayout.CENTER);
        createBackground(frame);
        
        //loadCharacters(frame);

    }

    public void loadCharacters(JFrame frame, Player player, Enemy enemy) {
        String playerImage = player.getPlayerImage(player);
        String enemyImage = enemy.getEnemyImage(enemy);
        Image playerDisplay = new ImageIcon(playerImage).getImage();
        Image EnemyDisplay = new ImageIcon(enemyImage).getImage();
    }

    public void createBackground(JFrame frame) {
        class BattledPanel extends JPanel {
            private final Image backgroundImage = new ImageIcon("images/battle_background.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);
                g.drawImage(playerDisplay, 0, 0, panelWidth / 4, panelHeight / 2, this);
                g.drawImage(EnemyDisplay, 0, 0, panelWidth / 4, panelHeight / 2, this);
            }
        }
        BattledPanel battledPanel = new BattledPanel();
        battledPanel.setLayout(null);
        frame.add(battledPanel,BorderLayout.CENTER);
    }
}
