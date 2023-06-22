package PaooGame;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//for databases : LOAD AND SCORE + try/catch gestioneaza bazele de date
public class DataBaseWriter {
    public static final String DB_URL_SCORES = "jdbc:sqlite:Scores.db";
    public static final String DB_URL_LOAD = "jdbc:sqlite:Load.db";

    public static void writeToDatabaseScores(int score, int kills, int lives, int stars, String notes) {
        try (Connection connection = DriverManager.getConnection(DB_URL_SCORES)) {
            String date = getFormattedDateTime();

            //un nr max de linii ca sa arate frumos i njoc
            String deleteQuery = "DELETE FROM SCORES WHERE rowid NOT IN (SELECT rowid FROM SCORES ORDER BY rowid DESC LIMIT 6)";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();

            // Insert new row
            String insertQuery = "INSERT INTO SCORES (date, kills, score, lives, stars, notes) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, date);
            insertStatement.setInt(2, kills);
            insertStatement.setInt(3, score);
            insertStatement.setInt(4, lives);
            insertStatement.setInt(5, stars);
            insertStatement.setString(6, notes);
            insertStatement.executeUpdate();

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error writing to database: " + e.getMessage());
        }
    }


    public static void writeToDatabaseLoad(int level, int score, int kills, int stars, int lives) {
        try (Connection connection = DriverManager.getConnection(DB_URL_LOAD)) {
            // if a row with the same values already exists
            String selectQuery = "SELECT * FROM LOAD WHERE level = ? AND score = ? AND kills = ? AND stars = ? AND lives = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, level);
            selectStatement.setInt(2, score);
            selectStatement.setInt(3, kills);
            selectStatement.setInt(4, stars);
            selectStatement.setInt(5, lives);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                // max 3 rows
                String deleteQuery = "DELETE FROM LOAD WHERE rowid NOT IN (SELECT rowid FROM LOAD ORDER BY level DESC LIMIT 2)";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.executeUpdate();

                // Insert nwe roew
                String insertQuery = "INSERT INTO LOAD (level, score, kills, stars, lives) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, level);
                insertStatement.setInt(2, score);
                insertStatement.setInt(3, kills);
                insertStatement.setInt(4, stars);
                insertStatement.setInt(5, lives);
                insertStatement.executeUpdate();

                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Row with the same values already exists. Skipping insertion.");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error writing to database: " + e.getMessage());
        }
    }




    private static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }



    public static void printAllScores() {
        try (Connection connection = DriverManager.getConnection(DB_URL_SCORES)) {
            String query = "SELECT * FROM SCORES ORDER BY score DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("All Scores:");

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                int score = resultSet.getInt("score");
                int kills = resultSet.getInt("kills");
                int lives = resultSet.getInt("lives");

                System.out.println("Date: " + date + ", Score: " + score + ", Kills: " + kills + ", Lives: " + lives);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving scores from database: " + e.getMessage());
        }
    }


    public static void deleteAllScores() {
        try (Connection connection = DriverManager.getConnection(DB_URL_SCORES)) {
            String query = "DELETE FROM SCORES";
            Statement statement = connection.createStatement();
            int rowsDeleted = statement.executeUpdate(query);

            System.out.println(rowsDeleted + " rows deleted from the database.");
        } catch (SQLException e) {
            System.err.println("Error deleting scores from database: " + e.getMessage());
        }
    }
    public static void deleteAllLoads() {
        try (Connection connection = DriverManager.getConnection(DB_URL_LOAD)) {
            String query = "DELETE FROM LOAD";
            Statement statement = connection.createStatement();
            int rowsDeleted = statement.executeUpdate(query);

            System.out.println(rowsDeleted + " rows deleted from the database.");
        } catch (SQLException e) {
            System.err.println("Error deleting scores from database: " + e.getMessage());
        }
    }

}
