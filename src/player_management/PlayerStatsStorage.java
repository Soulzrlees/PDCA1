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
    // adds a new player and their stats into the playerstats file
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
    // loads a player's stats from the playerstats file
    public PlayerStats loadExistingStats(String playerName) {
        playerStats = fileManager.readPlayerStats();
        for (PlayerStats ps : playerStats) {
            if (ps.getPlayerName().equals(playerName)) {
                return ps;
            }
        }
        return new PlayerStats(playerName, 0, 0, 0);
    }
    // returns the arrayset of players' stats from the playerstats file
    public ArraySet<PlayerStats> getPlayerStats() {
        return playerStats;
    }
    // writes the updated changes to a player's stats into playerstats file
    public void saveChanges() {
        fileManager.writePlayerStats(playerStats);
    }
    // returns a player from the playerstats file
    public PlayerStats getPlayer(String playerName) {
        for (PlayerStats ps : playerStats) {
            if (ps.getPlayerName().equals(playerName)) {
                return ps;
            }
        }
        return null;
    }
}
