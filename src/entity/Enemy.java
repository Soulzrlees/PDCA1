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
                //35% change to attack, 35% chance to defend, 20% to heal, and 10% to dodge
                if(meleeAction < 35){
                    enemyAction.attack(target);
                }
                else if(meleeAction < 55){
                    enemyAction.moveForward(target, this);
                }
                else if(meleeAction < 75){
                    System.out.println("Moving Backwards");
                }
                else if(meleeAction < 90){
                    System.out.println("Heal");
                }
                else{
                    System.out.println("Dodge!");
                }
                break;
            case "ranger":
                int rangerAction = random.nextInt(100);
                //35% change to attack, 35% chance to defend, 20% to heal, and 10% to dodge
                if(rangerAction < 35){
                    System.out.println("Attack");
                }
                else if(rangerAction < 70){
                    System.out.println("Moved");
                }
                else if(rangerAction < 90){
                    System.out.println("Heal");
                }
                else{
                    System.out.println("Dodge!");
                }
                break;
            case "mage":
                int mageAction = random.nextInt(100);
                //35% change to attack, 35% chance to defend, 20% to heal, and 10% to dodge
                if(mageAction < 35){
                    System.out.println("Attack");
                }
                else if(mageAction < 70){
                    System.out.println("Moved");
                }
                else if(mageAction < 90){
                    System.out.println("Heal");
                }
                else{
                    System.out.println("Dodge!");
                }
                break;
            default:
                System.out.println("Invalid Class.");
                break;
        }
    }
}
