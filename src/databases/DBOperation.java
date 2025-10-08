package databases;

import java.sql.*;

import entity.Player;
import entity.PlayerStats;

public class DBOperation {
    private DBManager db;

    public DBOperation(DBManager db) {
        this.db = db;
    }

    public void addPlayer(String username, int level, int exp, int gold, String className) {
        if (playerExists(username)) {
            System.out.println("Player already exists: " + username);
            return;
        }
        
        String sql_statement = "INSERT INTO ACCOUNTS (USERNAME, LEVEL, EXP, GOLD, CLASS) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql_statement);

            pstmt.setString(1, username);
            pstmt.setInt(2, level);
            pstmt.setInt(3, exp);
            pstmt.setInt(4, gold);
            pstmt.setString(5, className);

            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Player <" + username + "> added to database.");
        } catch (SQLException e) {
            System.err.println("Error adding player: " + e.getMessage());
            try {
                db.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Error rolling back: " + ex.getMessage());
            }
        }
    }

    //This methods updates the player data (Level, Exp and gold) to the database
    public void updatePlayer(Player player) {
        String sql_statement = "UPDATE ACCOUNTS SET lEVEL = ?, EXP = ?, GOLD = ? WHERE USERNAME = ?";
        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql_statement);


            pstmt.setInt(1, player.getLevel());
            pstmt.setInt(2, player.getExp());
            pstmt.setInt(3, player.getGold());
            pstmt.setString(4, player.getName());

            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Successfully updated!");
        }
        catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    //This method updates the player skillpoint stats to the database
    public void updatePlayerStats(PlayerStats player) {
        String sql_statement = "UPDATE STATS SET DAMANGE_POINTS = ?, HEALTH_POINTS = ?, RANGE_POINTS = ? WHERE USERNAME = ?";
        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql_statement);

            pstmt.setInt(1, player.getDamageSkillPoints());
            pstmt.setInt(2, player.getHealthSkillPoints());
            pstmt.setInt(3, player.getRangeSkillPoints());
            pstmt.setString(4, player.getPlayerName());

            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Successfully updated!");

        } catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    //This method gets the player account data from the database
    public Player getPlayer(String username){
        String sql_statement = "SELECT * FROM ACCOUNTS WHERE USERNAME = ?";

        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql_statement);

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                Player player = new Player(rs.getString("USERNAME"),
                        rs.getInt("LEVEL"),
                        rs.getInt("EXP"),
                        rs.getInt("GOLD"),
                        rs.getString("CLASS"));
                System.out.println("Player successfully loaded!");
                return player;
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }

    //This method gets the player skill points data from the database
    public PlayerStats getPlayerStats(String username){
        String sql_statement = "SELECT * FROM STATS WHERE USERNAME = ?";

        try{
            Connection conn = db.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql_statement);

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                PlayerStats playerstats = new PlayerStats(rs.getString("USERNAME"),
                        rs.getInt("DAMANGE_POINTS"),
                        rs.getInt("HEALTH_POINTS"),
                        rs.getInt("RANGE_POINTS"));
                System.out.println("PlayerStats successfully loaded!");
                return playerstats;
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }

    public void DisplayDB(){
        String sql = "SELECT * FROM ACCOUNTS";

        try{
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("________ACCOUNTS________");
            System.out.println("username | level | exp | gold | class");
            while(rs.next()){
                String username = rs.getString("USERNAME");
                int level = rs.getInt("LEVEL");
                int exp = rs.getInt("EXP");
                int gold = rs.getInt("GOLD");
                String classes = rs.getString("CLASS");

                System.out.println(username + " | " + level + " | " + exp + " | " + gold + " | " + classes);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean playerExists(String username) {
        String sql = "SELECT 1 FROM ACCOUNTS WHERE USERNAME = ?";
        Connection conn = null; // declare here
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Check failed: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null && !conn.getAutoCommit()) {
                    conn.rollback(); // rollback leftover read transaction
                }
            } catch (SQLException ignored) {}
        }
    }
}

