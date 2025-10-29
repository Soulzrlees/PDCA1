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
public class PotionTest {

    private Potion strengthPotion;
    private Potion rangePotion;
    private Entity player;

    @Before
    public void setUp() {
        strengthPotion = new Potion("Mighty Potion", Potion.potionType.Strength, 2);
        rangePotion = new Potion("Long Shot", Potion.potionType.Range, 3);

        player = new Entity("TestPlayer", 1, "melee");
        player.setbaseDmg(10);
        player.setAttackRange(5);
        player.setLevel(2); 
    }

    @Test
    public void testGetName() {
        assertEquals("Mighty Potion", strengthPotion.getName());
        assertEquals("Long Shot", rangePotion.getName());
    }

    @Test
    public void testGetType() {
        assertEquals(Potion.potionType.Strength, strengthPotion.getType());
        assertEquals(Potion.potionType.Range, rangePotion.getType());
    }

    @Test
    public void testGetstatsBuff() {
        assertEquals(2, strengthPotion.getstatsBuff());
        assertEquals(3, rangePotion.getstatsBuff());
    }

    @Test
    public void testUsePotion_Strength() {
        strengthPotion.usePotion(player);
        assertEquals(14, player.getbaseDmg());
    }

    @Test
    public void testUsePotion_Range() {
        rangePotion.usePotion(player);
        assertEquals(8, player.getAttackRange());
    }

    @Test
    public void testToString() {
        assertEquals("Mighty Potion (Strength +2)", strengthPotion.toString());
        assertEquals("Long Shot (Range +3)", rangePotion.toString());
    }
}
