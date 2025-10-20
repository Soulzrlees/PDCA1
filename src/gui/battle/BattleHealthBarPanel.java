package gui.battle;

import javax.swing.*;
import java.awt.*;

public class BattleHealthBarPanel {
    public void createHealthBarPanel(JFrame frame) {
        JPanel healthBarPanel = new JPanel();
        healthBarPanel.setLayout(new BoxLayout(healthBarPanel, BoxLayout.X_AXIS));
        healthBarPanel.setPreferredSize(new Dimension(800, 100));
        healthBarPanel.setBackground(Color.decode("#1B3C53"));
        frame.add(healthBarPanel, BorderLayout.NORTH);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        healthBarPanel.add(Box.createVerticalGlue());

        // Create Health Bars
        JProgressBar playerHealthBar = new JProgressBar();
        JProgressBar enemyHealthBar = new JProgressBar();

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
    }

    private void createVisuals(JProgressBar healthBar) {
        healthBar.setStringPainted(true);
        healthBar.setValue(90);
        healthBar.setForeground(Color.RED);
        healthBar.setBackground(Color.WHITE);
        healthBar.setFocusable(false);
        healthBar.setBorder(BorderFactory.createEmptyBorder());
        healthBar.setOpaque(true);
    }
}
