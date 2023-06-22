package PaooGame.States;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.*;

import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import static PaooGame.Graphics.Assets.menu;
import static PaooGame.Items.Coin.score;

public class MenuState extends State {

    private ArrayList<Button> buttons;

    public MenuState(RefLinks refLink) {
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(900, 160, 150, 50, "Play"));
        buttons.add(new Button(900, 240, 150, 50, "Load"));
        buttons.add(new Button(900, 320, 150, 50, "Scores"));
        buttons.add(new Button(900, 400, 150, 50, "Exit"));
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
                if (button.getText().equals("Exit")) {
                    Timer exitTimer = new Timer(500, e -> System.exit(0));
                    exitTimer.setRepeats(false);
                    exitTimer.start();
                } else if (button.getText().equals("Play")) {
                    Timer exitTimer = new Timer(500, e ->  State.SetState(refLink.GetGame().getLvl1()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();


                } else if (button.getText().equals("Scores")) {
                    Timer exitTimer = new Timer(500, e ->  State.SetState(refLink.GetGame().getScores()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();

                }else if (button.getText().equals("Load")) {
                    Timer exitTimer = new Timer(500, e ->  State.SetState(refLink.GetGame().getLoad()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();

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
