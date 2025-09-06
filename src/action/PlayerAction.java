package action;

import java.util.Random;
import entity.Entity;

/**
 *
 * @author Shawn lee
 **/

public class PlayerAction implements Action_Interface{
    private Random random;
    private int healCount;

    public PlayerAction(){
        this.random = new Random();
        this.healCount = 0;
    }

    //Finds the total damage
    @Override
    public void attack(Entity enemy, Entity player, int round){
        //Based on the position of the player to the enemy, if it range is less than the Position than attack does not hit.
        if(Entity.calculateDistance(enemy, player) > player.getAttackRange()){
            System.out.println("Attack missed due to distance!");
        }
        //If getEvade is valid than attack will be dodged so no damage taken.
        else if(enemy.getEvade() && round != 1){
            System.out.println("Enemy has dodged the attack!");
        }
        else {
            int totalDamage = player.getbaseDmg() + random.nextInt(5);
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
            System.out.println("You can't move further in this direction");
        }
        player.setPosition(newPosition);
        int distance = Entity.calculateDistance(player, enemy);
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
                System.out.println("You have push the enemy to the edge of the arena!");
                if (player.getPosition() > 19) {
                    player.setPosition(19);
                    System.out.println("You have cornered the enemy");
                } else {
                    System.out.println("You moved towards the enemy by: " + difference + "m");
                }
            } else {
                System.out.println("You have pushed the enemy backwards by: " + toMove + "m");
                System.out.println("You moved towards the enemy by: " + difference + "m");
            }
        } else {
            player.setPosition(newPosition);
            int distance = Entity.calculateDistance(player, enemy);
            if(distance < 1){
                distance = 1;
            }
            System.out.println("Moved forwards " + moveDistance + "m");
        }
    }
    // heals the player (only 2 times allowed)
    public void heal(Entity player) {
        int newHealth = player.getHealth();
        int healAmount = 0;
        if (healCount >= 2){
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

        System.out.println(player.getName() + " HP healed " + healAmount + "+");

    }
}