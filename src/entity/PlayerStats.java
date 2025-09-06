package entity;

import player_management.AccessFile;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerStats {
    private String playerName;
    private int damagePoints;
    private int healthPoints;
    private int rangePoints;

    public PlayerStats(String playerName, int damagePoints, int healthPoints, int rangePoints) {
        this.playerName = playerName;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
        this.rangePoints = rangePoints;
    }
    // returns player's name
    public String getPlayerName() {
        return playerName;
    }
    // returns the player's damage skillpoints
    public int getDamageSkillPoints() {
        return damagePoints;
    }
    // sets the player's damage skillpoints
    public void setDamageSkillPoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }
    // returns the player's health skillpoints
    public int getHealthSkillPoints() {
        return healthPoints;
    }
    // sets the player's health skillpoints
    public void setHealthSkillPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    // returns the player's range skillpoints
    public int getRangeSkillPoints() {
        return rangePoints;
    }
    // sets the player's range skillpoints
    public void setRangeSkillPoints(int rangePoints) {
        this.rangePoints = rangePoints;
    }
    // returns how many skillpoints have been used
    public int getUsedSkillPoints() {
        return damagePoints + healthPoints + rangePoints;
    }
    // used for file i/o
    @Override
    public String toString() {
        return playerName + " " + damagePoints + " " + healthPoints + " " + rangePoints;
    }

}
