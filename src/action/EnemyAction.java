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
        int newPosition = enemy.getPosition() + moveDistance;
        //Making sure that the new Position is under or at 10
        if(newPosition > 20){
            newPosition = 20;
        }
        enemy.setPosition(newPosition);
        System.out.println(player.getPosition() + " " + enemy.getPosition());
        int distance = Entity.calculateDistance(player, enemy);
        System.out.println("Moved Backwards " + moveDistance + "m");
    }

    //Finds out the distance the enemy travelled backwards
    @Override
    public void moveForward(Entity player, Entity enemy){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() - moveDistance;
        //Making sure that the new Position above 1 or at 1
        if(newPosition < player.getPosition()){
            int difference = enemy.getPosition() - player.getPosition();
            int toMove = moveDistance - difference;
            int temp = player.getPosition();
            enemy.setPosition(temp);
            int playerPosition = player.getPosition() - toMove;
            player.setPosition(playerPosition);
            if (player.getPosition() < 0) {
                player.setPosition(0);
                System.out.println("Enemy has pushed you to the edge of the arena!");
                if (enemy.getPosition() < 1) {
                    enemy.setPosition(1);
                    System.out.println("Enemy has cornered you");
                } else {
                    System.out.println("Enemy moved towards you by " + difference + "m");
                }
            } else {
                System.out.println("Enemy has pushed you backwards by: " + toMove + "m");
                System.out.println("Enemy moved towards you by: " + difference + "m");
            }
        } else {
            enemy.setPosition(newPosition);
            int distance = Entity.calculateDistance(player, enemy);
            if (distance < 1) {
                distance = 1;
            }
            System.out.println("Moved Forwards " + moveDistance + "m");
        }
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
