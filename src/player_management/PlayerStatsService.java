package player_management;

import entity.Player;
import entity.PlayerStats;

public class PlayerStatsService {
    private PlayerStatsStorage statsStorage;

    public PlayerStatsService(PlayerStatsStorage statsStorage) {
        this.statsStorage = statsStorage;
    }
    // increments the player's damage skillpoints in the file
    public void updateDamagePoints(String playerName) {
        PlayerStats playerStat = statsStorage.getPlayer(playerName);

        if (playerStat != null) {
            int newDamagePoints = playerStat.getDamageSkillPoints() + 1;
            playerStat.setDamageSkillPoints(newDamagePoints);
        } else {
            System.out.println("Player not found");
        }
    }
    // increments the player's health skillpoints in the file
    public void updateHealthPoints(String playerName) {
        PlayerStats playerStat = statsStorage.getPlayer(playerName);

        if (playerStat != null) {
            int newHealthPoints = playerStat.getHealthSkillPoints() + 1;
            playerStat.setHealthSkillPoints(newHealthPoints);
        } else {
            System.out.println("Player not found");
        }
    }
    // increments the player's range skillpoints in the file
    public void updateRangePoints(String playerName) {
        PlayerStats playerStat = statsStorage.getPlayer(playerName);

        if (playerStat != null) {
            int newRangePoints = playerStat.getRangeSkillPoints() + 1;
            playerStat.setRangeSkillPoints(newRangePoints);
        } else {
            System.out.println("Player not found");
        }
    }
}
