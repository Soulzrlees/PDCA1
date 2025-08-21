package player_management;

import entity.Player;
import main.exceptions.PlayerNotFoundException;
import collections.ArraySet;

public class PlayerStorage {
    private ArraySet<Player> playerbase;
    private PlayerFileManager fileManager;

    public PlayerStorage(PlayerFileManager playerFileManager) {
        this.fileManager = playerFileManager;
        this.playerbase = fileManager.readPlayerBase();
    }

    public void addPlayer(String playerName, String playerClass) {
        boolean found = false;
        for (Player p : playerbase) {
            String name = p.getName();
            if (name.equals(playerName)) {
                found = true;
                System.out.println("Player already exists.");
                break;
            }
        }
        if (!found) {
            Player newPlayer = new Player(playerName, 1, 10, 100, playerClass);
            playerbase.add(newPlayer);
        }
    }

    public Player loadExistingPlayer(String playerName) throws PlayerNotFoundException {
        playerbase = fileManager.readPlayerBase();
        for (Player p : playerbase) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        throw new PlayerNotFoundException("Player: " + playerName + " not found.");
    }

    public ArraySet<Player> getPlayerBase() {
        return playerbase;
    }

    public void saveChanges() {
        fileManager.writePlayerBase(playerbase);
    }

    public Player findPlayer(String playerName) {
        for (Player p : playerbase) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }
}
