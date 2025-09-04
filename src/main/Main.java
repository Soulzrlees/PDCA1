/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;

import entity.Enemy;
import entity.Player;
import main.exceptions.PlayerNotFoundException;
import player_management.AccessFile;
import java.util.InputMismatchException;

/**
 *
 * @author fatehbhular, ShawnLee
 */



public class Main {
    
    public static void main(String[] args) {
        // Setup variables for scanning and accessing file
        Scanner scanner = new Scanner(System.in);
        AccessFile file = new AccessFile();

        // boolean for main game loop
        boolean gameRunning = true;

        // Method gets the player to input their username
        Player player = playerLoginScreen(scanner, file);
        System.out.println("Welcome to the GAME " + player.getName() + "!");

        while (gameRunning) {
            System.out.println("Select an option:\n1. Battle\n2. Inventory\n3. Stats\n4. Exit\n");
            int threadChoice = scanner.nextInt();
            scanner.nextLine();

            // Player can choose whether to battle, check their inventory, check their stats, or exit the game
            switch (threadChoice) {
                case 1:
                    // starts the battle interface from Battle class
                    System.out.println("You have chosen to battle!");
                    Enemy enemy = Enemy.createEnemy(player);
                    Battle battle = new Battle(player, enemy, scanner);
                    boolean result = battle.battle_interface();
                    // rewards players based on if they won or lost
                    if (result) {
                        giveRewards(player, true, scanner, file);
                    } else {
                        giveRewards(player, false, scanner, file);
                    }

                    break;
                case 2:
                    System.out.println("Entering inventory: ");
                    // print inventory
                    System.out.println("enter any key to EXIT the inventory.");
                    String exitKey = scanner.nextLine();
                    break;
                case 3:
                    // prints out the players stats
                    player.loadPlayerStats(file);
                    System.out.println(player.getName() + "'s stats:\n");
                    player.statsDisplay(player);
                    player.CheckSkillPoints(player, scanner, file);
                    
                    break;
                case 4:
                    // exits the gameloop thus ending game
                    System.out.println("Game saved. Goodbye!");
                    gameRunning = false;
                    break;
            }
        }
        scanner.close();
    }
    
    public static Player playerLoginScreen(Scanner scan, AccessFile file) {
        // create the player template
        Player player = null;
        boolean validGameType = false;
        while (!validGameType) {
            System.out.println("Select an option:\n1. Start new game\n2. Resume existing game");
            String gameType = scan.nextLine().trim();

            // entire loop to get correct input for username and class
            switch (gameType) {
                case "1":
                    // for creating new account
                    String playerName = getPlayerName(scan);
                    String playerClass = getPlayerClass(scan);
                    player = new Player(playerName, 1, playerClass);

                    try {
                        boolean success = file.addPlayer(playerName, playerClass);
                        if (!success) {
                            continue;
                        }
                        file.addPlayerStats(playerName);
                        file.writeToPlayerBase();
                        file.writeToPlayerStats();
                        player.loadPlayerStats(file);
                    } catch (Exception e) {
                        System.out.println("Could not save player to file: " + e.getMessage());
                    }

                    validGameType = true;
                    break;
                case "2":
                    // for logging into existing account
                    boolean loginComplete = false;
                    while (!loginComplete) {
                        System.out.println("Enter your username:");
                        String playerInput = scan.nextLine().trim();
                        try {
                            player = file.loadExistingPlayer(playerInput);
                            player.loadPlayerStats(file);
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
                                    boolean success = file.addPlayer(playerName, playerClass);
                                    if (!success) {
                                        continue;
                                    }
                                    file.addPlayerStats(playerName);
                                    file.writeToPlayerBase();
                                    file.writeToPlayerStats();
                                    player.loadPlayerStats(file);
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

    // ask for a valid username and return it
    public static String getPlayerName(Scanner scan) {
        String name = "";
        do {
            System.out.println("Please enter your name: ");
            name = scan.nextLine().trim();
            // makes sure name is not empty
            if(name.isEmpty()) {
                System.out.println("Name cannot be empty, please enter valid name.");
            }
        } while(name.isEmpty());
        return name;
    }

    // ask for userclass and return it
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

            // returns the user's choice of class
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

    // update the player's stats based on their previous win or lose
    public static void giveRewards(Player player, boolean wonBattle, Scanner scanner, AccessFile file) {
        if (wonBattle) {
            file.updatePlayerExperience(player.getName(), 100);
            file.addPlayerGold(player.getName(), 20);
        } else {
            file.removePlayerGold(player.getName(), 20);
        }
    }
}