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
        stats = new PlayerStats("Shawn", 2, 3, 4);
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("Shawn", stats.getPlayerName());
    }

    @Test
    public void testGetDamageSkillPoints() {
        assertEquals(2, stats.getDamageSkillPoints());
    }

    @Test
    public void testSetDamageSkillPoints() {
        stats.setDamageSkillPoints(5);
        assertEquals(5, stats.getDamageSkillPoints());
    }

    @Test
    public void testGetHealthSkillPoints() {
        assertEquals(3, stats.getHealthSkillPoints());
    }

    @Test
    public void testSetHealthSkillPoints() {
        stats.setHealthSkillPoints(6);
        assertEquals(6, stats.getHealthSkillPoints());
    }

    @Test
    public void testGetRangeSkillPoints() {
        assertEquals(4, stats.getRangeSkillPoints());
    }

    @Test
    public void testSetRangeSkillPoints() {
        stats.setRangeSkillPoints(7);
        assertEquals(7, stats.getRangeSkillPoints());
    }

    @Test
    public void testGetUsedSkillPoints() {
        assertEquals(2 + 3 + 4, stats.getUsedSkillPoints());
    }

    @Test
    public void testToString() {
        assertEquals("Shawn 2 3 4", stats.toString());
    }
}