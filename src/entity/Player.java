/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.ArrayList;
/**
 *
 * @author Shawn lee
 */
public class Player extends Entity{
    //variables for the player
    private int gold;
    private int exp;
    private int skillpoints;

    public Player(String name, int level, String entityClass){
        super(name, level, entityClass);
        this.gold = 0;
        this.exp = 0;
        this.skillpoints = 0;
    }

    public Player(String name, int level, int exp, int gold, String entityClass){
        super(name, level, entityClass);
        this.gold = gold;
        this.exp = exp;

    }

    //add gold to player
    public void addGold(int money) {
        this.gold += money;
    }

    //remove gold from player
    public void removeGold(int money){
        if(gold >= money){
            this.gold = this.gold - money;
        }
        else{
            System.out.println("Not enough gold to remove!");
        }
    }
    //get the gold to the player
    public int getGold(){
        return this.gold;
    }

    //add exp to player
    public void addExp(int exp){
        this.exp += exp;
        addSkillPoints();
    }

    //get the exp to the player
    public int getExp(){
        return this.exp;
    }

    //Skillpoint scalling when leveling up, results exp and adds level
    public void addSkillPoints(){
        int expNeeded = 100 * this.level * this.level;
        if (this.exp >= expNeeded){
            this.skillpoints++;
            this.exp = 0;
            this.level++;
        }
        this.skillpoints++;
    }

    //Remove skillpoints from player
    public void removeSkillPoints(int points){
        if(this.skillpoints > 0){
            this.skillpoints -= points;
        }
    }
    
    public int getSkillPoints(){
        return this.skillpoints;
    }

    public void statsDisplay(Player player){
        System.out.println("__________" + this.name + "__________");
        System.out.println("          Exp: " + this.exp);
        System.out.println("        Level: " + this.level);
        System.out.println("         Gold: " + this.gold);
        System.out.println("        Class: " + this.entityClass);
        System.out.println();
        
        System.out.println("       Damage: " + player.getbaseDmg() + " (type 1 to add skillpoints)");
        System.out.println("           HP: " + player.getMaxHealth() + " (type 2 to add skillpoints)");
        System.out.println("        Range: " + player.getAttackRange() + " (type 3 to add skillpoints)");
        System.out.println();
        
        
        
    }

    @Override
    public String toString() {
        return this.name + " " + this.level + " " + this.exp + " " + this.gold + " " + this.entityClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
   //Increase base damage after using a skillpoint 
   public void skillpointIncreaseDamage(){
       if(this.skillpoints > 0){
           this.setbaseDmg(1 + this.getbaseDmg());
           this.removeSkillPoints(1);
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
   }
   
   //Increase base max health after using a skillpoint 
    public void skillpointIncreaseHealth(){
       if(this.skillpoints > 0){
           this.setMaxHealth(1 + this.getMaxHealth());
           this.removeSkillPoints(1);
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
    }
    
    //Increase base attack range after using a skillpoint 
    public void skillpointIncreaseRange(){
        if(this.skillpoints > 0){
           if(this.getAttackRange() < 8){
                this.setAttackRange(1 + this.getAttackRange());
                this.removeSkillPoints(1);
           }
           else{
               System.out.println("Max base attack Range reached!\n");
           }
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
   }


}
