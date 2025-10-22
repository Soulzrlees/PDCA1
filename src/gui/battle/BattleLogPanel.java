/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Shawn lee
 */
public class BattleLogPanel {
    private JPanel logPanel;
    private JTextArea logTextArea;
    private JScrollPane scrollPane;
    
    public void createBattleLogPanel(JFrame frame){
        logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());
        logPanel.setPreferredSize(new Dimension(300, 800));
        logPanel.setBackground(Color.DARK_GRAY);
        
        // Create JTextArea
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setBackground(Color.BLACK);
        logTextArea.setForeground(Color.WHITE);
        
        //Set custom font to the TextArea
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);
            is.close();
            logTextArea.setFont(customFont);
        } catch (Exception e) {
           logTextArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        }
        
        //Create ScrollPane
        scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        logPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(logPanel, BorderLayout.EAST);
    }
    
    public void appendLog(String text) {
        logTextArea.append(text + "\n\n");
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
    }
}
