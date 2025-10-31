package gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import entity.Player;
import entity.PlayerStats;

public class MainInterface {

    private final JFrame frame;
    private BackgroundPanel backgroundPanel;    
    private StatsPanel statsPanel;
    
    public final Player player;
    public final PlayerStats playerStats;

    // Creates the main interface when this object is created
    public MainInterface(Player player, PlayerStats playerStats) {
        frame = new JFrame("RPG Game");
        createMainInterface(player, playerStats);
        this.player = player;
        this.playerStats = playerStats;
    }

    //Setup of the MainInterface
    private void createMainInterface(Player player, PlayerStats playerstats) {
        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setUndecorated(true);

        //Creates the sidebar with button options
        MenuPanel menuPanel = new MenuPanel(this);
        frame.add(menuPanel, BorderLayout.WEST);
        
        //Switch the character image displayed on the main interface depending on the users class
        String Image_Character;
        if(Objects.equals(player.getClasses(), "melee")){
            Image_Character = "images/Knight_player.png";
        }
        else if(Objects.equals(player.getClasses(), "ranger")){
            Image_Character = "images/Ranger_player.png";
        }
        else{
             Image_Character = "images/Mage_player.png";
        }
        // Background panel
        backgroundPanel = new BackgroundPanel(
                "images/MainBackground.gif",
                Image_Character,
                "images/BTRealm_logo.png"
        );
        backgroundPanel.setLayout(new BorderLayout());
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Stats panel
        statsPanel = new StatsPanel(backgroundPanel, player, playerstats);
        statsPanel.setVisible(false);
        statsPanel.setBackground(new Color(110, 110, 110, 150));
        backgroundPanel.add(statsPanel, BorderLayout.EAST);
        
        StatsPanelAction statsListener = new StatsPanelAction(statsPanel, player, playerstats);
        statsPanel.getDamageButton().addActionListener(statsListener);
        statsPanel.getHealthButton().addActionListener(statsListener);
        statsPanel.getRangeButton().addActionListener(statsListener);

        frame.setVisible(true);
    }
    
    // Gets the player from the main interface
    public Player getPlayer() {
        return player;
    }

    // Gets the player stats from the main interface
    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    //Toggle open and close of Stats Panel
    public void toggleStatsPanel() {
        if (statsPanel != null) {
            statsPanel.setVisible(!statsPanel.isVisible());
            backgroundPanel.revalidate();
            backgroundPanel.repaint();
        }
    }
    
    // Returns the background panel
    public BackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    // Returns the stats panel
    public StatsPanel getStatsPanel() {
        return statsPanel;
    }
    
    //Removes the MainInterface 
    public void dispose() {
        frame.dispose();
    }
}



