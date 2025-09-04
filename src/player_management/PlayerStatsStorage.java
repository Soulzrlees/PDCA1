package player_management;

import entity.PlayerStats;
import main.exceptions.PlayerNotFoundException;
import collections.ArraySet;

public class PlayerStatsStorage {
    private ArraySet<PlayerStats> playerStats;
    private PlayerFileManager fileManager;

    public PlayerStatsStorage(PlayerFileManager fileManager) {
        this.fileManager = fileManager;
        this.playerStats = fileManager.readPlayerStats();
    }

    public void addPlayerStats(String playerName) {
        boolean found = false;
        for (PlayerStats ps : playerStats) {
            String name = ps.getPlayerName();
            if (name.equals(playerName)) {
                found = true;
                System.out.println(name + " already exists.");
                break;
            }
        }
        if (!found) {
            PlayerStats newPlayerStats = new PlayerStats(playerName, 0, 0, 0);
            playerStats.add(newPlayerStats);
        }
    }

    public PlayerStats loadExistingStats(String playerName) {
        playerStats = fileManager.readPlayerStats();
        for (PlayerStats ps : playerStats) {
            if (ps.getPlayerName().equals(playerName)) {
                return ps;
            }
        }
        return new PlayerStats(playerName, 0, 0, 0);
    }

    public ArraySet<PlayerStats> getPlayerStats() {
        return playerStats;
    }

    public void saveChanges() {
        fileManager.writePlayerStats(playerStats);
    }

    public PlayerStats getPlayer(String playerName) {
        for (PlayerStats ps : playerStats) {
            if (ps.getPlayerName().equals(playerName)) {
                return ps;
            }
        }
        return null;
    }
}
