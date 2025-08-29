package player_management;

import java.io.*;
import entity.Player;
import collections.ArraySet;

public class PlayerFileManager {
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private static String filePath = "src/resources/playerbase.txt";

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
}
