package databases;

import java.sql.*;

import entity.Player;
import entity.PlayerStats;

public class DBOperation {
    private final DBManager db;

    public DBOperation(DBManager db) {
        this.db = db;
    }

    public void addPlayer(String username, int level, int exp, int gold, String className) {
        if (playerExists(username)) {
            System.out.println("Player already exists: " + username);
            return;
        }

        String sql = "INSERT INTO ACCOUNTS (USERNAME, LEVEL, EXP, GOLD, CLASS) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setInt(2, level);
            pstmt.setInt(3, exp);
            pstmt.setInt(4, gold);
            pstmt.setString(5, className);

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

            System.out.println("Player <" + username + "> added to database.");
        } catch (SQLException e) {
            System.err.println("Error adding player: " + e.getMessage());
            rollback();
        }
    }

    public void addPlayerStats(String username, int damage_points, int health_points, int range_points) {
        if (playerStatsExists(username)) {
            System.out.println("Player stats already exist for: " + username);
            return;
        }

        String sql = "INSERT INTO STATS (USERNAME, DAMAGE_POINTS, HEALTH_POINTS, RANGE_POINTS) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setInt(2, damage_points);
            pstmt.setInt(3, health_points);
            pstmt.setInt(4, range_points);

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

            System.out.println("PlayerStats <" + username + "> added to database.");
        } catch (SQLException e) {
            System.err.println("Error adding player stats: " + e.getMessage());
            rollback();
        }
    }

    public void updatePlayer(Player player) {
        String sql = "UPDATE ACCOUNTS SET LEVEL = ?, EXP = ?, GOLD = ? WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, player.getLevel());
            pstmt.setInt(2, player.getExp());
            pstmt.setInt(3, player.getGold());
            pstmt.setString(4, player.getName());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

            System.out.println("Player successfully updated!");
        } catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    public void updatePlayerStats(PlayerStats player) {
        String sql = "UPDATE STATS SET DAMAGE_POINTS = ?, HEALTH_POINTS = ?, RANGE_POINTS = ? WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, player.getDamageSkillPoints());
            pstmt.setInt(2, player.getHealthSkillPoints());
            pstmt.setInt(3, player.getRangeSkillPoints());
            pstmt.setString(4, player.getPlayerName());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

            System.out.println("PlayerStats successfully updated!");
        } catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    public Player getPlayer(String username) {
        String sql = "SELECT * FROM ACCOUNTS WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            Player player = null;
            if (rs.next()) {
                player = new Player(
                        rs.getString("USERNAME"),
                        rs.getInt("LEVEL"),
                        rs.getInt("EXP"),
                        rs.getInt("GOLD"),
                        rs.getString("CLASS")
                );
                System.out.println("Player successfully loaded!");
            }

            rs.close();
            pstmt.close();
            return player;
        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }

    public PlayerStats getPlayerStats(String username) {
        String sql = "SELECT * FROM STATS WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            PlayerStats stats = null;
            if (rs.next()) {
                stats = new PlayerStats(
                        rs.getString("USERNAME"),
                        rs.getInt("DAMAGE_POINTS"),
                        rs.getInt("HEALTH_POINTS"),
                        rs.getInt("RANGE_POINTS")
                );
                System.out.println("PlayerStats successfully loaded!");
            }

            rs.close();
            pstmt.close();
            return stats;
        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }

    public boolean playerExists(String username) {
        String sql = "SELECT 1 FROM ACCOUNTS WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        } catch (SQLException e) {
            System.err.println("Check failed (ACCOUNTS): " + e.getMessage());
            return false;
        }
    }

    public boolean playerStatsExists(String username) {
        String sql = "SELECT 1 FROM STATS WHERE USERNAME = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            return exists;
        } catch (SQLException e) {
            System.err.println("Check failed (STATS): " + e.getMessage());
            return false;
        }
    }

    private void rollback() {
        try {
            db.getConnection().rollback();
        } catch (SQLException ex) {
            System.err.println("Rollback failed: " + ex.getMessage());
        }
    }

    public void displayAccounts() {
        String sql = "SELECT * FROM ACCOUNTS";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("________ACCOUNTS________");
            System.out.println("username | level | exp | gold | class");

            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int level = rs.getInt("LEVEL");
                int exp = rs.getInt("EXP");
                int gold = rs.getInt("GOLD");
                String className = rs.getString("CLASS");

                System.out.println(username + " | " + level + " | " + exp + " | " + gold + " | " + className);
            }

        } catch (SQLException e) {
            System.err.println("Error displaying accounts: " + e.getMessage());
        }
    }

    public void displayStats() {
        String sql = "SELECT * FROM STATS";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("________STATS________");
            System.out.println("username | damage | health | range");

            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int damage = rs.getInt("DAMAGE_POINTS");
                int health = rs.getInt("HEALTH_POINTS");
                int range = rs.getInt("RANGE_POINTS");

                System.out.println(username + " | " + damage + " | " + health + " | " + range);
            }

        } catch (SQLException e) {
            System.err.println("Error displaying stats: " + e.getMessage());
        }
    }
}

