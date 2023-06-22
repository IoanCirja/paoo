package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import PaooGame.RefLinks;


import static PaooGame.Graphics.Assets.*;


public class Bubble extends Item{


//implementeaza bula care ma face invulnerabil 7 secunde daca o ating

    private   boolean collected = false;


    public Bubble(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 100, 100);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 32;
        this.bounds.height = 32;

    }

    @Override
    public void Update() {


        bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset());


        bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset());


        if (!collected) {
            if (this.bounds.intersects(refLink.GetGame().getPlayState().GetHero().getBounds())) {
                collected = true;
                refLink.GetGame().getPlayState().GetHero().setBubble_power(true);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        refLink.GetGame().getPlayState().GetHero().setBubble_power(false);
                    }
                }, 7000);
            }
        }




    }


    @Override
    public void Draw(Graphics g) {

        if(! collected)
        {
            BufferedImage image = bubble;

            g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()) - 35, (int) (y - refLink.GetGame().getCamera().GetyOffset()) -35, width, height, null);


        }

    }


}
