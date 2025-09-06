package player_management;

import java.io.*;
import entity.Player;
import collections.ArraySet;
import entity.PlayerStats;

public class PlayerFileManager {
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private static String filePath = "src/resources/playerbase.txt";
    private static String filePath2 = "src/resources/playerstats.txt";

    // reads the entire playerbase file and puts it into an arrayset
    public ArraySet<Player> readPlayerBase() {
        String playerName, playerLevel, playerExperience, playerGold, playerClass;
        ArraySet<Player> playerbase = new ArraySet<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                if (split.length < 5) {
                    System.out.println("Invalid format");
                    continue;
                }
                try {
                    playerName = split[0];
                    playerLevel = split[1];
                    playerExperience = split[2];
                    playerGold = split[3];
                    playerClass = split[4];
                    playerbase.add(new Player(playerName, Integer.parseInt(playerLevel), Integer.parseInt(playerExperience), Integer.parseInt(playerGold), playerClass));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid line structure");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) { reader.close(); }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }

        return playerbase;
    }
    // writes the entire arrayset of players into the playerbase file
    public void writePlayerBase(ArraySet<Player> playerbase) {
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            for (Player p : playerbase) {
                writer.write(p.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) { writer.close(); }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
    // returns an arrayset of all players' skillpoints
    public ArraySet<PlayerStats> readPlayerStats() {
        String playerName, damagePoints, healthPoints, rangePoints;
        ArraySet<PlayerStats> playerstats = new ArraySet<>();

        try {
            reader = new BufferedReader(new FileReader(filePath2));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                if (split.length != 4) {
                    System.out.println("Invalid format");
                    continue;
                }
                try {
                    playerName = split[0];
                    damagePoints = split[1];
                    healthPoints = split[2];
                    rangePoints = split[3];
                    playerstats.add(new PlayerStats(playerName, Integer.parseInt(damagePoints), Integer.parseInt(healthPoints), Integer.parseInt(rangePoints)));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid line structure");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) { reader.close(); }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
        return playerstats;
    }
    // writes the arrayset of players' skillpoints into the playerstat's file
    public void writePlayerStats(ArraySet<PlayerStats> playerstats) {
        try {
            writer = new BufferedWriter(new FileWriter(filePath2));
            for (PlayerStats player : playerstats) {
                writer.write(player.toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) { writer.close(); }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
