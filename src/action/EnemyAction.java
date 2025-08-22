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

    private Random random;

    public EnemyAction() {
        this.random = new Random();
    }

    //Finds the total damage
    @Override
    public void attack(Entity enemy, Entity player){
        if(player.getEvade()){
            System.out.println("You have dodged the attack!");
        }
        else {
            int totalDamage = enemy.getbaseDmg() + random.nextInt(10);
            System.out.println("Enemy dealt " + totalDamage + " damage to you!");
            player.damageCalculate(totalDamage);
        }
    }

    //Finds out the distance the enemy travelled forwards
    @Override
    public void moveBackward(Entity player, Entity enemy){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = moveDistance + enemy.getPosition();
        //Making sure that the new Position is under or at 10
        if(newPosition > 10){
            newPosition = 10;
        }
        enemy.setPosition(newPosition);

        int distance = enemy.calculateDistance(player, enemy);
        System.out.println("Distance to " + player.getName() + ": " + distance + "m");
    }

    //Finds out the distance the enemy travelled backwards
    @Override
    public void moveForward(Entity player, Entity enemy){
        int moveDistancebackwards = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() - moveDistancebackwards;
        //Making sure that the new Position above 1 or at 1
        if(newPosition <= 1){
            newPosition = 1;
        }
        enemy.setPosition(newPosition);

        int distance = enemy.calculateDistance(player, enemy);
        if(distance < 1){
            distance = 1;
        }
        System.out.println("Distance to " + player.getName() + ": " + distance + "m");
    }
    
    //Heals the enemy 
    @Override
    public void heal(Entity enemy) {
        int healAmount = random.nextInt(11) + 5;
        // Increase health, but don't exceed max
        int newHealth = enemy.getHealth() + healAmount;
        if (newHealth > enemy.getMaxHealth()) {
            newHealth = enemy.getMaxHealth();
        }

        enemy.setHealth(newHealth);
        System.out.println(enemy.getName() + " HP healed " + healAmount + "+");
    }

}
