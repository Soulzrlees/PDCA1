package gui.battle;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class BattleButtonsPanel {
    // expose buttons so callers can attach listeners
    private JButton attackButton;
    private JButton healButton;
    private JButton moveBackButton;
    private JButton moveForwardButton;

    public void createBattleButtonsPanel(JFrame frame) {
        JPanel battleButtonsPanel = new JPanel();
        battleButtonsPanel.setLayout(new GridLayout(1, 4));

        attackButton = createButton("Attack");
        healButton = createButton("Heal");
        moveBackButton = createButton("Move Backwards");
        moveForwardButton = createButton("Move Forwards");

        // set sizes on each button container similar to previous behavior
        battleButtonsPanel.add(createCenteredPanel(attackButton));
        battleButtonsPanel.add(createCenteredPanel(healButton));
        battleButtonsPanel.add(createCenteredPanel(moveBackButton));
        battleButtonsPanel.add(createCenteredPanel(moveForwardButton));

        frame.add(battleButtonsPanel, BorderLayout.SOUTH);
    }

    private JPanel createCenteredPanel(JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        button.setPreferredSize(new Dimension(450, 150));
        panel.add(button);
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        return panel;
    }

    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.GRAY);
        button.setFocusable(false);
        
        //Set custom font to the button text
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
            is.close();
            button.setFont(customFont);
        } catch (Exception e) {
           button.setFont(new Font("Monospaced", Font.PLAIN, 20));
        }
        return button;
    }

    // getters so the caller (BattleInterface) can attach listeners
    public JButton getAttackButton() { return attackButton; }
    public JButton getHealButton() { return healButton; }
    public JButton getMoveBackButton() { return moveBackButton; }
    public JButton getMoveForwardButton() { return moveForwardButton; }
}