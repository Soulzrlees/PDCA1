package gui;

import javax.swing.*;
import java.awt.*;

public class BattleButtonsPanel {
    public void createBattleButtonsPanel(JFrame frame) {
        JPanel battleButtonsPanel = new JPanel();
        battleButtonsPanel.setLayout(new GridLayout(1, 4));

        battleButtonsPanel.add(createCenteredPanel("Attack"));
        battleButtonsPanel.add(createCenteredPanel("Heal"));
        battleButtonsPanel.add(createCenteredPanel("Move Backwards"));
        battleButtonsPanel.add(createCenteredPanel("Move Forwards"));

        frame.add(battleButtonsPanel, BorderLayout.SOUTH);
    }

    private JPanel createCenteredPanel(String buttonText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = createButton(buttonText);
        button.setPreferredSize(new Dimension(360, 150));
        panel.add(button);
        panel.setOpaque(true);

        return panel;
    }

    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.BLACK);
        button.setFocusable(false);
        button.setForeground(Color.WHITE);

        return button;
    }
}
