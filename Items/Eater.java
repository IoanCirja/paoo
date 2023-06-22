package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.cert.CertificateFactory;
import java.util.Random;

import PaooGame.RefLinks;
import PaooGame.States.Level2;
import PaooGame.States.Level3;
import PaooGame.States.State;


import static PaooGame.Graphics.Assets.*;
import static java.awt.Color.BLUE;

//mancatorul ----> capcana din boss fight si din level 2
public class Eater extends Item {
    private boolean goo = false;
    private long lastTime;
    private boolean shouldDrawWhat = false; // draw what dialog box on hero

    public Eater(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 300, 300);

        bounds.x = (int) (x);
        bounds.y = (int) (y);

        this.bounds.width = 300;
        this.bounds.height = 300;
    }

    @Override
    public void Update() {

        //actiunea difera in functie de level 2 sau 3, ar fi mers un strategy but
        if (State.GetState().getClass() == Level3.class) {
            this.width = 100;
            this.height = 100;
            bounds.width = 100;
            bounds.height = 100;
        }

        if (refLink.GetGame().getPlayState().GetHero().GetX() >= 2142 && refLink.GetGame().getPlayState().GetHero().GetY() >= 132 && State.GetState().getClass() == Level2.class) {
            goo = true;
        }
        if (State.GetState().getClass() == Level3.class) {
            goo = true;
        }
        if (goo) {
            y += 40;

            bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset());
            bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset());
        }

        if (y >= 2000) {
            y = -300;

            if (State.GetState().getClass() == Level3.class) {
                Random random = new Random();
                this.x = random.nextInt((89 - 52 + 1) * 64) + (52 * 64);
            }
        }

        if (refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds)) {
            refLink.GetGame().getPlayState().GetHero().hurtHero();
        }


        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 30000) {
            shouldDrawWhat = true;
            lastTime = currentTime;
        }


        if (shouldDrawWhat && currentTime - lastTime >= 5000) {
            shouldDrawWhat = false;
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (goo) {
            if(State.GetState().getClass() == Level3.class || (State.GetState().getClass() == Level2.class && refLink.GetGame().getPlayState().GetHero().GetX() == 2142 && refLink.GetGame().getPlayState().GetHero().GetY() == 132 )) {

                BufferedImage img = shouldDrawWhat ? what : null;

                g.drawImage(img, (int) refLink.GetGame().getPlayState().GetHero().getBounds().x + 50, (int) refLink.GetGame().getPlayState().GetHero().getBounds().y - 50, 100, 100, null);


            }

            g.drawImage(lvl3_lantern, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), bounds.width, bounds.height, null);


        }
    }
}