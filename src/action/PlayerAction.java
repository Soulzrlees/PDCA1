package action;

import java.util.Random;
import entity.Entity;
import gui.battle.BattleLogPanel;

/**
 *
 * @author Shawn lee
 **/

public class PlayerAction implements Action_Interface{
    private Random random;
    private int healCount;
    private BattleLogPanel battleLogPanel;

    public PlayerAction(BattleLogPanel battleLogPanel){
        this.random = new Random();
        this.healCount = 0;
        this.battleLogPanel = battleLogPanel;
    }

    //Finds the total damage
    @Override
    public void attack(Entity enemy, Entity player, int round){
        //Based on the position of the player to the enemy, if it range is less than the Position than attack does not hit.
        if(Entity.calculateDistance(enemy, player) > player.getAttackRange()){
            battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Attack missed due to distance!");
            System.out.println("Attack missed due to distance!");
        }
        //If getEvade is valid than attack will be dodged so no damage taken.
        else if(enemy.getEvade() && round != 1){
            battleLogPanel.appendLog("[ " + enemy.getName() + " ] " + "Dodged the attack!");
            System.out.println("Enemy has dodged the attack!");
        }
        else {
            int totalDamage = player.getbaseDmg() + random.nextInt(5);
            battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Dealt " + totalDamage + " damage to the enemy!");
            System.out.println("You dealt " + totalDamage + " damage to the enemy!");
            enemy.damageCalculate(totalDamage);
        }
    }

    // calculates distance the player moved away from enemy
    @Override
    public void moveBackward(Entity enemy, Entity player){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = player.getPosition() - moveDistance;
        //Making sure that the new Position is under or at 10:
        if(newPosition < 0){
            newPosition = 0;
            battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Can't move further in this direction");
            System.out.println("You can't move further in this direction");
        }
        player.setPosition(newPosition);
        int distance = Entity.calculateDistance(player, enemy);
        battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Moved backwards " + moveDistance + "m");
        System.out.println("Moved backwards " + moveDistance + "m");
    }

    //Finds out the distance the enemy travelled backwards
    @Override
    public void moveForward(Entity enemy, Entity player){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = player.getPosition() + moveDistance;
        //Making sure that the new Position above 1 or at 1
        if(newPosition > enemy.getPosition()){
            int difference = enemy.getPosition() - player.getPosition();
            int toMove = moveDistance - difference;
            int temp = enemy.getPosition();
            player.setPosition(temp);
            int enemyPosition = enemy.getPosition() + toMove;
            enemy.setPosition(enemyPosition);
            if (enemy.getPosition() > 20) {
                enemy.setPosition(20);
                battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Has pushed the enemy to the edge of the arena!");
                System.out.println("You have push the enemy to the edge of the arena!");
                
                if (player.getPosition() > 19) {
                    player.setPosition(19);
                    battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Has cornered the enemy");
                    System.out.println("You have cornered the enemy");
                } else {
                    battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Moved towards the enemy by: " + difference + "m");
                    System.out.println("You moved towards the enemy by: " + difference + "m");
                }
            } else {
                battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Has pushed the enemy backwards by: " + toMove + "m");
                battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Moved towards the enemy by: " + difference + "m");
                System.out.println("You have pushed the enemy backwards by: " + toMove + "m");
                System.out.println("You moved towards the enemy by: " + difference + "m");
            }
        } else {
            player.setPosition(newPosition);
            int distance = Entity.calculateDistance(player, enemy);
            if(distance < 1){
                distance = 1;
            }
            battleLogPanel.appendLog("[ " + player.getName() + " ] " + "Moved forwards " + moveDistance + "m");
            System.out.println("Moved forwards " + moveDistance + "m");
        }
    }
    // heals the player (only 2 times allowed)
    public void heal(Entity player) {
        int newHealth = player.getHealth();
        int healAmount = 0;
        if (healCount >= 2){
            battleLogPanel.appendLog("You have used all your heals!");
            System.out.println("You have used all your heals!");
        }
        else{
            healAmount = random.nextInt(20) + 10;
            newHealth = player.getHealth() + healAmount;
            healCount++;
            // Increase health, but don't exceed max
            if (newHealth > player.getMaxHealth()) {
                newHealth = player.getMaxHealth();
            } else {
                player.setHealth(newHealth);
            }
        }
        battleLogPanel.appendLog("[ " + player.getName() + " ] " + "HP healed " + healAmount + "+");
        System.out.println(player.getName() + " HP healed " + healAmount + "+");

    }
}