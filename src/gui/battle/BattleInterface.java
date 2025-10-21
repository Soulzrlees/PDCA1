package gui.battle;

import entity.Enemy;
import entity.Player;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.PlayerStats;

public class BattleInterface{
    private Player player;
    private Enemy enemy;
    private PlayerStats playerStats;
    private BattleController controller;    

    public BattleInterface(Player player, PlayerStats playerStats) {
        this.player = player;
        this.playerStats = playerStats;
    }
    
        public void createBattleInterface(Player player, PlayerStats playerStats) {
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
        BattleScreenPanel battleScreenPanel = new BattleScreenPanel(player, playerStats);
        battleScreenPanel.createBattleScreen(frame, player, playerStats);

        controller = new BattleController(player, enemy);
        // Ensure we have a valid enemy instance before creating the controller        if (this.enemy == null) {
           // Try using a factory if available
        
        if (this.enemy == null) {
           try {
                this.enemy = Enemy.createEnemy(player);
            } catch (NoSuchMethodError | NoClassDefFoundError | Exception ex) {
                this.enemy = new Enemy("Orc", 1, "melee"); // replace with proper constructor if required
            }
        }
        controller = new BattleController(player, this.enemy);
 
        // attach a single BattleInterfaceAction listener (same style as LoginInterface)
        BattleInterfaceAction listener = new BattleInterfaceAction(controller, battleButtonsPanel);
        battleButtonsPanel.getAttackButton().addActionListener(listener);
        battleButtonsPanel.getHealButton().addActionListener(listener);
        battleButtonsPanel.getMoveForwardButton().addActionListener(listener);
        battleButtonsPanel.getMoveBackButton().addActionListener(listener);
    }
    

}
