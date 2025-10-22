/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import java.util.Random;
import entity.Entity;
import gui.battle.BattleLogPanel;

/**
 *
 * @author Shawn lee
 */

public class EnemyAction implements Action_Interface{

    private Random random;
    private BattleLogPanel battleLogPanel;

    public EnemyAction(BattleLogPanel battleLogPanel) {
        this.random = new Random();
        this.battleLogPanel = battleLogPanel;
    }

    //Finds the total damage
    @Override
    public void attack(Entity enemy, Entity player, int round){
        //Based on the position of the player to the enemy, if it range is less than the Position than attack does not hit.
        if(Entity.calculateDistance(enemy, player) > enemy.getAttackRange()){
            battleLogPanel.appendLog("Attack missed due to distance!");
            System.out.println("Attack missed due to distance!");
        }
        //If getEvade return true then attack will be dodged so no damage taken.
        else if(player.getEvade() && round != 1){
            battleLogPanel.appendLog(player.getName() + " has dodged the attack!");
            System.out.println(player.getName() + " has dodged the attack!");
        }
        else {
            int totalDamage = enemy.getbaseDmg() + random.nextInt(5);
            battleLogPanel.appendLog("Enemy dealt " + totalDamage + " damage to you!");
            System.out.println("Enemy dealt " + totalDamage + " damage to you!");
            player.damageCalculate(totalDamage);
        }
    }

    //Finds out the distance the enemy travelled away from player
    @Override
    public void moveBackward(Entity player, Entity enemy){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() + moveDistance;
        //Making sure that the new Position is under or at 20
        if(newPosition > 20){
            newPosition = 20;
        }
        enemy.setPosition(newPosition);
        battleLogPanel.appendLog(player.getPosition() + " " + enemy.getPosition());
        System.out.println(player.getPosition() + " " + enemy.getPosition());
        int distance = Entity.calculateDistance(player, enemy);
        battleLogPanel.appendLog("Moved Backwards " + moveDistance + "m");
        System.out.println("Moved Backwards " + moveDistance + "m");
    }

    //Finds out the distance the enemy travelled towards player
    @Override
    public void moveForward(Entity player, Entity enemy){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() - moveDistance;
        //Making sure that the new Position is in front of player or above 1
        if(newPosition < player.getPosition()){
            int difference = enemy.getPosition() - player.getPosition();
            int toMove = moveDistance - difference;
            int temp = player.getPosition();
            enemy.setPosition(temp);
            int playerPosition = player.getPosition() - toMove;
            player.setPosition(playerPosition);
            if (player.getPosition() < 0) {
                player.setPosition(0);
                battleLogPanel.appendLog("Enemy has pushed you to the edge of the arena!");
                System.out.println("Enemy has pushed you to the edge of the arena!");
                if (enemy.getPosition() < 1) {
                    enemy.setPosition(1);
                    battleLogPanel.appendLog("Enemy has cornered you");
                    System.out.println("Enemy has cornered you");
                } else {
                    battleLogPanel.appendLog("Enemy moved towards you by " + difference + "m");
                    System.out.println("Enemy moved towards you by " + difference + "m");
                }
            } else {
                battleLogPanel.appendLog("Enemy has pushed you backwards by: " + toMove + "m");
                battleLogPanel.appendLog("Enemy moved towards you by: " + difference + "m");
                System.out.println("Enemy has pushed you backwards by: " + toMove + "m");
                System.out.println("Enemy moved towards you by: " + difference + "m");
            }
        } else {
            enemy.setPosition(newPosition);
            int distance = Entity.calculateDistance(player, enemy);
            if (distance < 1) {
                distance = 1;
            }
            battleLogPanel.appendLog("Moved Forwards " + moveDistance + "m");
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
        battleLogPanel.appendLog(enemy.getName() + " HP healed " + healAmount + "+");
        System.out.println(enemy.getName() + " HP healed " + healAmount + "+");
    }

}
