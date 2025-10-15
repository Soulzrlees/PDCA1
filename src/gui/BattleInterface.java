package gui;

import entity.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleInterface implements ActionListener {

    public void createBattleInterface() {
        JFrame frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);

        // Health Bar Panel --------------------------------------------------
        BattleHealthBarPanel battleHealthBarPanel = new BattleHealthBarPanel();
        battleHealthBarPanel.createHealthBarPanel(frame);

        // Moves Panel --------------------------------------------------
        BattleButtonsPanel battleButtonsPanel = new BattleButtonsPanel();
        battleButtonsPanel.createBattleButtonsPanel(frame);



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
        BattleScreenPanel battleScreenPanel = new BattleScreenPanel();
        battleScreenPanel.createBattleScreen(frame);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
