package action;

import entity.Player;
import entity.Enemy;
import gui.battle.BattleLogPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyActionTest {

    private EnemyAction enemyAction;
    private Enemy testEnemy;
    private Player testPlayer;
    private BattleLogPanel testBattleLog;

    @Before
    public void setUp() {
        testBattleLog = new BattleLogPanel();

        // Inject dummy JTextArea into BattleLogPanel
        try {
            java.lang.reflect.Field field = BattleLogPanel.class.getDeclaredField("logTextArea");
            field.setAccessible(true);
            field.set(testBattleLog, new javax.swing.JTextArea());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        enemyAction = new EnemyAction(testBattleLog);
        testEnemy = new Enemy("Skeleton", 1, "melee");
        testPlayer = new Player("Hero", 1, 0, 0, "melee");
    }

    @After
    public void tearDown() {
        enemyAction = null;
        testEnemy = null;
        testPlayer = null;
        testBattleLog = null;
    }

    // --- ATTACK TESTS ---

    @Test
    public void testAttack_MissedDueToDistance() {
        testEnemy.setAttackRange(1);
        testEnemy.setPosition(0);
        testPlayer.setPosition(10); // Player is far away

        enemyAction.attack(testEnemy, testPlayer, 1); // round = 1 avoids evade

        String log = testBattleLog.getText();
        assertTrue("Log should mention missed attack due to distance", log.contains("missed"));
    }

    @Test
    public void testAttack_HitsAndDealsDamage() {
        testEnemy.setAttackRange(10);
        testEnemy.setPosition(0);
        testPlayer.setPosition(5); // Within attack range

        int initialHealth = testPlayer.getHealth();

        enemyAction.attack(testEnemy, testPlayer, 1); // round = 1 ignores evade

        assertTrue("Player should take damage", testPlayer.getHealth() < initialHealth);
        String log = testBattleLog.getText();
        assertTrue("Log should mention damage dealt", log.contains("Dealt"));
    }

    // --- MOVEMENT TESTS ---

    @Test
    public void testMoveBackward() {
        testEnemy.setPosition(10);
        testPlayer.setPosition(2);

        enemyAction.moveBackward(testPlayer, testEnemy);

        assertTrue("Enemy should not move past position 20", testEnemy.getPosition() <= 20);
        String log = testBattleLog.getText();
        assertTrue("Log should mention moving backwards", log.contains("Backwards"));
    }

    @Test
    public void testMoveForward() {
        testEnemy.setPosition(8);
        testPlayer.setPosition(3);

        enemyAction.moveForward(testPlayer, testEnemy);

        assertTrue("Enemy should not move behind player", testEnemy.getPosition() >= testPlayer.getPosition());
        String log = testBattleLog.getText();
        assertTrue("Log should mention moving forwards or pushed", log.contains("Forwards") || log.contains("pushed"));
    }

    // --- HEAL TESTS ---

    @Test
    public void testHeal_Increases() {
        testEnemy.setHealth(50);
        testEnemy.setMaxHealth(60);

        enemyAction.heal(testEnemy);

        assertTrue("Enemy health should increase after healing", testEnemy.getHealth() > 50);
        assertTrue("Enemy health should not exceed max health", testEnemy.getHealth() <= 60);

        String log = testBattleLog.getText();
        assertTrue("Heal log should mention HP", log.contains("HP"));
    }
}