/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package entity;

import action.EnemyAction;
import gui.battle.BattleLogPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shawn lee
 */
public class EnemyTest {

    private Player testPlayer;
    private Enemy testEnemy;
    private EnemyAction dummyAction;

    @Before
    public void setUp() {
        testPlayer = new Player("Hero", 1, 0, 0, "melee");
        testEnemy = new Enemy("Skeleton", 1, "melee");
        BattleLogPanel dummyPanel = new BattleLogPanel();
        dummyAction = new  EnemyAction(dummyPanel) {
            @Override
            public void attack(Entity self, Entity target, int round) {}
            @Override
            public void moveForward(Entity target, Entity self) {}
            @Override
            public void moveBackward(Entity target, Entity self) {}
            @Override
            public void heal(Entity self) {}
        };
    }

    @Test
    public void testCreateEnemy() {
        Enemy e = Enemy.createEnemy(testPlayer);
        assertNotNull(e);
        assertTrue(e.getLevel() >= testPlayer.getLevel() && e.getLevel() <= testPlayer.getLevel() + 2);
        assertTrue(e.getName().matches("Skeleton|High Orc|Assassin|Monkey|Devil|Witch"));
        assertTrue(e.getClasses().matches("melee|ranger|mage"));
    }

    @Test
    public void testAction() {
        // Just make sure it does not throw an exception
        testEnemy.Action(testPlayer, dummyAction, 1);
    }

    @Test
    public void testGetEnemyImage() {
        Enemy skeleton = new Enemy("Skeleton", 1, "melee");
        assertEquals("images/Skeleton_enemy.png", skeleton.getEnemyImage(skeleton));

        Enemy witch = new Enemy("Witch", 1, "mage");
        assertEquals("images/Witch_enemy.png", witch.getEnemyImage(witch));

        Enemy unknown = new Enemy("Unknown", 1, "mage");
        assertNull(unknown.getEnemyImage(unknown));
    }
}
