/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.InputMismatchException;
import java.util.Scanner;

import player_management.AccessFile;

/**
 *
 * @author Shawn lee
 */
public class Player extends Entity{
    //variables for the player
    private int gold;
    private int exp;
    private int skillpoints;
    private PlayerStats playerstats;
    private AccessFile file;

    public Player(String name, int level, String entityClass) {
        super(name, level, entityClass);
        this.gold = 0;
        this.exp = 0;
        this.playerstats = new PlayerStats(this.name, 0, 0, 0);
        this.skillpoints = this.level - this.playerstats.getUsedSkillPoints();
    }

    public Player(String name, int level, int exp, int gold, String entityClass) {
        super(name, level, entityClass);
        this.gold = gold;
        this.exp = exp;
        this.playerstats = new PlayerStats(this.name, 0, 0, 0);
        this.skillpoints = this.level - this.playerstats.getUsedSkillPoints();
    }

    public void loadPlayerStats(AccessFile file) {
        this.file = file;
        this.playerstats = file.loadPlayerStats(this.name);
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
        addLevel();
    }

    //get the exp to the player
    public int getExp(){
        return this.exp;
    }

    //Skillpoint scalling when leveling up, results exp and adds level
    public void addLevel(){
        int expNeeded = 100 * this.level * this.level;
        if (this.exp >= expNeeded){
            this.exp = 0;
            this.level++;
        }
    }

    //Remove skillpoints from player
    public void removeSkillPoints(int points){
        if(this.skillpoints > 0){
            this.skillpoints -= points;
        }
    }

    public int getSkillPoints(){
        return (this.level - playerstats.getUsedSkillPoints());
    }

    public void statsDisplay(Player player){
        System.out.println("__________" + this.name + "__________");
        System.out.println("          Exp: " + this.exp);
        System.out.println("        Level: " + this.level);
        System.out.println("         Gold: " + this.gold);
        System.out.println("        Class: " + this.entityClass);
        System.out.println();

        System.out.println("       Damage: " + player.getbaseDmg() + "[" + this.playerstats.getDamageSkillPoints() + "+] (type 1 to add skillpoints)");
        System.out.println("           HP: " + player.getMaxHealth() + "[" + this.playerstats.getHealthSkillPoints() + "+] (type 2 to add skillpoints)");
        System.out.println("        Range: " + player.getAttackRange() + "[" + this.playerstats.getRangeSkillPoints() + "+] (type 3 to add skillpoints)");
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
   public void skillpointIncreaseDamage(AccessFile file){
       if(this.skillpoints > 0){
           this.setbaseDmg(1 + this.getbaseDmg());
           //this.removeSkillPoints(1);
           //this.playerstats.setDamageSkillPoints( this.playerstats.getDamageSkillPoints() + 1);
           this.file.updateDamagePoints(this.name);
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
   }

   //Increase base max health after using a skillpoint
    public void skillpointIncreaseHealth(AccessFile file){
       if(this.skillpoints > 0){
           this.setMaxHealth(1 + this.getMaxHealth());
           //this.removeSkillPoints(1);
           //this.playerstats.setHealthSkillPoints( this.playerstats.getHealthSkillPoints() + 1);
           this.file.updateHealthPoints(this.name);
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
    }

    //Increase base attack range after using a skillpoint
    public void skillpointIncreaseRange(AccessFile file){
        if(this.skillpoints > 0){
           if(this.getAttackRange() < 8){
               this.setAttackRange(1 + this.getAttackRange());
               //this.removeSkillPoints(1);
               //this.playerstats.setRangeSkillPoints( this.playerstats.getRangeSkillPoints() + 1);
               this.file.updateRangePoints(this.name);
           }
           else{
               System.out.println("Max base attack Range reached!\n");
           }
       }
       else{
           System.out.println("Not enough skillpoints\n");
       }
   }

    //Checks which base stats the skill point would increase
    public void CheckSkillPoints(Player player, Scanner scanner, AccessFile file){
        while(getSkillPoints() > 0){ // only enter if player has skill points
            System.out.print("Skillpoints (" + getSkillPoints() + " available) [-1 to exit]: ");
            int input;

            try {
                input = scanner.nextInt();

                //Exit the stats interface if input is -1
                if(input == -1){
                    break;
                }

                //Depending on the input number it adds either damage, health or range
                switch(input){
                    case 1 -> player.skillpointIncreaseDamage(file);
                    case 2 -> player.skillpointIncreaseHealth(file);
                    case 3 -> player.skillpointIncreaseRange(file);
                    default -> System.out.println("Invalid input! Enter 1, 2, or 3.\n");
                }

            } catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.\n");
                scanner.next(); // clear invalid input
            }
        }

        //Exit when theres no skillpoints avialable
        if(getSkillPoints() == 0){
            System.out.println("No skill points remaining!\n");
        }
    }


}
