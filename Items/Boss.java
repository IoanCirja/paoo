package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Constants.ArcherConstants.*;
import static PaooGame.Items.Constants.ArcherConstants.HURT;
import static PaooGame.Items.Constants.PlayerConstants.*;
import static java.awt.Color.BLUE;

public class Boss extends Character {
    private int action = IDLE1;
    private int aniTick, aniIndex;

    //pt dialog box
    private boolean displayImage = false;
    private boolean displayCurse = false;
    private long displayStartTime = 0;

    private float aniSpeed = 2f;
    private int ofsetX = 45, ofsetY = 45;


    private boolean hurtAnimationPlayed = false;
    private boolean bubble_power = false;
    private long lastBubbleToggleTime = System.currentTimeMillis();
    private long bubbleToggleInterval = 3000;
    private boolean isShooting = false;
    private boolean dead = false;
    //locuri de spawnare
    private int [][] coordiantes = {{72,25}, {84,21}, {85,16}, {84, 5}, {72,10}, {57,6}, {56,16}, {56,21}};

    private long lastLocationChangeTime = System.currentTimeMillis();

    private long locationChangeInterval = 25000; //cand se teleporteaza

    private int arrowX, arrowY; // vraji
    private Rectangle arrowBounds;

    private long lastShotTime = System.currentTimeMillis();

    public Boss(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 200, 200);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 100;
        this.bounds.height = 100;

        this.arrowBounds = new Rectangle(0, 0, 100, 100);
        this.life = 5;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void Update() {
        if(!inv)

        {
            if(!dead)
            {


                if((refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds) &&
                        refLink.GetGame().getPlayState().GetHero().getPlayerAction() == SLASH &&
                        !bubble_power &&
                        refLink.GetGame().getPlayState().GetHero().getAniIndex() >= 5))
                {
                    if (!hurtAnimationPlayed) {
                        action = HURT;
                        aniIndex = 0;
                        hurtAnimationPlayed = true;
                        life--;
                    }

                }

                bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset() + ofsetX);
                bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset() + ofsetY);


                if (action == HURT && aniIndex >= GetSpriteAmount1(action) - 2 && life >=0) {
                    action = IDLE1;
                    aniIndex = 0;
                    hurtAnimationPlayed = false;

                }
                else if (action == HURT && aniIndex >= GetSpriteAmount1(action) - 10 && life <=0)
                {
                    action = DIE1;
                    aniIndex = 0;
                }


                //moare
                if(action == DIE1 && aniIndex>= GetSpriteAmount1(action) - 4 && life <= 0)
                {
                    dead = true;
                    refLink.GetGame().getPlayState().GetHero().IncreaseKills();
                }



                if(refLink.GetGame().getPlayState().GetHero().getBounds().intersects(arrowBounds) && !refLink.GetGame().getPlayState().GetHero().isBubble_power())
                {
                    refLink.GetGame().getPlayState().GetHero().hurtHero();

                }


                long currentTime = System.currentTimeMillis();
                long timeSinceLastShot = currentTime - lastShotTime;

                if (!isShooting && timeSinceLastShot >= 15000) {
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

                long currentTime1 = System.currentTimeMillis();
                long timeSinceLastBubbleToggle = currentTime1 - lastBubbleToggleTime;
                if (timeSinceLastBubbleToggle >= bubbleToggleInterval) {
                    lastBubbleToggleTime = currentTime1;
                    bubble_power = !bubble_power;
                }


                long currentTime11 = System.currentTimeMillis();
                long timeSinceLastLocationChange = currentTime11 - lastLocationChangeTime;
                    //teleportare
                if (timeSinceLastLocationChange >= locationChangeInterval) {
                    lastLocationChangeTime = currentTime11;
                    int index = (int)(Math.random() * coordiantes.length);

                    int newX =(coordiantes[index][0] -1) * 64;
                    int newY = (coordiantes[index][1] - 2) * 64;
                    this.SetX(newX);
                    this.SetY(newY);
                }

                updateAnimationTick();

            }

        }


    }


    @Override
    public void Draw(Graphics g) {
        if (!inv) {
            if (!dead) {
                BufferedImage image = boss[action][aniIndex];

                g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);

                if (isShooting) {
                    g.drawImage(meteor, (int) (arrowBounds.x), (int) (arrowBounds.y), arrowBounds.width, arrowBounds.height, null);

                }



                if (bubble_power) {
                    g.drawImage(red_ball, bounds.x - 30, bounds.y - 30, 200, 200, null);
                }

                long currentTime = System.currentTimeMillis();
                if (currentTime - displayStartTime <= 5000) {
                    if (displayImage) {
                        if (displayCurse) {
                            BufferedImage curseImg = Assets.curse;
                            g.drawImage(curseImg, (int) (bounds.x + 80), (int) bounds.y - 120, 200, 150, null);
                        } else {
                            BufferedImage hahImg = Assets.hah;
                            g.drawImage(hahImg, (int) (bounds.x + 80), (int) bounds.y - 120, 200, 150, null);
                        }
                    }
                } else {

                    displayStartTime = currentTime;


                    //care dialog box sa arate


                    if (Math.random() < 0.5) {
                        displayCurse = true;
                        displayImage = true;
                    } else {
                        displayCurse = false;
                        displayImage = true;
                    }
                }

                // Reset the displayImage
                if (currentTime - displayStartTime > 10000) {
                    displayImage = false;
                    displayCurse = false;
                }
                if(life > 0 )
                {
                    int aux = bounds.x;
                    for(int i = 0; i< life;i++)
                    {
                        g.drawImage(Assets.life, aux, bounds.y - 30, 20,20, null);
                        aux+= 20;
                    }
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
