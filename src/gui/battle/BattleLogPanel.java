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
 * @author Shawn lee
 */
public class BattleLogPanel {
        public void createBattleLogPanel(JFrame frame){
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new FlowLayout());
        logPanel.setPreferredSize(new Dimension(300, 800));
        logPanel.setBackground(Color.DARK_GRAY);
        frame.add(logPanel, BorderLayout.EAST);
    }
}
