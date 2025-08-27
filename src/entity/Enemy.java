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
    public void Action(Entity target, EnemyAction enemyAction, int round){
        switch(entityClass){
            case "melee":
                int meleeAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(meleeAction < 35){ //35
                    enemyAction.attack(this, target, round);
                }
                else if(meleeAction < 50){ //50
                    enemyAction.moveForward(target, this);
                }
                else if(meleeAction < 65){ //65
                    enemyAction.moveBackward(target, this);
                }
                else{ //85
                    enemyAction.heal(this);
                }
                break;
            case "ranger":
                int rangerAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(rangerAction < 50){
                    enemyAction.attack(this, target, round);
                }
                else if(rangerAction < 75){
                    enemyAction.moveForward(target, this);

                }
                else if(rangerAction < 85){
                    enemyAction.moveBackward(target, this);
                }
                else{
                    enemyAction.heal(this);
                }
                break;
            case "mage":
                int mageAction = random.nextInt(100);
                //35% change to attack, 15% chance to moveforward, 15% chance to movebackward, 20% to heal, and 15% to dodge
                if(mageAction < 50){
                    enemyAction.attack(this, target, round);
                }
                else if(mageAction < 75){
                    enemyAction.moveForward(target, this);
                }
                else if(mageAction < 85){
                    enemyAction.moveBackward(target, this);
                }
                else{
                    enemyAction.heal(this);
                }
                break;
            default:
                System.out.println("Invalid Class.");
                break;
        }
    }
}
