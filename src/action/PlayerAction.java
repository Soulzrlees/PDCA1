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

    public void stats(Entity player){
        System.out.println("Name = " + player.getName());
        System.out.println("Enemy Health = " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Enemy Position = " + player.getPosition() + "m");
    }

    //Finds the total damage
    @Override
    public void attack(Entity player){
        int totalDamage = player.getbaseDmg() + random.nextInt(10);
        System.out.println("You have dealt " + totalDamage + " damage !");

        player.damageCalculate(totalDamage);
        stats(player);
    }

    @Override
    public void moveForward(Entity enemy, Entity player){
        int moveDistance = random.nextInt(5) + 1;
        int newPosition = moveDistance + player.getPosition();
        //Making sure that the new Position is under or at 10
        if(newPosition > 10){
            newPosition = 10;
        }
        player.setPosition(newPosition);

        int distance = player.calculateDistance(enemy, player);
        System.out.println("Distance to " + player.getName() + ": " + distance + "m");
        stats(player);
    }
    //Finds out the distance the enemy travelled backwards
    @Override
    public void moveBackward(Entity enemy, Entity player){
        int moveDistancebackwards = random.nextInt(5) + 1;
        int newPosition = player.getPosition() - moveDistancebackwards;
        //Making sure that the new Position above 1 or at 1
        if(newPosition <= 1){
            newPosition = 1;
        }
        player.setPosition(newPosition);

        int distance = enemy.calculateDistance(player, enemy);
        if(distance < 1){
            distance = 1;
        }
        System.out.println("Distance to " + enemy.getName() + ": " + distance + "m");
        stats(player);
    }

    public void heal(Entity player) {
        int newHealth = player.getHealth();
        if (healCount > 2){
            System.out.println("You have used all your heals!");
        }
        else{
            int healAmount = random.nextInt(11) + 5;
            newHealth = player.getHealth() + healAmount;
            healCount++;
            // Increase health, but don't exceed max
            if (newHealth > player.getMaxHealth()) {
                newHealth = player.getMaxHealth();
            }
        }

        System.out.println(player.getName() + " HP healed " + newHealth + "+");
        stats(player);

    }

    public boolean evadetrue(Entity entity){
        int evadeProbability = random.nextInt(100);
        if(evadeProbability > 70){ //30% chance to evade
            return true;
        }
        else{
            return false;
        }
    }
}