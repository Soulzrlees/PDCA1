/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("Shawn", 1, 0, 0, "melee");
    }
    
    @Test
    public void testGetName() {
        assertEquals("Shawn", player.getName());
    }

    @Test
    public void testGetClasses() {
        assertEquals("melee", player.getClasses());
    }

    @Test
    public void testGetGold() {
        assertEquals(0, player.getGold());
        player.addGold(50);
        assertEquals(50, player.getGold());
        player.removeGold(20);
        assertEquals(30, player.getGold());
    }

    @Test
    public void testGetExpAndAddExp() {
        assertEquals(0, player.getExp());
        player.addExp(50);
        assertEquals(50, player.getExp());
    }

    @Test
    public void testLevelAndSkillPoints() {
        assertEquals(1, player.getLevel());
        assertEquals(1, player.getskillPoints());

        player.addExp(100); 
        assertEquals(2, player.getLevel());
        assertEquals(2, player.getskillPoints());
    }

    @Test
    public void testPlayerStats() {
        PlayerStats stats = player.getPlayerStats();
        assertNotNull(stats);
    }

    @Test
    public void testSkillpointIncreaseDamage() {
        int baseDmgBefore = player.getbaseDmg();
        player.skillpointIncreaseDamage();
        assertEquals(baseDmgBefore + 1, player.getbaseDmg());
        assertEquals(0, player.getskillPoints()); 
    }

    @Test
    public void testSkillpointIncreaseHealth() {
        int healthBefore = player.getMaxHealth();
        player.skillpointIncreaseHealth();
        assertEquals(healthBefore + 1, player.getMaxHealth());
        assertEquals(0, player.getskillPoints());
    }

    @Test
    public void testSkillpointIncreaseRange() {
        int rangeBefore = player.getAttackRange();
        player.skillpointIncreaseRange();
        assertEquals(rangeBefore + 1, player.getAttackRange());
        assertEquals(0, player.getskillPoints());
    }

    @Test
    public void testEqualsAndHashCode() {
        Player samePlayer = new Player("Shawn", 1, 0, 0, "melee");
        Player differentPlayer = new Player("Alex", 1, 0, 0, "melee");

        assertTrue(player.equals(samePlayer));
        assertFalse(player.equals(differentPlayer));
        assertEquals(player.hashCode(), samePlayer.hashCode());
    }

    @Test
    public void testToString() {
        String str = player.toString();
        assertTrue(str.contains("Shawn"));
        assertTrue(str.contains("1"));
        assertTrue(str.contains("0"));
        assertTrue(str.contains("melee"));
    }

}
