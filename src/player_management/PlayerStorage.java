package player_management;

import entity.Player;
import main.exceptions.PlayerNotFoundException;
import collections.ArraySet;

public class PlayerStorage {
    private ArraySet<Player> playerbase;
    private PlayerFileManager fileManager;

    public PlayerStorage(PlayerFileManager fileManager) {
        this.fileManager = fileManager;
        this.playerbase = fileManager.readPlayerBase();
    }
    // adds a new player into the playerbase file
    public boolean addPlayer(String playerName, String playerClass) {
        boolean found = false;
        for (Player p : playerbase) {
            String name = p.getName();
            if (name.equals(playerName)) {
                found = true;
                System.out.println("Player already exists.");
                return false;
            }
        }
        if (!found) {
            Player newPlayer = new Player(playerName, 1, 10, 100, playerClass);
            playerbase.add(newPlayer);
        }
        return true;
    }
    // loads an existing player from the playerbase file
    public Player loadExistingPlayer(String playerName) throws PlayerNotFoundException {
        playerbase = fileManager.readPlayerBase();
        for (Player p : playerbase) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        throw new PlayerNotFoundException("Player: " + playerName + " not found.");
    }
    // returns the entire playerbase
    public ArraySet<Player> getPlayerBase() {
        return playerbase;
    }
    // saves the updated players
    public void saveChanges() {
        fileManager.writePlayerBase(playerbase);
    }
    // returns a player from the playerbase file
    public Player findPlayer(String playerName) {
        for (Player p : playerbase) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }
}
