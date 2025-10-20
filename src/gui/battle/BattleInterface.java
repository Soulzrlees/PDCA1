package gui.battle;

import entity.Player;

import javax.swing.*;
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
        BattleInventoryPanel inventoryPanel = new BattleInventoryPanel();
        inventoryPanel.createBattleInventoryPanel(frame);

        // Battle Log Panel --------------------------------------------------
        BattleLogPanel logPanel = new BattleLogPanel();
        logPanel.createBattleLogPanel(frame);


        // Main Battle Panel --------------------------------------------------
        BattleScreenPanel battleScreenPanel = new BattleScreenPanel();
        battleScreenPanel.createBattleScreen(frame);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
