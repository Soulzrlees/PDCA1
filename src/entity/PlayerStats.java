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

    public String getPlayerName() {
        return playerName;
    }

    public int getDamageSkillPoints() {
        return damagePoints;
    }

    public void setDamageSkillPoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public int getHealthSkillPoints() {
        return healthPoints;
    }

    public void setHealthSkillPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getRangeSkillPoints() {
        return rangePoints;
    }

    public void setRangeSkillPoints(int rangePoints) {
        this.rangePoints = rangePoints;
    }

    public int getUsedSkillPoints() {
        return damagePoints + healthPoints + rangePoints;
    }

    @Override
    public String toString() {
        return playerName + " " + damagePoints + " " + healthPoints + " " + rangePoints;
    }

}
