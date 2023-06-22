package PaooGame.States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.Graphics.Assets.end;
import static PaooGame.Graphics.Assets.menu;
//final state
public class EndState extends State {

    private ArrayList<Button> buttons;

    public EndState(RefLinks refLink) {
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(890, 220, 150, 50, "Brava!"));


        buttons.add(new Button(890, 370, 150, 50, "Exit"));
    }

    @Override
    public void Update() {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();

        int windowX = refLink.GetGame().getGameWindow().getX(); // X position of the game window
        int windowY = refLink.GetGame().getGameWindow().getY(); // Y position of the game window

        int mouseX = (int) (mousePos.getX() - windowX);
        int mouseY = (int) (mousePos.getY() - windowY) - 40;

        for (Button button : buttons) {
            button.update(mouseX, mouseY);

            if (button.contains(mouseX, mouseY)) {
                if (button.getText().equals("Exit")) {
                    Timer exitTimer = new Timer(500, e -> System.exit(0));
                    exitTimer.setRepeats(false);
                    exitTimer.start();
                }

                 else if (button.getText().equals("Brava!")) {
                    Timer exitTimer = new Timer(500, e ->  State.SetState(refLink.GetGame().getScores()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();

                }
            }
        }
    }



    @Override
    public void Draw(Graphics g) {
        g.drawImage(end, 0, 0, 1280, 640, null);

        for (Button button : buttons) {
            button.draw(g);
        }

    }

}
