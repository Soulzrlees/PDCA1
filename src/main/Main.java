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
import main.exceptions.PlayerNotFoundException;
/**
 *
 * @author fatehbhular, ShawnLee
 */
public class Main {
    public static void main(String[] args) {
        // Setup variables
        Scanner scan = new Scanner(System.in);
        AccessFile file = new AccessFile();
        Entity player = null;
        
        String playerName;
        String playerClass;
        
        System.out.println("Select an option:\n1. Start new game\n2. Resume existing game");
        String gameType = scan.nextLine();
        
        switch (gameType) {
            case "1":
                playerName = getPlayerName(scan);
                playerClass = getPlayerClass(scan);
                player = new Player(playerName, 1, playerClass);
                file.addPlayer(playerName);
                break;
            case "2":
                boolean loginComplete = false;
                while (!loginComplete) {
                    System.out.println("Enter your username:");
                    String playerInput = scan.nextLine();
                    try {
                        player = file.loadExistingPlayer(playerInput);
                        loginComplete = true;
                    } catch (PlayerNotFoundException e) {
                        System.out.println(e.getMessage());
                        
                        boolean isValidChoice = false;
                        while (!isValidChoice) {
                            System.out.println("Player not found. Would you like to:\n1. try again\n2. create a new player");
                            String choice = scan.nextLine();
                            
                            if (choice.equals("1")) {
                                System.out.println("Retrying player entry...");
                                isValidChoice = true;
                            } else if (choice.equals("2")) {
                                playerName = getPlayerName(scan);
                                playerClass = getPlayerClass(scan);
                                player = new Player(playerName, 1, playerClass);
                                file.addPlayer(playerName);
                                loginComplete = true;
                                isValidChoice = true;
                            } else {
                                System.out.println("Invalid choice. Enter 1 or 2.");
                            }
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid option. Please restart and choose 1 or 2.");
                break;
        }
        
        

        //Testing Area - Shawn:

        //Entity player = new Entity("Hero", 1, "melee");
        //Enemy goblin = new Enemy("Goblin", 3, "melee");
        //EnemyAction enemyAction = new EnemyAction();

        //goblin.Action(player, enemyAction);
        //player.setPosition(2);
        //goblin.setPosition(10);

        //Testing area ends here...

        


        //Testing Area - Fateh:


        //Testing area ends here...

        scan.close();
    }

    public static String getPlayerName(Scanner scan) {
        System.out.println("Please enter a name: \n");
        String name = scan.nextLine();
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