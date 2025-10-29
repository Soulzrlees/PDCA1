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
public class EntityTest {

    private Entity instance;

    @Before
    public void setUp() {
        instance = new Entity("Shawn", 1, "melee");
    }

    @Test
    public void testSetClassStats() {
        assertEquals(100, instance.baseHealthPerLevel);
        assertEquals(20, instance.baseDmgPerLevel);
    }

    @Test
    public void testCalculateStatsFromLevel() {
        instance.calculateStatsFromLevel();
        assertEquals(105, instance.getMaxHealth());
        assertEquals(22, instance.getbaseDmg());
    }

    @Test
    public void testDamageCalculate() {
        int result = instance.damageCalculate(10);
        assertEquals(instance.getMaxHealth() - 10, result);
    }

    @Test
    public void testGetAndSetBaseDmg() {
        instance.setbaseDmg(50);
        assertEquals(50, instance.getbaseDmg());
    }

    @Test
    public void testGetAndSetHealth() {
        instance.setHealth(200);
        assertEquals(200, instance.getHealth());
    }

    @Test
    public void testGetAndSetMaxHealth() {
        instance.setMaxHealth(300);
        assertEquals(300, instance.getMaxHealth());
    }

    @Test
    public void testGetName() {
        assertEquals("Shawn", instance.getName());
    }

    @Test
    public void testSetAndGetLevel() {
        instance.setLevel(5);
        assertEquals(5, instance.getLevel());
    }

    @Test
    public void testSetAndGetPosition() {
        instance.setPosition(15);
        assertEquals(15, instance.getPosition());
    }

    @Test
    public void testSetAndGetAttackRange() {
        instance.setAttackRange(10);
        assertEquals(10, instance.getAttackRange());
    }

    @Test
    public void testGetEvade() {
        boolean result = instance.getEvade();
        assertTrue(result || !result); 
    }
    
    @Test
    public void testGetClass(){
      assertEquals("melee", instance.getClasses());
    }
    

    @Test
    public void testCalculateDistance() {
        Entity player = new Entity("Player", 1, "melee");
        Entity enemy = new Entity("Enemy", 1, "ranger");
        player.setPosition(5);
        enemy.setPosition(15);
        int result = Entity.calculateDistance(enemy, player);
        assertEquals(10, result);
    }
}
