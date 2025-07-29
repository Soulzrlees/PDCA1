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
        Scanner scanner = new Scanner(System.in);
        String playerClass = "";
        String name;
        int selection = 0;
        System.out.println("******** 2D RPG GAME ********");
        
        while (selection < 1 || selection > 3) {
            System.out.println("Select your class (1, 2, 3): \n1. Melee\n2. Ranger\n3. Mage");
            selection = scanner.nextInt();
            scanner.nextLine();
            switch (selection) {
                case 1:
                    playerClass = "Melee";
                    break;
                case 2:
                    playerClass = "Ranger";
                    break;
                case 3:
                    playerClass = "Mage";
                    break;
                default:
                    System.out.println("Invalid input, choose again.");
                    break;
            }
        }
        System.out.println("Enter your name: ");
        name = scanner.nextLine();
        
       
        Player player = new Player(name, 1, playerClass);
        System.out.println("Hello " + name + ", you are a ... " + playerClass + "!");
        
        System.out.println("___________Testing purposes___________");
        player.addGold(100);
        System.out.println("Gold: " + player.getGold());
    }
}
