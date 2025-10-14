package gui;

import javax.swing.*;
import java.awt.*;
import entity.Player;
import entity.PlayerStats;

public class MainInterface {

    private final JFrame frame;
    private BackgroundPanel backgroundPanel;
    private StatsPanel statsPanel;

    public MainInterface(Player player, PlayerStats playerstats) {
        frame = new JFrame("RPG Game");
        createMainInterface(player, playerstats);
    }

    //Setup of the MainInterface
    private void createMainInterface(Player player, PlayerStats playerstats) {
        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setUndecorated(true);

        MenuPanel menuPanel = new MenuPanel(this);
        frame.add(menuPanel, BorderLayout.WEST);
        
        //Switch the character image displayed on the main interface depending on the users class
        String Image_Character;
        if(player.getClasses() == "melee"){
            Image_Character = "images/Knight_player.png";
        }
        else if(player.getClasses() == "ranger"){
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
        backgroundPanel.setLayout(null);
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Stats panel
        statsPanel = new StatsPanel(backgroundPanel, player, playerstats);
        statsPanel.setVisible(false);
        backgroundPanel.add(statsPanel);
        
        StatsPanelAction statsListener = new StatsPanelAction(statsPanel, player, playerstats);
        statsPanel.getdamageButton().addActionListener(statsListener);
        statsPanel.gethealthButton().addActionListener(statsListener);
        statsPanel.getRangeButton().addActionListener(statsListener);

        frame.setVisible(true);
    }

    //Toggle open and close of Stats Panel
    public void toggleStatsPanel() {
        if (statsPanel != null) {
            statsPanel.updateBounds(); // update size, position before showing
            statsPanel.setVisible(!statsPanel.isVisible());
            backgroundPanel.revalidate();
            backgroundPanel.repaint();
        }
    }

    public BackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    //Testing purposes
    public static void main(String[] args) {
        Player player = new Player("Fateh", 10, "mage"); 
        PlayerStats playerstats = new PlayerStats("Fateh",2,2,2);
        new MainInterface(player, playerstats);
    }
}
