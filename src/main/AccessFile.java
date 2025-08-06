/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fatehbhular
 */
package main;

import java.io.*;
import entity.Player;
import collections.ArraySet;

public class AccessFile {
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    ArraySet<Player> playerbase = new ArraySet<>();
    ArraySet<Player> playerNames = new ArraySet<>();

    public void readPlayerBase() {
        String pName, pLevel, pClass;
        try {
            reader = new BufferedReader(new FileReader("src/resources/playerbase.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                pName = split[0];
                pLevel = split[1];
                pClass = split[2];
                playerbase.add(new Player(pName, Integer.parseInt(pLevel), pClass));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    public void addPlayer(String pName) {
        boolean found = false;
        readPlayerBase();
        for (Player p : playerbase) {
            String name = p.getName();
            if (name.equals(pName)) {
                found = true;
            }
        }
        if (!found) {
            Player newPlayer = new Player(pName, 1, "default");
            try {
                writer = new BufferedWriter(new FileWriter("src/resources/playerbase.txt", true));
                writer.write(newPlayer.toString());
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        }
    }
}
