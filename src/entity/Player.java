/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.exceptions.PlayerNotFoundException;
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
    // loads current player's stats from the player storage
    public void loadPlayerStats(AccessFile file) {
        this.file = file;
        this.playerstats = file.loadPlayerStats(this.name);
    }

    public String getName(){
        return this.name;
    }

    public String getClasses(){
        return this.entityClass;
    }
    //get the gold to the player
    public int getGold(){
        return this.gold;
    }
    
    //get the exp to the player
    public int getExp(){
        return this.exp;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public int getskillPoints(){
        return this.skillpoints;
    }

    public PlayerStats getPlayerStats(){
        return this.playerstats;
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

    //add exp to player
    public void addExp(int exp){
        this.exp += exp;
        addLevel();
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
    // gets the skillpoints of the current player from the playerstats storage
    public int getSkillPoints(Player player) throws PlayerNotFoundException {
        player = file.loadExistingPlayer(this.name);
        return (player.level - playerstats.getUsedSkillPoints());
    }
    // displays the player's stats and upgrades
    public void statsDisplay(Player player) throws PlayerNotFoundException {
        player = file.loadExistingPlayer(this.name);
        System.out.println("__________" + this.name + "__________");
        System.out.println("          Exp: " + player.exp);
        System.out.println("        Level: " + player.level);
        System.out.println("         Gold: " + player.gold);
        System.out.println("        Class: " + player.entityClass);
        System.out.println();

        System.out.println("       Damage: " + this.getbaseDmg() + "[" + this.playerstats.getDamageSkillPoints() + "+] (type 1 to add skillpoints)");
        System.out.println("           HP: " + this.getMaxHealth() + "[" + this.playerstats.getHealthSkillPoints() + "+] (type 2 to add skillpoints)");
        System.out.println("        Range: " + this.getAttackRange() + "[" + this.playerstats.getRangeSkillPoints() + "+] (type 3 to add skillpoints)");
        System.out.println();
    }
    // used for file i/o
    @Override
    public String toString() {
        return this.name + " " + this.level + " " + this.exp + " " + this.gold + " " + this.entityClass;
    }
    // equals and hashcode is used to compare when making new players
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
    public void CheckSkillPoints(Player player, Scanner scanner, AccessFile file) throws PlayerNotFoundException {
        while(getSkillPoints(file.loadExistingPlayer(this.getName())) > 0){ // only enter if player has skill points
            System.out.print("Skillpoints (" + getSkillPoints(file.loadExistingPlayer(this.getName())) + " available) [-1 to exit]: ");
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

        //Exit when there's no skillpoints avialable
        if(getSkillPoints(file.loadExistingPlayer(this.getName())) == 0){
            System.out.println("No skill points remaining!\n");
        }
    }

    
//New methods for the GUI___________________________________________________________________________________________
    
    
    //Method that switches the image icon depending on the class
    public String getPlayerImage(Player player) {
        String pClass = player.getClasses();
        return switch (pClass) {
            case "melee" -> "images/Knight_player.png";
            case "ranger" -> "images/Ranger_player.png";
            case "mage" -> "images/Mage_player.png";
            default -> "-1";
        };
    }
    
    //This loads the skillpoints used from the previous save
    public void setPlayerStats(PlayerStats stats) {
        this.playerstats = stats;
        this.skillpoints = this.level - this.playerstats.getUsedSkillPoints();
    }
}
