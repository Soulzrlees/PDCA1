/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package databases;

import entity.Player;
import entity.PlayerStats;
import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shawn lee
 */
public class DBOperationTest {

    private static DBManager dbManager;
    private static DBOperation dbOp;

    @BeforeClass
    public static void setUpClass() {
        // Connect to embedded Derby database
        dbManager = new DBManager("jdbc:derby:TEST_ACCOUNTS_DB;create=true", null, null);
        dbOp = new DBOperation(dbManager);

        // Create tables if not exist
        try {
            Connection conn = dbManager.getConnection();
            Statement stmt = conn.createStatement();

            // ACCOUNTS table
            stmt.executeUpdate("""
                    CREATE TABLE ACCOUNTS (
                        USERNAME VARCHAR(30) PRIMARY KEY,
                        LEVEL INT,
                        EXP INT,
                        GOLD INT,
                        CLASS VARCHAR(20)
                    )
                    """);

            // STATS table
            stmt.executeUpdate("""
                    CREATE TABLE STATS (
                        USERNAME VARCHAR(30) PRIMARY KEY,
                        DAMAGE_POINTS INT,
                        HEALTH_POINTS INT,
                        RANGE_POINTS INT
                    )
                    """);

            conn.commit();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Tables may already exist: " + e.getMessage());
        }
    }

    @AfterClass
    public static void tearDownClass() {
        if (dbManager != null) {
            dbManager.close();
        }
    }

    @Before
    public void setUp() {
        // Clean tables before each test
        try {
            Connection conn = dbManager.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM STATS");
            stmt.executeUpdate("DELETE FROM ACCOUNTS");
            conn.commit();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cleanup failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testAddAndGetPlayer() {
        dbOp.addPlayer("Shawn", 1, 0, 100, "melee");
        Player player = dbOp.getPlayer("Shawn");

        assertNotNull(player);
        assertEquals("Shawn", player.getName());
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getExp());
        assertEquals(100, player.getGold());
        assertEquals("melee", player.getClasses());
    }

    @Test
    public void testAddAndGetPlayerStats() {
        dbOp.addPlayerStats("Shawn", 2, 3, 4);
        PlayerStats stats = dbOp.getPlayerStats("Shawn");

        assertNotNull(stats);
        assertEquals("Shawn", stats.getPlayerName());
        assertEquals(2, stats.getDamageSkillPoints());
        assertEquals(3, stats.getHealthSkillPoints());
        assertEquals(4, stats.getRangeSkillPoints());
    }

    @Test
    public void testUpdatePlayer() {
        dbOp.addPlayer("Shawn", 1, 0, 100, "melee");
        Player player = dbOp.getPlayer("Shawn");

        player.setLevel(2);
        player.addExp(50);
        player.addGold(200);
        dbOp.updatePlayer(player);

        Player updated = dbOp.getPlayer("Shawn");
        assertEquals(2, updated.getLevel());
        assertEquals(50, updated.getExp());
        assertEquals(300, updated.getGold());
    }

    @Test
    public void testUpdatePlayerStats() {
        dbOp.addPlayerStats("Shawn", 2, 3, 4);
        PlayerStats stats = dbOp.getPlayerStats("Shawn");

        stats.setDamageSkillPoints(5);
        stats.setHealthSkillPoints(6);
        stats.setRangeSkillPoints(7);
        dbOp.updatePlayerStats(stats);

        PlayerStats updated = dbOp.getPlayerStats("Shawn");
        assertEquals(5, updated.getDamageSkillPoints());
        assertEquals(6, updated.getHealthSkillPoints());
        assertEquals(7, updated.getRangeSkillPoints());
    }

    @Test
    public void testPlayerExists() {
        assertFalse(dbOp.playerExists("Shawn"));
        dbOp.addPlayer("Shawn", 1, 0, 100, "melee");
        assertTrue(dbOp.playerExists("Shawn"));
    }

    @Test
    public void testPlayerStatsExists() {
        assertFalse(dbOp.playerStatsExists("Shawn"));
        dbOp.addPlayerStats("Shawn", 2, 3, 4);
        assertTrue(dbOp.playerStatsExists("Shawn"));
    }
}