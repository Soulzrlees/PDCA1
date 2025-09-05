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
    public void attack(Entity enemy, Entity player, int round){
        //Based on the position of the player to the enemy, if it range is less than the Position than attack does not hit.
        if(Entity.calculateDistance(enemy, player) > enemy.getAttackRange()){
            System.out.println("Attack missed due to distance!");
        }
        //If getEvade return true then attack will be dodged so no damage taken.
        else if(player.getEvade() && round != 1){
            System.out.println(player.getName() + " has dodged the attack!");
        }
        else {
            int totalDamage = enemy.getbaseDmg() + random.nextInt(5);
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

        int distance = Entity.calculateDistance(player, enemy);
        System.out.println("Moved " + distance + "m");
    }

    //Finds out the distance the enemy travelled backwards
    @Override
    public void moveForward(Entity player, Entity enemy){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() - moveDistance;
        //Making sure that the new Position above 1 or at 1
        if(newPosition <= 1){
            newPosition = 1;
        }
        enemy.setPosition(newPosition);

        int distance = Entity.calculateDistance(player, enemy);
        if(distance < 1){
            distance = 1;
        }
        System.out.println("Moved " + distance + "m");
    }
    
    //Heals the enemy 
    @Override
    public void heal(Entity enemy) {
        int healAmount = random.nextInt(20) + 10;
        // Increase health, but don't exceed max
        int newHealth = enemy.getHealth() + healAmount;
        if (newHealth > enemy.getMaxHealth()) {
            newHealth = enemy.getMaxHealth();
        }

        enemy.setHealth(newHealth);
        System.out.println(enemy.getName() + " HP healed " + healAmount + "+");
    }

}
