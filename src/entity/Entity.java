/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author fatehbhular
 */
public class Entity {
    // Important variables for each entity
    protected String name;
    protected int level;
    protected String entityClass;
    protected int health;
    protected int maxHealth;
    protected int baseDmg;
    protected boolean isDefeated;
    protected int distance;
    
    // Variables used to calculate stats of the entity
    protected int baseHealthPerLevel;
    protected int baseDmgPerLevel;
    
    public Entity (String name, int level, String entityClass) {
        this.name = name;
        this.level = level;
        this.entityClass = entityClass;
        this.isDefeated = false;
        
        setClassStats();
        calculateStatsFromLevel();
    }
    
    // Sets the health and damage of the entity depending on its class
    protected void setClassStats() {
        switch (entityClass.toLowerCase()) {
            case "melee":
                this.baseHealthPerLevel = 100;
                this.baseDmgPerLevel = 20;
                break;
            case "ranger":
                this.baseHealthPerLevel = 80;
                this.baseDmgPerLevel = 15;
                break;
            case "mage":
                this.baseHealthPerLevel = 60;
                this.baseDmgPerLevel = 10;
                break;
            default:
                this.baseHealthPerLevel = 80;
                this.baseDmgPerLevel = 15;
                break;
        }
    }
    
    // Calculates the health and damage of the entity depending on its level
    protected void calculateStatsFromLevel() {
        this.maxHealth = baseHealthPerLevel + (level * 5);
        this.health = maxHealth;
        this.baseDmg = baseDmgPerLevel + (level * 2);
    }
}
