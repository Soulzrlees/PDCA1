package player_management;

import entity.Player;
import entity.PlayerStats;
import main.exceptions.PlayerNotFoundException;

public class AccessFile {
    private PlayerFileManager playerFileManager;
    private PlayerStorage playerStorage;
    private PlayerService playerService;
    private PlayerStatsStorage playerStatsStorage;
    private PlayerStatsService playerStatsService;


    public AccessFile() { // This constructor was made by AI
        this.playerFileManager = new PlayerFileManager();
        this.playerStorage = new PlayerStorage(playerFileManager);
        this.playerService = new PlayerService(playerStorage);
        this.playerStatsStorage = new PlayerStatsStorage(playerFileManager);
        this.playerStatsService = new PlayerStatsService(playerStatsStorage);
    }

    // Playerbase methods
    public boolean addPlayer(String playerName, String playerClass) {
        boolean success = playerStorage.addPlayer(playerName, playerClass);
        writeToPlayerBase();
        return success;
    }
    // updates the player's level in the file
    public void updatePlayerLevel(String playerName) {
        playerService.updateLevel(playerName);
        writeToPlayerBase();
    }
    // updates the player's experience in the file
    public void updatePlayerExperience(String playerName, int experienceToAdd) {
        playerService.updateExperience(playerName, experienceToAdd);
        writeToPlayerBase();
    }
    // updates the player's gold in the file
    public void addPlayerGold(String playerName, int goldToAdd) {
        playerService.addGold(playerName, goldToAdd);
        writeToPlayerBase();
    }
    // updates the player's gold in the file
    public void removePlayerGold(String playerName, int goldToRemove) {
        playerService.removeGold(playerName, goldToRemove);
        writeToPlayerBase();
    }
    // returns the player using their name
    public Player loadExistingPlayer(String playerName) throws PlayerNotFoundException {
        return playerStorage.loadExistingPlayer(playerName);
    }
    // writes the arrayset of players into the file
    public void writeToPlayerBase() {
        playerStorage.saveChanges();
    }


    // Playerstats methods
    
    // writes a player's skill points to the file
    public void addPlayerStats(String playerName) {
        playerStatsStorage.addPlayerStats(playerName);
        writeToPlayerStats();
    }
    // update the player's damage skill points in the file
    public void updateDamagePoints(String playerName) {
        playerStatsService.updateDamagePoints(playerName);
        writeToPlayerStats();
    }
    // update the player's health skill points in the file   
    public void updateHealthPoints(String playerName) {
        playerStatsService.updateHealthPoints(playerName);
        writeToPlayerStats();
    }
    // update the player's range skill points in the file
    public void updateRangePoints(String playerName) {
        playerStatsService.updateRangePoints(playerName);
        writeToPlayerStats();
    }
    // returns all the player's skillpoints using the player's name
    public PlayerStats loadPlayerStats(String playerName) {
        return playerStatsStorage.loadExistingStats(playerName);
    }
    // writes a player's stats to the file
    public void writeToPlayerStats() {
        playerStatsStorage.saveChanges();
    }

}
