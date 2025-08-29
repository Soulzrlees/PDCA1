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
    private ArrayList<String> inventory;

    public Player(String name, int level, String entityClass){
        super(name, level, entityClass);
        this.gold = 0;
        this.exp = 0;
        this.skillpoints = 0;
        this.inventory = new ArrayList<>();
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
            this.level ++;
        }
    }

    //Remove skillpoints from player
    public void removeSkillPoints(int points){
        if(this.skillpoints > 0){
            this.skillpoints -= points;
        }
    }

    public void statsDisplay(Player player){
        System.out.println("__________" + this.name + "__________");
        System.out.println("          Exp: " + this.exp);
        System.out.println("        Level: " + this.level);
        System.out.println("  Skillpoints: " + this.skillpoints);
        System.out.println("         Gold: " + this.gold);
        System.out.println("        Class: " + this.entityClass);
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


}
