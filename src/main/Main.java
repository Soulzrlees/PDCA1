/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import action.EnemyAction;
import action.PlayerAction;
import main.exceptions.PlayerNotFoundException;
import player_management.AccessFile;
import collections.LinkedList;
import player_management.PlayerFileManager;

/**
 *
 * @author fatehbhular, ShawnLee
 */
public class Main {
    public static void main(String[] args) {
        // Setup variables
        Scanner scan = new Scanner(System.in);
        AccessFile file = new AccessFile();

        
        
        Player player = new Player("Shawn", 1, "melee");
        Enemy orc = new Enemy("Orc", 1, "melee");

        Battle battle = new Battle(player, orc);
        battle.battle_interface();
        
        
        //Entity player = playerLoginScreen(scan, file);


        scan.close();
    }
    
    public static Entity playerLoginScreen(Scanner scan, AccessFile file) {
        Entity player = null;
        boolean validGameType = false;
        while (!validGameType) {
            System.out.println("Select an option:\n1. Start new game\n2. Resume existing game");
            String gameType = scan.nextLine().trim();

            switch (gameType) {
                case "1":
                    String playerName = getPlayerName(scan);
                    String playerClass = getPlayerClass(scan);
                    player = new Player(playerName, 1, playerClass);

                    try {
                        file.addPlayer(playerName, playerClass);
                    } catch (Exception e) {
                        System.out.println("Could not save player to file: " + e.getMessage());
                    }

                    validGameType = true;
                    break;
                case "2":
                    boolean loginComplete = false;
                    while (!loginComplete) {
                        System.out.println("Enter your username:");
                        String playerInput = scan.nextLine().trim();
                        try {
                            player = file.loadExistingPlayer(playerInput);
                            loginComplete = true;
                        } catch (PlayerNotFoundException e) {
                            System.out.println(e.getMessage());

                            boolean isValidChoice = false;
                            while (!isValidChoice) {
                                System.out.println("Player not found. Would you like to:\n1. try again\n2. create a new player");
                                String choice = scan.nextLine().trim();

                                if (choice.equals("1")) {
                                    System.out.println("Retrying player entry...");
                                    isValidChoice = true;
                                } else if (choice.equals("2")) {
                                    playerName = getPlayerName(scan);
                                    playerClass = getPlayerClass(scan);
                                    player = new Player(playerName, 1, playerClass);
                                    file.addPlayer(playerName, playerClass);
                                    loginComplete = true;
                                    isValidChoice = true;
                                } else {
                                    System.out.println("Invalid choice. Enter 1 or 2.");
                                }
                            }
                        }
                    }
                    validGameType = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 or 2.");
                    break;
            }
        }
        return player;
    }

    public static String getPlayerName(Scanner scan) {
        String name = "";
        do {
            System.out.println("Please enter your name: ");
            name = scan.nextLine().trim();
            if(name.isEmpty()) {
                System.out.println("Name cannot be empty, please enter valid name.");
            }
        } while(name.isEmpty());
        return name;
    }

    public static String getPlayerClass(Scanner scan) {
        String choice = "";
        String pClass = "";
        boolean nameValid = false;
        
        System.out.println("Please select a class (1-3):");
        while (!nameValid) {
            System.out.println("1. Melee");
            System.out.println("2. Ranger");
            System.out.println("3. Mage\n");
            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    pClass = "melee";
                    nameValid = true;
                    break;
                case "2":
                    pClass = "ranger";
                    nameValid = true;
                    break;
                case "3":
                    pClass = "mage";
                    nameValid = true;
                    break;
                default:
                    System.out.println("Invalid input, please choose between 1-3!");
                    break;
            }
        }
        return pClass;
    }
}