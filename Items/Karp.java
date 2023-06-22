package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;
import static PaooGame.Items.Constants.ArcherConstants.*;
import static PaooGame.Items.Constants.PlayerConstants.*;


//inamicul cu verde din level2 si 3 isi face scut si arunca cu vraji
public class Karp extends Character {
    private int action = IDLE1;
    private int aniTick, aniIndex;
    private float aniSpeed = 2f;
    private int ofsetX = 45, ofsetY = 45;
    private boolean bubble_power = false;
    private long lastBubbleToggleTime = System.currentTimeMillis();
    private long bubbleToggleInterval = 3000;
    private boolean isShooting = false;
    private boolean dead = false;

    private int arrowX, arrowY;
    private Rectangle arrowBounds;

    private long lastShotTime = System.currentTimeMillis();

    public Karp(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 150, 150);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 90;
        this.bounds.height = 90;

        this.arrowBounds = new Rectangle(0, 0, 100, 100);
    }

    @Override
    public void Update() {
        if(!inv)

        {
            if(!dead)
            {
                updateAnimationTick();


                bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset() + ofsetX);
                bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset() + ofsetY);


                long currentTime = System.currentTimeMillis();
                long timeSinceLastShot = currentTime - lastShotTime;

                if (!isShooting && timeSinceLastShot >= 5000) {
                    lastShotTime = currentTime;
                    isShooting = true;
                    action = SHOOT1;
                    aniIndex = 0;
                    arrowX = (int) (x- refLink.GetGame().getCamera().GetxOffset());
                    arrowY = (int) (y + 64 - refLink.GetGame().getCamera().GetyOffset());
                    arrowBounds.x = arrowX;
                    arrowBounds.y = arrowY;

                }
                if (isShooting) {
                    arrowX -= 20;
                    arrowBounds.x = arrowX;
                    if (arrowX <= (x - refLink.GetGame().getCamera().GetxOffset() - 500)) {
                        isShooting = false;
                        action = IDLE1;
                    }
                }

                if(action == SHOOT1 && aniIndex == GetSpriteAmount1(action) - 1)
                {
                    action = IDLE1;
                    aniIndex = 0;
                }

                if((refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds) && refLink.GetGame().getPlayState().GetHero().getPlayerAction() == SLASH && !bubble_power))
                {
                    action = DIE1;
                    aniIndex = 0;
                }



                if(action == DIE1 && aniIndex>= GetSpriteAmount1(action) - 4)
                {
                    dead = true;
                    score+=10;
                    System.out.println(dead);
                    refLink.GetGame().getPlayState().GetHero().IncreaseKills();
                }



                if(refLink.GetGame().getPlayState().GetHero().getBounds().intersects(arrowBounds) && !refLink.GetGame().getPlayState().GetHero().isBubble_power())
                {
                    refLink.GetGame().getPlayState().GetHero().hurtHero();

                }

                long currentTime1 = System.currentTimeMillis();
                long timeSinceLastBubbleToggle = currentTime1 - lastBubbleToggleTime;
                if (timeSinceLastBubbleToggle >= bubbleToggleInterval) {
                    lastBubbleToggleTime = currentTime1;
                    bubble_power = !bubble_power;
                }



            }

        }




    }


    @Override
    public void Draw(Graphics g) {
        if(!inv)
        {
            if(!dead)
            {
                BufferedImage image = karp[action][aniIndex];

                g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);

                if (isShooting) {
                    g.drawImage(green_ball,  (int)(arrowBounds.x) , (int)(arrowBounds.y), arrowBounds.width, arrowBounds.height, null);

                }


                if(bubble_power)
                {
                    g.drawImage(bubble_green, bounds.x - 30, bounds.y - 30, 120, 120, null);
                }

            }

        }


    }

    private void updateAnimationTick() {
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
