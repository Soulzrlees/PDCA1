/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author fatehbhular
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import javax.swing.border.EmptyBorder;

public class MainInterface implements ActionListener {

    // Frame and buttons
    private final JFrame frame = new JFrame();
    private JButton inventoryButton, battleButton, statsButton, exitButton;

    // Constants
    private JPanel skillPanel;
    private BackgroundPanel backgroundPanel;
    private static final Color MENU_BG_COLOR = Color.decode("#2D4F2B");
    private static final Color BUTTON_COLOR = Color.decode("#ACD860");
    private static final int BUTTON_FONT_SIZE = 25;
    private static final Dimension MENU_SIZE = new Dimension(300, 0);
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 50;

    // ===== Button Creation =====
    private JButton createMenuButton(String text, String iconPath, boolean rightAlign) {
        ImageIcon icon = null;
        if (iconPath != null) {
            icon = new ImageIcon(iconPath);
        }
        JButton button = new JButton(text, icon);

        // Set custom font
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) BUTTON_FONT_SIZE);
            is.close();
            button.setFont(customFont);
        } catch (Exception e) {
            button.setFont(new Font("Monospaced", Font.PLAIN, BUTTON_FONT_SIZE));
        }

        button.setFocusable(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setHorizontalTextPosition(rightAlign ? JButton.RIGHT : JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.addActionListener(this);
        button.setBackground(BUTTON_COLOR);

        return button;
    }

    // ===== Menu Panel =====
    private void createMenuBar() {
        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        menuPanel.setBackground(MENU_BG_COLOR);
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        menuPanel.setPreferredSize(MENU_SIZE);

        battleButton = createMenuButton("Battle", "images/battle_icon.png", true);
        inventoryButton = createMenuButton("Inventory", "images/inventory_icon.png", true);
        statsButton = createMenuButton("Stats", "images/skill_points_icon.png", true);
        exitButton = createMenuButton("Exit", null, false);

        menuPanel.add(battleButton);
        menuPanel.add(inventoryButton);
        menuPanel.add(statsButton);
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.WEST);
    }

    // ===== Background Panel with Bouncing Character =====
    private class BackgroundPanel extends JPanel {
        private final Image backgroundImage = new ImageIcon("images/MainBackground.gif").getImage();
        private final Image characterImage = new ImageIcon("images/Knight_player.png").getImage();
        private int yOffset = 0;
        private int dy = 2;
        private final int maxBounce = 20;

        public BackgroundPanel() {
            Timer timer = new Timer(30, e -> updateBounce());
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);

            int charWidth = panelWidth / 4;
            int charHeight = panelHeight / 2;
            int x = (panelWidth - charWidth) / 2;
            int y = (panelHeight - charHeight) / 2 + panelHeight / 10 + yOffset;

            g.drawImage(characterImage, x, y, charWidth, charHeight, this);
        }

        private void updateBounce() {
            yOffset += dy;
            if (yOffset > maxBounce || yOffset < -maxBounce) dy = -dy;
            repaint();
        }
    }

    private void createBackground() {
        backgroundPanel = new BackgroundPanel(); // assign to field
        backgroundPanel.setLayout(null); // for custom overlays
        frame.add(backgroundPanel, BorderLayout.CENTER);
    }

    // ===== Stats Panel Overlay =====
    private void createStatsPanel(JPanel backgroundPanel) {
        JPanel skillPanel = new JPanel(new FlowLayout());
        skillPanel.setBackground(new Color(100, 200, 100, 180));
        skillPanel.setBounds(750, 200, 450, 500); // x, y, width, height
        backgroundPanel.add(skillPanel);
        backgroundPanel.repaint();
    }

    private void toggleStatsPanel() {
        if (skillPanel == null) {
            // First time creation
            skillPanel = new JPanel(new FlowLayout());
            skillPanel.setBackground(new Color(100, 200, 100, 180));
            skillPanel.setBounds(750, 200, 450, 500); // x, y, width, height
            backgroundPanel.add(skillPanel);
        }

        // Toggle visibility
        skillPanel.setVisible(!skillPanel.isVisible());
        backgroundPanel.repaint();
    }

    // ===== Action Listener =====
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inventoryButton) {
            System.out.println("Inventory clicked!");
        } else if (e.getSource() == battleButton) {
            System.out.println("Battle clicked!");
            new BattleInterface().createBattleInterface();
        }
        else if (e.getSource() == statsButton) {
            toggleStatsPanel(); // toggle on/off
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }



    // ===== Main Interface =====
    public void createMainInterface() {
        frame.setTitle("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout(0, 0));
        frame.setVisible(true);

        createMenuBar();
        createBackground();

    }
}

