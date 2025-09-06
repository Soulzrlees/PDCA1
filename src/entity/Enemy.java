/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.Random;
import action.EnemyAction;
/**
 *
 * @author Shawn lee
 */

public class Enemy extends Entity{
    private Random random;
    // enemy constructor
    public Enemy(String name, int level, String entityClass) {
        super(name, level, entityClass);
        this.random = new Random();
    }

    // This method generates a random enemy for the player to battle
    public static Enemy createEnemy(Player player) {
        Random random = new Random();
        int levelToAdd = random.nextInt(3);
        int level = player.getLevel() + levelToAdd;
        int classNum = random.nextInt(3) + 1;

        String enemyClass = "";
        String enemyName = "";

        switch (classNum) {
            case 1:
                enemyClass = "melee";
                enemyName = "High Orc";
                break;
            case 2:
                enemyClass = "ranger";
                enemyName = "Undead Shooter";
                break;
            case 3:
                enemyClass = "mage";
                enemyName = "Ice Elf";
                break;
            default:
                break;
        }
        return new Enemy(enemyName, level, enemyClass);
    }

    //The randomise action of different enemy types
    public void Action(Entity target, EnemyAction enemyAction, int round){
        switch(entityClass){
            case "melee":
                int meleeAction = random.nextInt(100);
                //55% change to attack, 25% chance to moveforward, 2% chance to movebackward, 18% to heal
                if(meleeAction < 55){ //55
                    enemyAction.attack(this, target, round);
                }
                else if(meleeAction < 80){ //80
                    enemyAction.moveForward(target, this);
                }
                else if(meleeAction < 82){ //82
                    enemyAction.moveBackward(target, this);
                }
                else{ //100
                    enemyAction.heal(this);
                }
                break;
            case "ranger":
                int rangerAction = random.nextInt(100);
                //50% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal
                if(rangerAction < 50){ //50
                    enemyAction.attack(this, target, round);
                }
                else if(rangerAction < 65){ //65
                    enemyAction.moveForward(target, this);

                }
                else if(rangerAction < 80){ //80
                    enemyAction.moveBackward(target, this);
                }
                else{ //100
                    enemyAction.heal(this);
                }
                break;
            case "mage":
                int mageAction = random.nextInt(100);
                //50% change to attack, 10% chance to moveforward, 25% chance to movebackward, 15% to heal
                if(mageAction < 50){ //50
                    enemyAction.attack(this, target, round);
                }
                else if(mageAction < 60){ //60
                    enemyAction.moveForward(target, this);
                }
                else if(mageAction < 85){ //85
                    enemyAction.moveBackward(target, this);
                }
                else{ //100
                    enemyAction.heal(this);
                }
                break;
            default:
                System.out.println("Invalid Class.");
                break;
        }
    }
}
