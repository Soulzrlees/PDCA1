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
        // Set text alignment for the button
        int horizontalPosition;
        if (rightAlign) {
            horizontalPosition = JButton.RIGHT;
        } 
        else {
            horizontalPosition = JButton.CENTER;
        }
        button.setHorizontalTextPosition(horizontalPosition);
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
        
        //Paint the Background panel and character onto the Background Panel
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);
            //Gets the relative window size and determines the ratio
            int charWidth = panelWidth / 4;
            int charHeight = panelHeight / 2;
            int x = (panelWidth - charWidth) / 2;
            int y = (panelHeight - charHeight) / 2 + panelHeight / 10 + yOffset;

            g.drawImage(characterImage, x, y, charWidth, charHeight, this);
        }
        
        //This method is used for the speed of the animation of the character
        private void updateBounce() {
            yOffset += dy;
            if (yOffset > maxBounce || yOffset < -maxBounce) dy = -dy;
            repaint();
        }
    }
    
    private void createBackground() {
        backgroundPanel = new BackgroundPanel(); 
        backgroundPanel.setLayout(null); 
        frame.add(backgroundPanel, BorderLayout.CENTER);
    }

    
private void StatsPanel() {
    if (skillPanel == null) {
        skillPanel = new JPanel(new FlowLayout());
        skillPanel.setBackground(new Color(100, 200, 100, 180));

        // Compute position and size relative to backgroundPanel
        int panelWidth = (int) (backgroundPanel.getWidth() * 0.3);   // 40% of width
        int panelHeight = (int) (backgroundPanel.getHeight() * 0.6); // 60% of height
        int x = backgroundPanel.getWidth() - panelWidth - 50;        // 50 px padding from right
        int y = backgroundPanel.getHeight() / 5;                     // 20% from top

        skillPanel.setBounds(x, y, panelWidth, panelHeight);
        skillPanel.setVisible(true);
        backgroundPanel.add(skillPanel);
        backgroundPanel.repaint();
        return;
    }

    // Toggle visibility
    skillPanel.setVisible(!skillPanel.isVisible());
    backgroundPanel.repaint();
}


    // ===== Action Listener =====
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inventoryButton) {
        } else if (e.getSource() == battleButton) {
            new BattleInterface().createBattleInterface();
        }
        else if (e.getSource() == statsButton) {
            StatsPanel(); // toggle on and off the stats display
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
        frame.setResizable(false);
        frame.setUndecorated(true);
        
        
        createMenuBar();
        createBackground();
        frame.setVisible(true);

    }
}

