package game.tools;

import game.model.Player;

import java.sql.*;
import java.util.ArrayList;
public class Database {
    /**
     * It let the client connect to the Database.
     *
     * @return connection to the Database
     */
    public Connection connect() {
        String url = "jdbc:sqlite:SnakeDB";
        Connection c = null;

        try {
            c = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }


    /**
     * Initialize the Database
     */
    public void makeTable() {
        Connection c = null;
        Statement stmt = null;


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SnakeDB");
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Snake " +
                    "(PLAYERNAME            VARCHAR(64) PRIMARY KEY     NOT NULL, " +
                    " GAMESPEED             VARCHAR(64)                 NOT NULL, " +
                    " GAMEDIFFICULTY        VARCHAR(64)                 NOT NULL, " +
                    " SCORE                  INT                 NOT NULL, " +
                    " STEPS                  INT                 NOT NULL " +")";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    /**
     * @return check if the Database is empty or not.
     */

    public boolean isTableEmpty() {
        String sql = "SELECT COUNT(*) FROM Snake";

        try (Connection c = this.connect();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.getInt(1) == 0) {
                System.out.println("The Tables does not have any gamer yet!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * @param PLAYERNAME     The name of the player
     * @param GAMESPEED      The game speed.
     * @param GAMEDIFFICULTY    The game difficulty
     * @param SCORE   The score of the player
     * @param STEPS   The steps of the play.
     * @return true if the player added correctly to the Database.
     */

    public boolean insertNewUser(String PLAYERNAME, String GAMESPEED,String GAMEDIFFICULTY, int SCORE, int STEPS) {

        String sql = "INSERT INTO Snake(PLAYERNAME, GAMESPEED,GAMEDIFFICULTY, SCORE,STEPS ) VALUES(?,?,?,?,?)";

        try (Connection c = this.connect(); PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, PLAYERNAME);
            pstmt.setString(2, GAMESPEED);
            pstmt.setString(3,GAMEDIFFICULTY);
            pstmt.setInt(4, SCORE);
            pstmt.setInt(5,STEPS);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * @param name the username of the player
     * @return true if the user is in the Database.
     */

    public boolean isUserInDB(String name) {

        String sql = "SELECT PLAYERNAME FROM Snake";

        try (Connection c = this.connect();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                if (rs.getString("PLAYERNAME").equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Updating the score of the player after winning a game.
     *
     * @param name       username
     * @param newScore the new score of the player.
     * @param  newSteps the new steps of the player.
     */

    public void updateUser(String name, int newScore, int newSteps,String gameDifficulty, String gameSpeed) {
        String sql = "UPDATE Snake SET SCORE = ?, STEPS= ?, GAMEDIFFICULTY = ?, GAMESPEED = ?"
                + "WHERE PLAYERNAME = ?";

        try (Connection c = this.connect();
             PreparedStatement pstmt = c.prepareStatement(sql)) {

            pstmt.setInt(1, newScore);
            pstmt.setInt(2,newSteps);
            pstmt.setString(3, gameDifficulty);
            pstmt.setString(4, gameSpeed);
            pstmt.setString(5, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateUserGameDifficulty(String name, String gameDifficulty) {
        String sql = "UPDATE Snake SET GAMEDIFFICULTY = ?"
                + "WHERE PLAYERNAME = ?";

        try (Connection c = this.connect();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, gameDifficulty);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUserGameSpeed(String name,String gameSpeed) {
        String sql = "UPDATE Snake SET GAMESPEED = ?"
                + "WHERE PLAYERNAME = ?";

        try (Connection c = this.connect();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, gameSpeed);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Player getPlayer(String name) {

        String sql = "SELECT * "
                + "FROM Snake WHERE PLAYERNAME = ?";
        Player player = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,name);
            ResultSet resultSet  = pstmt.executeQuery();

            while (resultSet.next()) {
                player = new Player(resultSet.getString(1),resultSet.getInt(4),resultSet.getInt(5),EnumTranslator.GameDifficultyStringToEnum(resultSet.getString(2)),EnumTranslator.GameSpeedStringToEnum(resultSet.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return player;
    }

    /**
     * Giving back the score of the user from the Database.
     *
     * @param name user name.
     * @return the score of the user.
     */

    public int getUserScore(String name) {

        String sql = "SELECT PLAYERNAME, SCORE FROM Snake";

        try (Connection c = this.connect();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("PLAYERNAME").equals(name)) {
                    int rsPunkte = rs.getInt(2);
                    return rsPunkte;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }



    /**
     * @return the 20 best players.
     */

    public ArrayList<Player> players() {

        String request = "SELECT * FROM Snake ORDER BY SCORE DESC ,STEPS DESC LIMIT 12";
        ArrayList<Player> players = new ArrayList<Player>();
        try (Connection c = this.connect();
             Statement stmtOrder = c.createStatement();
             ResultSet resultSet = stmtOrder.executeQuery(request);) {
            while (resultSet.next()){
                players.add(new Player(resultSet.getString(1),resultSet.getInt(4),resultSet.getInt(5),EnumTranslator.GameDifficultyStringToEnum(resultSet.getString(2)),EnumTranslator.GameSpeedStringToEnum(resultSet.getString(3))));
            }
            return players;
        }
        catch (Exception e){

        }
        return players;
    }
    public static void main(String[] args) {

    }
}