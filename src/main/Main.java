/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import entity.Player;
import java.util.Scanner;
/**
 *
 * @author fatehbhular, ShawnLee
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Intro intro = new Intro();
        boolean gameStart;
        String playerName;
        String playerClass;

        // Game Introduction + get Name and Class
        clearScreen();
        intro.printBanner();
        intro.welcomeMessage();
        gameStart = askForHelp(scan);
        playerName = getPlayerName(scan);
        System.out.println("\nGreetings to our world " + playerName + "!\n");
        playerClass = getPlayerClass(scan);    
        
        scan.close();
        
    }

    public static boolean askForHelp(Scanner scan) {
        System.out.println("Will you help us? (y/n): ");
        String accept = scan.nextLine();
        
        if (accept.equalsIgnoreCase("y") || accept.equalsIgnoreCase("yes")) {
            System.out.println("Wonderful news!!! I knew you were a hero when I saw you.");
            clearScreen();
            return true;
        } else if (accept.equalsIgnoreCase("n") || accept.equalsIgnoreCase("no")) {
            System.out.println("Are you sure? It doesn't look like you're doing anything important :/ (y/n): ");
            accept = scan.nextLine();
            if (accept.equalsIgnoreCase("y") || accept.equalsIgnoreCase("yes")) {
                System.out.println("You are really mean :c");
                clearScreen();
                return false;
            } else {
                System.out.println("Great!!! Thank you for changing your mind brave person.");
                clearScreen();
                return true;
            }
        } else {
            System.out.println("Oops, looks like you inputted the wrong key.");
            clearScreen();
            return askForHelp(scan);
        }
    }

    public static String getPlayerName(Scanner scan) {
        System.out.println("What should we call our new hero?\n");
        String name = scan.nextLine();
        clearScreen();
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

        clearScreen();
        return pClass;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}