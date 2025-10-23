package gui.battle;

import java.awt.*;
import javax.swing.*;
import entity.Player;
import entity.Enemy;

public class BattleScreenPanel extends JPanel {
    private Image playerDisplay;
    private Image enemyDisplay;
    private Image backgroundImage;
    private final Player player;
    private final Enemy enemy;

    public BattleScreenPanel(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        loadCharacters();
        setOpaque(false);
    }

    private void loadCharacters() {
        backgroundImage = new ImageIcon("images/battle_background.png").getImage();
        playerDisplay = new ImageIcon(player.getPlayerImage(player)).getImage();
        enemyDisplay = new ImageIcon(enemy.getEnemyImage(enemy)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Draw background
        g.drawImage(backgroundImage, 0, 0, width, height, this);

        int position_pixels = width / 20; // Convert width to 20 segments (0-20)
        int imgWidth = 120; //The width of the image

        // Player position
        int playerX = (player.getPosition() * position_pixels) - 50;
        if (playerX < 0) playerX = 0;                     // clamp left
        if (playerX > width - imgWidth) playerX = width - imgWidth; // clamp right

        // Enemy position
        int enemyX = (enemy.getPosition() * position_pixels) + 50;
        if (enemyX < 0) enemyX = 0;                       // clamp left
        if (enemyX > width - imgWidth) enemyX = width - imgWidth; // clamp right

        // Draw entities
        g.drawImage(playerDisplay, playerX, height - 250, imgWidth, 120, this);
        g.drawImage(enemyDisplay, enemyX, height - 250, imgWidth, 120, this);
    }

    // Redraw screen whenever positions change
    public void refreshPositions() {
        repaint();
    }
}