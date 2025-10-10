package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleInterface implements ActionListener {


    public void createBattleInterface() {
        JFrame frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);

        // Health Bar Panel --------------------------------------------------
        JPanel healthBarPanel = new JPanel();
        healthBarPanel.setLayout(new BoxLayout(healthBarPanel, BoxLayout.Y_AXIS));
        healthBarPanel.setPreferredSize(new Dimension(800, 100));
        healthBarPanel.setBackground(Color.decode("#1B3C53"));
        frame.add(healthBarPanel, BorderLayout.NORTH);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        healthBarPanel.add(Box.createVerticalGlue());

        JProgressBar playerHealthBar = new JProgressBar();
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setValue(80);
        playerHealthBar.setForeground(Color.RED);
        playerHealthBar.setBackground(Color.WHITE);
        playerHealthBar.setFocusable(false);
        playerHealthBar.setBorder(BorderFactory.createEmptyBorder());
        playerHealthBar.setOpaque(true);
        playerHealthBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerHealthBar.setPreferredSize(new Dimension(400, 25));
        healthBarPanel.add(playerHealthBar);

        healthBarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JProgressBar enemyHealthBar = new JProgressBar();
        enemyHealthBar.setStringPainted(true);
        enemyHealthBar.setValue(65);
        enemyHealthBar.setForeground(Color.RED);
        enemyHealthBar.setBackground(Color.WHITE);
        enemyHealthBar.setFocusable(false);
        enemyHealthBar.setBorder(BorderFactory.createEmptyBorder());
        enemyHealthBar.setOpaque(true);
        enemyHealthBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        enemyHealthBar.setPreferredSize(new Dimension(400, 25));
        healthBarPanel.add(enemyHealthBar);

        healthBarPanel.add(Box.createVerticalGlue());

        playerHealthBar.setPreferredSize(new Dimension(350, 25));
        playerHealthBar.setMaximumSize(new Dimension(350, 25));

        enemyHealthBar.setPreferredSize(new Dimension(350, 25));
        enemyHealthBar.setMaximumSize(new Dimension(350, 25));


        // Moves Panel --------------------------------------------------
        JPanel movePanel = new JPanel();
        movePanel.setPreferredSize(new Dimension(100, 200));
        frame.add(movePanel, BorderLayout.SOUTH);
        movePanel.setBackground(Color.decode("#1B3C53"));

        movePanel.setLayout(new GridLayout(2, 2));
        JButton[] buttons = {
                new JButton("Attack"),
                new JButton("Heal"),
                new JButton("Move Backwards"),
                new JButton("Move Forwards")
        };

        for (JButton button : buttons) {
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            wrapper.add(button, BorderLayout.CENTER);
            button.setBackground(Color.WHITE);
            button.setFocusable(false);
            movePanel.add(button);
        }



        // Inventory Panel --------------------------------------------------
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setPreferredSize(new Dimension(100, 100));
        frame.add(inventoryPanel, BorderLayout.WEST);
        inventoryPanel.setBackground(Color.decode("#1B3C53"));


        // Battle Log Panel --------------------------------------------------
        JPanel battleLogPanel = new JPanel();
        battleLogPanel.setPreferredSize(new Dimension(250, 100));
        frame.add(battleLogPanel, BorderLayout.EAST);
        battleLogPanel.setBackground(Color.decode("#1B3C53"));


        // Main Battle Panel --------------------------------------------------
        JPanel battlePanel = new JPanel();
        battlePanel.setPreferredSize(new Dimension(100, 100));
        frame.add(battlePanel, BorderLayout.CENTER);
        battlePanel.setBackground(Color.YELLOW);
        createBackground(frame);
    }

    //Make the background of the main interface resizable with the window size
    public void createBackground(JFrame frame) {
        class BattledPanel extends JPanel {
            private final Image backgroundImage = new ImageIcon("images/battle_background.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Draw background
                g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);

            }
        }

        BattledPanel battledPanel = new BattledPanel();
        battledPanel.setLayout(null);
        frame.add(battledPanel, BorderLayout.CENTER
);
    }
    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
