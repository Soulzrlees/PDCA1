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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entity.Potion;
import collections.LinkedList;
import collections.Node;
import entity.Player;
import java.util.Random;


/**
 *
 * @author fatehbhular
 */
public class BattleInventoryPanel {
    // Add player field so add buffs
    private Player player;
    
    public BattleInventoryPanel (Player player) {
        this.player = player;
    }
    
    // Creates the visuals of the inventory
    public void createBattleInventoryPanel(JFrame frame){
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new FlowLayout());
        inventoryPanel.setPreferredSize(new Dimension(100, 800));
        inventoryPanel.setBackground(Color.DARK_GRAY);
        
        LinkedList<Potion> inventory = generateInventory();
        displayInventory(inventoryPanel, inventory);
        
        frame.add(inventoryPanel, BorderLayout.WEST);
    }
    // Displays the inventory
    public void displayInventory(JPanel inventoryPanel, LinkedList<Potion> inventory) {
        Node<Potion> current = inventory.head;

        
        while (current != null) { // while loop to create and add all inventory items onto panel
            Potion potion = current.data;
            
            // Sets the image of each potion (inventory item)
            ImageIcon originalIcon = new ImageIcon(getPotionImage(potion));
            java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            // Sets the tooltip (when you hover you can see the buffs)
            JLabel potionLabel = new JLabel(icon);
            potionLabel.setToolTipText(potion.toString());
            potionLabel.setPreferredSize(new Dimension(100, 100));
            // Adds the mous listener so you can click on the potion and use it
            potionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    // Apply potion effect
                    potion.usePotion(player);

                    // Show message dialog
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        player.getName() + " used " + potion.getName() + "!\n" +
                        "Type: " + potion.getType() + "\nBuff: +" + potion.getstatsBuff(),
                        "Potion Used",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Remove the potion from inventory visually
                    potionLabel.setVisible(false);
                }
            });
            
            inventoryPanel.add(potionLabel); // add the potion visual to inventory panel
            
            current = current.next;
        }
        
    }
    // Helper get method to get the type of potion's image source
    private String getPotionImage(Potion potion) {
        switch (potion.getType()) {
            case Strength:
                return "images/damage_potion.png";
            case Range:
                return "images/range_potion.png";
            default:
                return "images/health_potion.png";
        }
    }
    // Initialises the random inventory for battle
    public LinkedList<Potion> generateInventory() {
        LinkedList<Potion> inventory = new LinkedList<>();
        Random rand = new Random();
        int numOfItems = rand.nextInt(3) + 1;
        
        for (int i = 0; i < numOfItems; i++) {
            Potion.potionType type = rand.nextBoolean() ? Potion.potionType.Strength : Potion.potionType.Range;
            
            int buff = rand.nextInt(2) + 1;
            String name = type == Potion.potionType.Strength ? "Strength Potion" : "Range Potion";
            
            Potion potion = new Potion(name, type, buff);
            
            inventory.addTail(potion);
            
        }
        
        return inventory;
    }
}
