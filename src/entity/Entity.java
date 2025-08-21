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
    protected int position;

    // Variables used to calculate stats of the entity
    protected int baseHealthPerLevel;
    protected int baseDmgPerLevel;

    public Entity (String name, int level, String entityClass) {
        this.name = name;
        this.level = level;
        this.entityClass = entityClass;
        this.isDefeated = false;
        this.position = 1;

        setClassStats();
        calculateStatsFromLevel();
    }

    // Sets the health and damage to the entity depending on its class
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

    //Determines the health after damage is taken
    public int damageCalculate(int damage) {
        this.health -= damage;
        if(this.health <= 0){
            this.isDefeated = true;
            this.health = 0;
        }
        return health;
    }

    public int getbaseDmg() {
        return this.baseDmg;
    }

    public int getHealth() {
        return this.health;
    }
    
    public void setHealth(int health){
        this.health = health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public String getName() {
        return this.name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() { return this.level; }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //Find the distance from the enemy.
    public int calculateDistance(Entity a, Entity b) {
        //If the position difference is lower than 0 then the position between each other would result in 0.
        int position_difference  = Math.abs(a.getPosition() - b.getPosition());

        //Capping the distance between each other to be 10.
        if(position_difference > 10){
            position_difference = 10;
        }
        return position_difference;
    }

}
