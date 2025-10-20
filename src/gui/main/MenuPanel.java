/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.main;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 *
 * @author Shawn lee
 */
public class MenuPanel extends JPanel {

    private final JButton inventoryButton, battleButton, statsButton, exitButton;

    public MenuPanel(MainInterface mainInterface) {
        setLayout(new GridLayout(4, 1, 20, 20));
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(300, 0));

        // Create buttons on menu panel
        battleButton = createButton("Battle", "images/battle_icon.png");
        inventoryButton = createButton("Inventory", "images/inventory_icon.png");
        statsButton = createButton("Stats", "images/skill_points_icon.png");
        exitButton = createButton("Exit", null);

        // Attach external listener
        MainInterfaceAction listener = new MainInterfaceAction(this, mainInterface);
        battleButton.addActionListener(listener);
        inventoryButton.addActionListener(listener);
        statsButton.addActionListener(listener);
        exitButton.addActionListener(listener);
        
        //Add the buttons to the panel
        add(battleButton);
        add(inventoryButton);
        add(statsButton);
        add(exitButton);
    }

    //Setup based specifications of the buttons
    private JButton createButton(String text, String iconPath) {
        ImageIcon icon = (iconPath != null) ? new ImageIcon(iconPath) : null;
        JButton button = new JButton(text, icon);
        button.setFocusable(false); 
        button.setOpaque(true);
        button.setBackground(Color.GRAY);

        //Set custom font to the text of the button
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
            is.close();
            button.setFont(customFont);
        } catch (Exception e) {
            button.setFont(new Font("Monospaced", Font.PLAIN, 25));
        }

        return button;
    }

    // Getters for action listener
    public JButton getInventoryButton() { return inventoryButton; }
    public JButton getBattleButton() { return battleButton; }
    public JButton getStatsButton() { return statsButton; }
    public JButton getExitButton() { return exitButton; }
}
