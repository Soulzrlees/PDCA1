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

    public void updatePlayerLevel(String playerName) {
        playerService.updateLevel(playerName);
        writeToPlayerBase();
    }

    public void updatePlayerExperience(String playerName, int experienceToAdd) {
        playerService.updateExperience(playerName, experienceToAdd);
        writeToPlayerBase();
    }

    public void addPlayerGold(String playerName, int goldToAdd) {
        playerService.addGold(playerName, goldToAdd);
        writeToPlayerBase();
    }

    public void removePlayerGold(String playerName, int goldToRemove) {
        playerService.removeGold(playerName, goldToRemove);
        writeToPlayerBase();
    }

    public Player loadExistingPlayer(String playerName) throws PlayerNotFoundException {
        return playerStorage.loadExistingPlayer(playerName);
    }

    public void writeToPlayerBase() {
        playerStorage.saveChanges();
    }


    // Playerstats methods
    public void addPlayerStats(String playerName) {
        playerStatsStorage.addPlayerStats(playerName);
        writeToPlayerStats();
    }

    public void updateDamagePoints(String playerName) {
        playerStatsService.updateDamagePoints(playerName);
        writeToPlayerStats();
    }
    
    public void updateHealthPoints(String playerName) {
        playerStatsService.updateHealthPoints(playerName);
        writeToPlayerStats();
    }

    public void updateRangePoints(String playerName) {
        playerStatsService.updateRangePoints(playerName);
        writeToPlayerStats();
    }

    public PlayerStats loadPlayerStats(String playerName) {
        return playerStatsStorage.loadExistingStats(playerName);
    }

    public void writeToPlayerStats() {
        playerStatsStorage.saveChanges();
    }

}
