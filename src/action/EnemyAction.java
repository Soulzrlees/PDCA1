/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import java.util.Random;
import entity.Entity;

/**
 *
 * @author Shawn lee
 */

public class EnemyAction implements Action_Interface{

    private Random randomDamage;
    private Random randomDistancef;

    public EnemyAction(){
        this.randomDamage = new Random();
        this.randomDistancef = new Random();
    }

    //Prints out the stats of the enemy
    public void stats(Entity enemy){
        System.out.println("Name = " + enemy.getName());
        System.out.println("Enemy Health = " + enemy.getHealth() + " from " + enemy.getMaxHealth());
        System.out.println("Enemy Position = " + enemy.getPosition());
    }

    //Finds the total damage
    public void attack(Entity enemy){
        int totalDamage = enemy.getbaseDmg() + randomDamage.nextInt(10);
        System.out.println("You have dealt " + totalDamage + " damage !");

        enemy.damageCalculate(totalDamage);
        stats(enemy);
    }

    //Finds out the distance the enemy travelled forward
    public void moveForward(Entity player, Entity enemy){
        int moveDistance = randomDistancef.nextInt(5) + 1;
        int newPosition = moveDistance + enemy.getPosition();
        //Making sure that the new Position is under or at 10
        if(newPosition > 10){
            newPosition = 10;
        }
        enemy.setPosition(newPosition);

        int distance = enemy.calculateDistance(player, enemy);
        System.out.println("Distance to " + player.getName() + ": " + distance + "m");
        stats(enemy);;
    }

    public void moveBackward(){};
    public void heal(){};
    public void evade(){};
}
