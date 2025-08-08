/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;

import entity.Enemy;
import entity.Entity;
import action.EnemyAction;
/**
 *
 * @author fatehbhular, ShawnLee
 */
public class Main {
    public static void main(String[] args) {
        // Setup variables
        Scanner scan = new Scanner(System.in);
        AccessFile accessFile = new AccessFile();
        boolean gameStart;
        String playerName;
        String playerClass;

        //Testing Area - Shawn:

        //Entity player = new Entity("Hero", 1, "melee");
        //Enemy goblin = new Enemy("Goblin", 3, "melee");
        //EnemyAction enemyAction = new EnemyAction();

        //goblin.Action(player, enemyAction);
        //player.setPosition(2);
        //goblin.setPosition(10);

        //Testing area ends here...

        playerName = getPlayerName(scan);
        accessFile.addPlayer(playerName);


        //Testing Area - Fateh:


        //Testing area ends here...

        scan.close();
    }

    public static String getPlayerName(Scanner scan) {
        System.out.println("What should we call our new hero?\n");
        String name = scan.nextLine();
        return name;
    }

    public static String getPlayerClass(Scanner scan) {
        String choice = "";
        String pClass = "";
        boolean nameValid = false;
        
        System.out.println("Please select a class so you can take our kingdom back from them! (1-3):");
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