package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;
import static PaooGame.Items.Constants.ArcherConstants.*;
import static PaooGame.Items.Constants.PlayerConstants.*;

public class Archer extends Character {
    //aniamtii
    private int action = IDLE1;
    private int aniTick, aniIndex;
    private float aniSpeed = 2f;


    private int ofsetX = 45, ofsetY = 45; // sa arate frumi
    private boolean isShooting = false; // pt shoot
    private boolean dead = false;

    private int arrowX, arrowY; // coord sageata
    private Rectangle arrowBounds; //sageata

    private long lastShotTime = System.currentTimeMillis(); //

    public Archer(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 150, 150);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 90;
        this.bounds.height = 90;

        this.arrowBounds = new Rectangle(0, 0, 64, 32);
    }

    @Override
    public void Update() {
        if(!inv){

        if (!dead) {
            updateAnimationTick();


            bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset() + ofsetX);
            bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset() + ofsetY);


            long currentTime = System.currentTimeMillis();
            long timeSinceLastShot = currentTime - lastShotTime;
            //sageti each 7 sec

            if (!isShooting && timeSinceLastShot >= 7000) {
                lastShotTime = currentTime;
                isShooting = true;
                action = SHOOT1;
                aniIndex = 0;
                arrowX = (int) (x - refLink.GetGame().getCamera().GetxOffset());
                arrowY = (int) (y + 64 - refLink.GetGame().getCamera().GetyOffset());
                arrowBounds.x = arrowX;
                arrowBounds.y = arrowY;

            }
            //deplasare sageata si idle action
            if (isShooting) {
                arrowX -= 20;
                arrowBounds.x = arrowX;
                if (arrowX < (x - refLink.GetGame().getCamera().GetxOffset() - 500)) {
                    isShooting = false;
                    action = IDLE1;
                }
            }

            if (action == SHOOT1 && aniIndex == GetSpriteAmount1(action) - 1) {
                action = IDLE1;
                aniIndex = 0;
            }

            if (refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds) && refLink.GetGame().getPlayState().GetHero().getPlayerAction() == SLASH) {
                action = DIE1;
                aniIndex = 0;
            }

                // cand moare
            if (action == DIE1 && aniIndex >= GetSpriteAmount1(action) - 4) {
                dead = true;
                score+=10;
                refLink.GetGame().getPlayState().GetHero().IncreaseKills();
            }

            //hurt hero
            if (refLink.GetGame().getPlayState().GetHero().getBounds().intersects(arrowBounds) && !refLink.GetGame().getPlayState().GetHero().isBubble_power()) {
                refLink.GetGame().getPlayState().GetHero().hurtHero();

            }


        }
    }


    }


    @Override
    public void Draw(Graphics g) {
        if(! inv)
        {
            if(!dead)
            {
                BufferedImage image = archer1[action][aniIndex];

                g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);
                //arrow
                if (isShooting) {
                    g.drawImage(arrow1,  (int)(arrowBounds.x) , (int)(arrowBounds.y), arrowBounds.width, arrowBounds.height, null);

                }


            }

        }


    }

    private void updateAnimationTick() {
        //animatii
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount1(action)) {
                aniIndex = 0;
            }
        }
    }

}
