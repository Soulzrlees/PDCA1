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

    public Enemy(String name, int level, String entityClass) {
        super(name, level, entityClass);
        this.random = new Random();

    }

    //The randomise action of different enemy types
    public void Action(Entity target, EnemyAction enemyAction){

        switch(entityClass){
            case "melee":
                int meleeAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(meleeAction < 35){ //35
                    enemyAction.attack(target);
                }
                else if(meleeAction < 50){ //50
                    enemyAction.moveForward(target, this);
                }
                else if(meleeAction < 65){ //65
                    enemyAction.moveBackward(target, this);
                }
                else if(meleeAction < 85){ //85
                    enemyAction.heal(this);
                }
                else{
                    enemyAction.evadetrue(this);
                    System.out.println("evadetrue");
                }
                break;
            case "ranger":
                int rangerAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(rangerAction < 35){ 
                    enemyAction.attack(target);
                }
                else if(rangerAction < 35){ 
                    enemyAction.moveForward(target, this);
                }
                else if(rangerAction < 75){ 
                    enemyAction.moveBackward(target, this);
                }
                else if(rangerAction < 90){ 
                    enemyAction.heal(this);
                }
                else{
                    enemyAction.evadetrue(this);
                    System.out.println("evadetrue");
                }
                break;
            case "mage":
                int mageAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(mageAction < 35){ 
                    enemyAction.attack(target);
                }
                else if(mageAction < 35){ 
                    enemyAction.moveForward(target, this);
                }
                else if(mageAction < 75){ 
                    enemyAction.moveBackward(target, this);
                }
                else if(mageAction < 90){ 
                    enemyAction.heal(this);
                }
                else{
                    enemyAction.evadetrue(this);
                    System.out.println("evadetrue");
                }
                break;
            default:
                System.out.println("Invalid Class.");
                break;
        }
    }
}
