package action;

import java.util.Random;
import entity.Entity;
import gui.battle.BattleLogPanel;

/**
 * Enemy actions for battle: attack, move, heal
 * @author Shawn lee
 */
public class EnemyAction implements Action_Interface {

    private Random random;
    private BattleLogPanel battleLogPanel;

    public EnemyAction(BattleLogPanel battleLogPanel) {
        this.random = new Random();
        this.battleLogPanel = battleLogPanel;
    }

    // Finds the total damage
    @Override
    public void attack(Entity enemy, Entity player, int round) {
        // FIXED: Ensure distance is positive using Math.abs
        if (Math.abs(Entity.calculateDistance(enemy, player)) > enemy.getAttackRange()) {
            battleLogPanel.appendLog("[ " + enemy.getName() + " ] Attack missed due to distance!");
            System.out.println("Attack missed due to distance!");
        } 
        // Check evade
        else if (player.getEvade() && round != 1) {
            battleLogPanel.appendLog("[ " + player.getName() + " ] Dodged the attack!");
            System.out.println(player.getName() + " has dodged the attack!");
        } 
        // Normal attack
        else {
            int totalDamage = enemy.getbaseDmg() + random.nextInt(5); // 0–4 damage variation
            battleLogPanel.appendLog("[ " + enemy.getName() + " ] Dealt " + totalDamage + " damage to you!");
            System.out.println("Enemy dealt " + totalDamage + " damage to you!");
            player.damageCalculate(totalDamage);
        }
    }

    // Move enemy away from player
    @Override
    public void moveBackward(Entity player, Entity enemy) {
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() + moveDistance;
        if (newPosition > 20) {
            newPosition = 20;
        }
        enemy.setPosition(newPosition);
        battleLogPanel.appendLog("[ " + enemy.getName() + " ] Moved Backwards " + moveDistance + "m");
        System.out.println("Moved Backwards " + moveDistance + "m");
    }

    // Move enemy towards player
    @Override
    public void moveForward(Entity player, Entity enemy) {
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = enemy.getPosition() - moveDistance;

        if (newPosition < player.getPosition()) {
            int difference = enemy.getPosition() - player.getPosition();
            int toMove = moveDistance - difference;
            int temp = player.getPosition();
            enemy.setPosition(temp);
            int playerPosition = player.getPosition() - toMove;
            player.setPosition(playerPosition);

            if (player.getPosition() < 0) {
                player.setPosition(0);
                battleLogPanel.appendLog("[ " + enemy.getName() + " ] Has pushed you to the edge of the arena!");
                System.out.println("Enemy has pushed you to the edge of the arena!");
                if (enemy.getPosition() < 1) {
                    enemy.setPosition(1);
                    battleLogPanel.appendLog("[ " + enemy.getName() + " ] Has cornered you");
                    System.out.println("Enemy has cornered you");
                } else {
                    battleLogPanel.appendLog("[ " + enemy.getName() + " ] Moved towards you by " + difference + "m");
                    System.out.println("Enemy moved towards you by " + difference + "m");
                }
            } else {
                battleLogPanel.appendLog("[ " + enemy.getName() + " ] Has pushed you backwards by: " + toMove + "m");
                battleLogPanel.appendLog("[ " + enemy.getName() + " ] Moved towards you by: " + difference + "m");
                System.out.println("Enemy has pushed you backwards by: " + toMove + "m");
                System.out.println("Enemy moved towards you by: " + difference + "m");
            }
        } else {
            enemy.setPosition(newPosition);
            int distance = Entity.calculateDistance(player, enemy);
            if (distance < 1) {
                distance = 1;
            }
            battleLogPanel.appendLog("[ " + enemy.getName() + " ] Moved Forwards " + moveDistance + "m");
            System.out.println("Moved Forwards " + moveDistance + "m");
        }
    }

    // Heals the enemy
    @Override
    public void heal(Entity enemy) {
        int healAmount = random.nextInt(20) + 10;
        int newHealth = enemy.getHealth() + healAmount;
        if (newHealth > enemy.getMaxHealth()) {
            newHealth = enemy.getMaxHealth();
        }
        enemy.setHealth(newHealth);
        battleLogPanel.appendLog("[ " + enemy.getName() + " ] HP healed " + healAmount + "+");
        System.out.println(enemy.getName() + " HP healed " + healAmount + "+");
    }
}