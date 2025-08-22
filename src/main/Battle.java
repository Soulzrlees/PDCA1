package main;

import java.util.*;
import entity.Player;
import entity.Enemy;
import entity.Entity;
import action.PlayerAction;
import action.EnemyAction;
import java.util.Random;

import java.util.InputMismatchException;
/**
 *
 * @author Shawn lee
 */

public class Battle {
    private Entity player;
    private Enemy enemy;
    private boolean playerturn;
    private boolean battleEnded;
    private EnemyAction enemyAction;
    private PlayerAction playerAction;
    private Random random;

    public Battle(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.enemyAction = new EnemyAction();
        this.playerAction = new PlayerAction();
        playerturn = true;
        //When playerturn is true the player attacks, if the entity turn is true the enemy attack
        //Determines if the battle ended
        battleEnded = false;
    }

    //Returns the instructions how the moves player can perform
    public String printActionOption(){
        return "Select 1 for Attack\nSelect 2 for move Forward\nSelect 3 for move Backward\nSelect 4 for heal";
    }

    public void battle_interface() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        int playerinput = 0;

        System.out.println("____Battle Begin!____\n\n");
        while(!battleEnded){
            if(playerturn){
                try {
                    System.out.println(this.player.getName() + "'s turn!\n" + printActionOption());
                    playerinput = scanner.nextInt();
                    while (playerinput != 1 && playerinput != 2 && playerinput != 3 && playerinput != 4) {
                        System.out.println("Invalid input!");
                        playerinput = scanner.nextInt();
                    }
                    switch(playerinput){
                        case 1:
                            this.playerAction.attack(enemy, player);
                            System.out.println("Your health: " + this.player.getHealth() + "/" + this.player.getMaxHealth());
                            System.out.println("Enemy Health: " + this.enemy.getHealth() + "/" + this.enemy.getMaxHealth() + "\n");
                            if (this.enemy.getHealth() == 0) {
                                System.out.println("You have defeated " + this.enemy.getName() + ". Battle over!!!");
                                battleEnded = true;
                                continue;
                            }
                            playerturn = !playerturn;
                            break;
                        case 2:
                            this.playerAction.moveForward(enemy, player);
                            playerturn = !playerturn;
                            break;
                        case 3:
                            this.playerAction.moveBackward(enemy, player);
                            playerturn = !playerturn;
                            break;
                        case 4:
                            this.playerAction.heal(this.player);
                            playerturn = !playerturn;
                            break;
                        case 5:
                            playerturn = !playerturn;
                            break;
                        default:
                            System.out.println("Invalid input!");

                    }
                }
                catch (InputMismatchException e ){
                    System.out.println("Invalid input! " + e.getMessage());
                    scanner.next();
                }


            }
            if(!playerturn){
                System.out.println(this.enemy.getName() + "'s turn!");
                enemy.Action(this.player, enemyAction);
                System.out.println("Your health: " + this.player.getHealth() + "/" + this.player.getMaxHealth());
                System.out.println("Enemy Health: " + this.enemy.getHealth() + "/" + this.enemy.getMaxHealth() + "\n");
                if (this.player.getHealth() == 0) {
                    System.out.println("Enemy has defeated you. Battle over!!!");
                    battleEnded = true;
                    continue;
                }
                playerturn = !playerturn;
            }
        }
    }



}
