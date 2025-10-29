/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shawn lee
 */
public class PlayerStatsTest {

    private PlayerStats stats;

    @Before
    public void setUp() {
        stats = new PlayerStats("Shawn", 5, 10, 3);
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("Shawn", stats.getPlayerName());
    }

    @Test
    public void testGetAndSetDamageSkillPoints() {
        assertEquals(5, stats.getDamageSkillPoints());
        stats.setDamageSkillPoints(8);
        assertEquals(8, stats.getDamageSkillPoints());
    }

    @Test
    public void testGetAndSetHealthSkillPoints() {
        assertEquals(10, stats.getHealthSkillPoints());
        stats.setHealthSkillPoints(12);
        assertEquals(12, stats.getHealthSkillPoints());
    }

    @Test
    public void testGetAndSetRangeSkillPoints() {
        assertEquals(3, stats.getRangeSkillPoints());
        stats.setRangeSkillPoints(6);
        assertEquals(6, stats.getRangeSkillPoints());
    }

    @Test
    public void testGetUsedSkillPoints() {
        assertEquals(5 + 10 + 3, stats.getUsedSkillPoints());
        stats.setDamageSkillPoints(7);
        stats.setHealthSkillPoints(8);
        stats.setRangeSkillPoints(5);
        assertEquals(7 + 8 + 5, stats.getUsedSkillPoints());
    }

    @Test
    public void testToString() {
        String expected = "Shawn 5 10 3";
        assertEquals(expected, stats.toString());
    }
}
