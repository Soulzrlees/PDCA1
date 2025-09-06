package player_management;

import entity.Player;

public class PlayerService {
    private PlayerStorage playerStorage;
    
    public PlayerService(PlayerStorage playerStorage) {
        this.playerStorage = playerStorage;
    }
    // updates the player's level in the playerbase file
    public void updateLevel(String playerName) {
        Player player = playerStorage.findPlayer(playerName);

        if (player != null) {
            int newLevel = player.getLevel() + 1;
            player.setLevel(newLevel);
        } else {
            System.out.println("Player not found");
        }
    }
    // updates the player's experience in the playerbase file
    public void updateExperience(String playerName, int experienceToAdd) {
        Player player = playerStorage.findPlayer(playerName);

        if (player != null) {
            player.addExp(experienceToAdd);
        } else {
            System.out.println("Player not found");
        }
    }
    // updates the player's gold in the playerbase file
    public void addGold(String playerName, int goldToAdd) {
        Player player = playerStorage.findPlayer(playerName);

        if (player != null) {
            player.addGold(goldToAdd);
        } else {
            System.out.println("Player not found");
        }
    }

    public void removeGold(String playerName, int goldToRemove) {
        Player player = playerStorage.findPlayer(playerName);

        if (player != null) {
            player.removeGold(goldToRemove);
        } else {
            System.out.println("Player not found");
        }
    }
}
