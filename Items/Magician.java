package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;

import javax.swing.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;
import static PaooGame.Items.Constants.ArcherConstants.*;
import static PaooGame.Items.Constants.MagicianConstants.*;
import static PaooGame.Items.Constants.PlayerConstants.*;

//inamic cu mov
public class Magician extends Character {
    private int action = IDLE2;
    private int aniTick, aniIndex;
    private float aniSpeed = 2f;
    private int ofsetX = 45, ofsetY = 45;
    private boolean isSlashing = false;
    private boolean dead = false;



    private long lastSlashTime = System.currentTimeMillis();

    public Magician(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 150, 150);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 90;
        this.bounds.height = 90;

    }

    @Override
    public void Update() {

        if(!dead)
        {
            updateAnimationTick();


            bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset() + ofsetX);
            bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset() + ofsetY);


            long currentTime = System.currentTimeMillis();
            long timeSinceLastShot = currentTime - lastSlashTime;

            if (!isSlashing && timeSinceLastShot >= 3000) {
                lastSlashTime = currentTime;
                isSlashing = true;
                action = SLASH2;
                aniIndex = 0;
            }


            if (isSlashing) {
                if (aniIndex == GetSpriteAmount1(action) - 1) {
                    isSlashing = false;
                    action = IDLE2;
                    aniIndex = 0;
                }
            }

            if(action == SLASH2 && aniIndex == GetSpriteAmount1(action) - 1)
            {
                action = IDLE2;
                aniIndex = 0;
            }

            if(refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds) && refLink.GetGame().getPlayState().GetHero().getPlayerAction() == SLASH)
            {
                action = DIE2;
                aniIndex = 0;
            }



            if(action == DIE2 && aniIndex>= GetSpriteAmount1(action) - 4)
            {
                dead = true;
                score+=10;
                System.out.println(dead);
                refLink.GetGame().getPlayState().GetHero().IncreaseKills();
            }



            if(refLink.GetGame().getPlayState().GetHero().getBounds().intersects(bounds) && action == SLASH2 && !refLink.GetGame().getPlayState().GetHero().isBubble_power())
            {
                refLink.GetGame().getPlayState().GetHero().hurtHero();

            }









        }


    }


    @Override
    public void Draw(Graphics g) {
        if(!dead)
        {
            BufferedImage image = magician1[action][aniIndex];

            g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);


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
