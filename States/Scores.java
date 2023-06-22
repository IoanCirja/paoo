package PaooGame.States;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.DataBaseWriter.DB_URL_SCORES;
import static PaooGame.Graphics.Assets.scores;

// leaderboards state + niste butoane
public class Scores extends State {

    private ArrayList<Button> buttons;

    public Scores(RefLinks refLink) {
        super(refLink);
        buttons = new ArrayList<>();

        buttons.add(new Button(750, 495, 150, 50, "Return"));
    }

    @Override
    public void Update() {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();

        int windowX = refLink.GetGame().getGameWindow().getX();
        int windowY = refLink.GetGame().getGameWindow().getY();

        int mouseX = (int) (mousePos.getX() - windowX);
        int mouseY = (int) (mousePos.getY() - windowY) - 40;

        for (Button button : buttons) {
            button.update(mouseX, mouseY);

            if (button.contains(mouseX, mouseY)) {
                if (button.getText().equals("Return")) {
                    Timer exitTimer = new Timer(500, e -> State.SetState(refLink.GetGame().getMenuState()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();
                }


            }
        }
    }



    @Override
    public void Draw(Graphics g) {
        //deleteAllScores();
        g.drawImage(scores, 0, 0, 1280, 640, null);

        try (Connection connection = DriverManager.getConnection(DB_URL_SCORES)) {
            String query = "SELECT * FROM SCORES ORDER BY score DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            int y = 74;

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                int score = resultSet.getInt("score");
                int kills = resultSet.getInt("kills");
                int lives = resultSet.getInt("lives");
                int stars = resultSet.getInt("stars");
                String note = resultSet.getString("notes");


                Font cartoonFont = new Font("Comic Sans MS", Font.BOLD, 15);
                g.setFont(cartoonFont);

                g.setColor(new Color(211, 211, 211));




                g.drawString(" * Date: " + date + ", Kills: " + score + ", Score: " + kills + ", Lives: " + lives + ", Stars: " + stars, 480, y);

                y += 30;
                g.drawString("                          Notes: " + note, 480, y);
                y += 30;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving scores from database: " + e.getMessage());
        }

        for (Button button : buttons) {
            button.draw(g);
        }
    }


}
