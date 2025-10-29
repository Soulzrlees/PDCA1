/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package action;

import entity.Entity;
import entity.Player;
import entity.Enemy;
import gui.battle.BattleLogPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fatehbhular
 */
public class PlayerActionTest {
    
    private PlayerAction playerAction;
    private Player testPlayer;
    private Enemy testEnemy;
    private BattleLogPanel testBattleLog;
    
    public PlayerActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testBattleLog = new BattleLogPanel();
        
        java.lang.reflect.Field field;
        try {
            field = BattleLogPanel.class.getDeclaredField("logTextArea");
            field.setAccessible(true);
            field.set(testBattleLog, new javax.swing.JTextArea());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        playerAction = new PlayerAction(testBattleLog);
        testPlayer = new Player("Hero", 1, 0, 0, "melee");
        testEnemy = new Enemy("Skeleton", 1, "melee");
    }
    
    @After
    public void tearDown() {
        testBattleLog = null;
        playerAction = null;
        testPlayer = null;
        testEnemy = null;
    }

    @Test
    public void testAttack_MissedDueToDistance() {
        testPlayer.setAttackRange(1);
        testPlayer.setPosition(0);
        testEnemy.setPosition(10);
        
        playerAction.attack(testEnemy, testPlayer, 1);
        
        String log = testBattleLog.getText();
        assertTrue("Player position must not go below 0", testPlayer.getPosition() >= 0);
    }
    
    @Test
    public void testMoveFoward() {
        testPlayer.setPosition(2);
        testEnemy.setPosition(4);
        playerAction.moveForward(testEnemy, testPlayer);
        
        assertTrue("Player should not move beyond enemy position", testPlayer.getPosition() <= testEnemy.getPosition());
    }
    
    @Test
    public void testMoveBackward() {
        testPlayer.setPosition(1);
        testEnemy.setPosition(4);
        playerAction.moveBackward(testEnemy, testPlayer);
        System.out.println(testPlayer.getPosition());
        
        assertTrue("Player should not move backwards of 0", testPlayer.getPosition() >= 0);
    }
    
    @Test
    public void testHeal() {
        testPlayer.setHealth(50);
        testPlayer.setMaxHealth(60);
        
        playerAction.heal(testPlayer);
        
        assertTrue("Player health should increase after healing", testPlayer.getHealth() > 50);
        assertTrue("Player health should not exceed max health", testPlayer.getHealth() <= 60);
        
        String log = testBattleLog.getText();
        assertTrue("Heal log should mention HP", log.contains("HP"));
    }
}
