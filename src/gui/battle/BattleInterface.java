package gui.battle;

import entity.Enemy;
import entity.Player;


import javax.swing.*;
import java.awt.*;
import entity.PlayerStats;

public class BattleInterface{
    private Player player;
    private Enemy enemy;
    private PlayerStats playerStats;
    private BattleController controller;    
    private JFrame frame;
    private BattleHealthBarPanel battleHealthBarPanel;
    private BattleButtonsPanel battleButtonsPanel;
    private BattleInventoryPanel battleinventoryPanel ;
    private BattleScreenPanel battleScreenPanel;
    private BattleLogPanel battleLogPanel;

    public BattleInterface(Player player, PlayerStats playerStats) {
        this.player = player;
        this.playerStats = playerStats;
    }
    
    public void createBattleInterface(Player player, PlayerStats playerStats) {
        frame = new JFrame("Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
        
        // Create enemy
        enemy = Enemy.createEnemy(player);

        // Battle Log Panel --------------------------------------------------
        battleLogPanel = new BattleLogPanel();
        battleLogPanel.createBattleLogPanel(frame);
        
        // Create Battle Controller
       controller = new BattleController(player, enemy, this);

        // Health Bar Panel --------------------------------------------------
        battleHealthBarPanel = new BattleHealthBarPanel(player, enemy);
        battleHealthBarPanel.createHealthBarPanel(frame, player, enemy);

        // Button Panel --------------------------------------------------
        battleButtonsPanel = new BattleButtonsPanel();
        battleButtonsPanel.createBattleButtonsPanel(frame);

        // Inventory Panel --------------------------------------------------
        battleinventoryPanel = new BattleInventoryPanel();
        battleinventoryPanel.createBattleInventoryPanel(frame);

        // Main Battle Panel --------------------------------------------------
        battleScreenPanel = new BattleScreenPanel(player, enemy);
        frame.add(battleScreenPanel, BorderLayout.CENTER);

        // attach a single BattleInterfaceAction listener (same style as LoginInterface)
        BattleInterfaceAction listener = new BattleInterfaceAction(controller, battleButtonsPanel);
        battleButtonsPanel.getAttackButton().addActionListener(listener);
        battleButtonsPanel.getHealButton().addActionListener(listener);
        battleButtonsPanel.getMoveForwardButton().addActionListener(listener);
        battleButtonsPanel.getMoveBackButton().addActionListener(listener);
        }

    //Remove the BattleInferface frame
    public void dispose() {
        frame.dispose();
    }
    
    public void UpdateHealthBar(int playerCurrent, int playerMax, int enemyCurrent, int enemyMax){
        battleHealthBarPanel.updatePlayerHealth(playerCurrent, playerMax);
        battleHealthBarPanel.updateEnemyHealth(enemyCurrent, enemyMax);
    }
    
    //Get method for the log 
    public BattleLogPanel getLogPanel(){
        return battleLogPanel;
    }
    
    public BattleScreenPanel getScreenPanel(){
        return battleScreenPanel;
    }
}
