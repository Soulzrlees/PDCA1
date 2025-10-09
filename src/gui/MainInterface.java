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

public class MainInterface implements ActionListener {
    JFrame frame = new JFrame();
    JButton inventory_button;
    JButton battle_button;
    JButton stats_button;
    JButton exit_button;

    private JButton MenuButtonDetails(String text, String iconPath, boolean rightAlign, String color) {
        ImageIcon icon = null;
        if (iconPath != null) {
            icon = new ImageIcon(iconPath);
        }
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(200, 100));
        button.setHorizontalTextPosition(rightAlign ? JButton.RIGHT : JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.addActionListener(this);
        switch(color){
            case "red":
                button.setBackground(Color.RED);
                break;

            case "green":
                button.setBackground(Color.GREEN);
                break;
        }
        return button;
    }


    public void createMainInterface() {

        frame.setTitle("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout(10, 10));
        frame.setVisible(true);

        createMenuBar(frame);
        createBackground(frame);

    }

    public void createMenuBar(JFrame frame){
        JPanel OptionBar_panel = new JPanel();
        OptionBar_panel.setPreferredSize(new Dimension(300, 100));
        OptionBar_panel.setBackground(Color.decode("#E0D9D9"));
        frame.add(OptionBar_panel, BorderLayout.WEST);
        OptionBar_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));

        battle_button = MenuButtonDetails("Battle", "images/battle_icon.png", true, "green");
        inventory_button = MenuButtonDetails("Inventory", "images/inventory_icon.png", true, "green");
        stats_button = MenuButtonDetails("Stats", "images/skill_points_icon.png", true, "green");
        exit_button = MenuButtonDetails("Exit", null, false, "red");

        OptionBar_panel.add(battle_button);
        OptionBar_panel.add(inventory_button);
        OptionBar_panel.add(stats_button);
        OptionBar_panel.add(exit_button);
    }


    public void createBackground(JFrame frame){
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("images/MainBackground.gif").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw image to fill entire panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        frame.add(backgroundPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == inventory_button){
            System.out.println("Inventory clicked!");
        } else if(e.getSource() == battle_button){
            System.out.println("Battle clicked!");
        } else if(e.getSource() == stats_button){
            System.out.println("Skill Points clicked!");
        } else if(e.getSource() == exit_button){
            System.exit(0);
        }
    }


}

