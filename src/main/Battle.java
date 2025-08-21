package main;

import java.util.*;
import entity.Player;
import entity.Enemy;
import entity.Entity;
import action.PlayerAction;
import action.EnemyAction;
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

    public Battle(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.enemyAction = new EnemyAction();
        this.playerAction = new PlayerAction();
        //When playerturn is false the player attacks, if the entity turn is true the enemy attack
        playerturn = false;
        //Determines if the battle ended
        battleEnded = false;
    }

    //Returns the instructions how the moves player can perform
    public String printActionOption(){
        return "Select 1 for Attack\nSelect 2 for move Forward\nSelect 3 for move Backward\nSelect 4 for possibility of evading next attack";
    }

    public void battle_interface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("____Battle Begin!____\n\n");
        while(!battleEnded){
            if(!playerturn){
                System.out.println(this.player.getName() + " turns!\n" + printActionOption());
                int playerinput = scanner.nextInt();
                while(playerinput != 1 && playerinput != 2 && playerinput != 3 && playerinput != 4){
                    System.out.println("Invalid input!");
                    playerinput = scanner.nextInt();
                }
                switch(playerinput){
                    case 1:
                        this.playerAction.attack(player);
                        break;
                    case 2:
                        this.playerAction.moveForward(enemy, player);
                        break;
                    case 3:
                        this.playerAction.moveBackward(enemy, player);
                        break;
                    case 4:
                        this.playerAction.evadetrue(player);
                        break;
                    case 0:
                        continue;
                    default:
                        System.out.println("Invalid input!");
                }

            }
        }
    }



}
