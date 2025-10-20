/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fatehbhular
 */
public class BattleInventoryPanel {
    public void createBattleInventoryPanel(JFrame frame){
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new FlowLayout());
        inventoryPanel.setPreferredSize(new Dimension(100, 800));
        inventoryPanel.setBackground(Color.DARK_GRAY);
        frame.add(inventoryPanel, BorderLayout.WEST);
    }
}
