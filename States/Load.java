package PaooGame.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

import PaooGame.Items.Coin;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.DataBaseWriter.DB_URL_LOAD;
import static PaooGame.Graphics.Assets.menu;
import static PaooGame.Items.Box.stars;

public class Load extends State {

    private ArrayList<Button> buttons;

    public Load(RefLinks refLink) {
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(900, 160, 150, 50, "Save 1"));
        buttons.add(new Button(900, 240, 150, 50, "Save 2"));
        buttons.add(new Button(900, 320, 150, 50, "Save 3"));
        buttons.add(new Button(900, 400, 150, 50, "Return"));
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
                } else if (button.getText().equals("Save 1")) {
                    try (Connection connection = DriverManager.getConnection(DB_URL_LOAD)) {
                        String selectQuery = "SELECT * FROM LOAD ORDER BY rowid LIMIT 1";
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            int level = resultSet.getInt("level");
                            Hero.kills = resultSet.getInt("kills");
                            Coin.score = resultSet.getInt("score");
                            stars = resultSet.getInt("stars");
                            refLink.GetGame().getPlayState().GetHero().setLife(resultSet.getInt("lives"));
                            if (level == 1) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl1());
                                refLink.SetMap(refLink.GetGame().getLvl11().getMap());// Assuming getLvl1() returns the desired state for level 1
                            } else if (level == 2) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl2());
                                refLink.SetMap(refLink.GetGame().getLvl22().getMap());// Assuming getLvl2() returns the desired state for level 2
                            } else if (level == 3) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl3());
                                refLink.SetMap(refLink.GetGame().getLvl33().getMap()); // Assuming getLvl3() returns the desired state for level 3
                            }
                        }

                        System.out.println("Data retrieved successfully.");
                    } catch (SQLException ex) {
                        System.err.println("Error retrieving data from database: " + ex.getMessage());
                    }
                } else if (button.getText().equals("Save 2")) {
                    try (Connection connection = DriverManager.getConnection(DB_URL_LOAD)) {
                        String selectQuery = "SELECT * FROM LOAD ORDER BY rowid LIMIT 1 OFFSET 1";
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            int level = resultSet.getInt("level");
                            Hero.kills = resultSet.getInt("kills");
                            Coin.score = resultSet.getInt("score");
                            stars = resultSet.getInt("stars");
                            refLink.GetGame().getPlayState().GetHero().setLife(resultSet.getInt("lives"));
                            if (level == 1) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl1());
                                refLink.SetMap(refLink.GetGame().getLvl11().getMap());// Assuming getLvl1() returns the desired state for level 1
                            } else if (level == 2) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl2());
                                refLink.SetMap(refLink.GetGame().getLvl22().getMap());// Assuming getLvl2() returns the desired state for level 2
                            } else if (level == 3) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl3());
                                refLink.SetMap(refLink.GetGame().getLvl33().getMap()); // Assuming getLvl3() returns the desired state for level 3
                            }
                        }

                        System.out.println("Data retrieved successfully.");
                    } catch (SQLException ex) {
                        System.err.println("Error retrieving data from database: " + ex.getMessage());
                    }
                } else if (button.getText().equals("Save 3")) {
                    try (Connection connection = DriverManager.getConnection(DB_URL_LOAD)) {
                        String selectQuery = "SELECT * FROM LOAD ORDER BY rowid LIMIT 1 OFFSET 2";
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            int level = resultSet.getInt("level");
                            Hero.kills = resultSet.getInt("kills");
                            Coin.score = resultSet.getInt("score");
                            stars = resultSet.getInt("stars");
                            refLink.GetGame().getPlayState().GetHero().setLife(resultSet.getInt("lives"));
                            if (level == 1) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl1());
                                refLink.SetMap(refLink.GetGame().getLvl11().getMap());// Assuming getLvl1() returns the desired state for level 1
                            } else if (level == 2) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl2());
                                refLink.SetMap(refLink.GetGame().getLvl22().getMap());// Assuming getLvl2() returns the desired state for level 2
                            } else if (level == 3) {
                                refLink.GetGame().resetLevels(refLink);
                                State.SetState(refLink.GetGame().getLvl3());
                                refLink.SetMap(refLink.GetGame().getLvl33().getMap()); // Assuming getLvl3() returns the desired state for level 3
                            }
                        }

                        System.out.println("Data retrieved successfully.");
                    } catch (SQLException ex) {
                        System.err.println("Error retrieving data from database: " + ex.getMessage());
                    }
                }
            }

        }
    }




    @Override
    public void Draw(Graphics g) {
        g.drawImage(menu, 0, 0, 1280, 640, null);

        for (Button button : buttons) {
            button.draw(g);
        }

    }

}
