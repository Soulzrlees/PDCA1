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
    //add gold to player
    public void addGold(int money) {
        this.gold += money;
    }
 
    //remove gold from player
    public void removeGold(int money){
        if(gold >= money){
            this.gold = this.gold - money;
        }
    }
    //get the gold to the player
    public int getGold(){
        return this.gold;
    }
    
    

    
    
}
