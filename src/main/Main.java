/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Scanner;
/**
 *
 * @author fatehbhular
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerClass;
        System.out.println("******** 2D RPG GAME ********");
        
        System.out.println("Select your class (1, 2, 3): \n1. Melee\n2. Ranger\n3. Mage");
        int selection = scanner.nextInt();
        switch (selection) {
            case 1:
                playerClass = "Melee";
                System.out.println("You are a ... Melee!!!");
                break;
            case 2:
                playerClass = "Ranger";
                System.out.println("You are a ... Ranger!!!");
                break;
            case 3:
                playerClass = "Mage";
                System.out.println("You are a ... Mage!!!");
                break;
            default:
                System.out.println("Invalid input, choose again!!!");
                break;
        }
    }
}
