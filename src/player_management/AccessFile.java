package player_management;

import entity.Player;
import main.exceptions.PlayerNotFoundException;

public class AccessFile {
    private PlayerFileManager playerFileManager;
    private PlayerStorage playerStorage;
    private PlayerService playerService;

    public AccessFile() { // This constructor was made by AI
        this.playerFileManager = new PlayerFileManager();
        this.playerStorage = new PlayerStorage(playerFileManager);
        this.playerService = new PlayerService(playerStorage);
    }

    public void addPlayer(String playerName, String playerClass) {
        playerStorage.addPlayer(playerName, playerClass);
    }

    public void updatePlayerLevel(String playerName) {
        playerService.updateLevel(playerName);
    }

    public void updatePlayerExperience(String playerName, int experienceToAdd) {
        playerService.updateExperience(playerName, experienceToAdd);
    }

    public void addPlayerGold(String playerName, int goldToAdd) {
        playerService.addGold(playerName, goldToAdd);
    }

    public void removePlayerGold(String playerName, int goldToRemove) {
        playerService.removeGold(playerName, goldToRemove);
    }

    public Player loadExistingPlayer(String playerName) throws PlayerNotFoundException {
        return playerStorage.loadExistingPlayer(playerName);
    }

    public void writeToPlayerBase() {
        playerStorage.saveChanges();
    }
}
