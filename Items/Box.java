package PaooGame.Items;

import java.util.Random;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import PaooGame.RefLinks;
import PaooGame.States.Level1;
import PaooGame.States.Level2;
import PaooGame.States.Level3;
import PaooGame.States.State;


import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;
import static PaooGame.Items.Constants.PlayerConstants.SLASH;


public class Box extends Item{


    private   boolean slashed = false;
    public static int stars = 0; // scorul stelelor


    public static int getStars() {
        return stars;
    }

    public Box(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 64, 64);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 64;
        this.bounds.height = 64;

    }

    @Override
    public void Update() {

        //boxul ori imi da coins stele hearts  bubble random
        //ps ajung cutiile simt eu


        if(!slashed)
        {


            if(this.bounds.intersects(refLink.GetGame().getPlayState().GetHero().getBounds()) && refLink.GetGame().getPlayState().GetHero().getPlayerAction() == SLASH)
            {
                slashed = true;

                int randomNum = (int) (Math.random() * 4);

                if(randomNum == 0) {
                    score++;
                }
                else if(randomNum == 1) {
                    stars++;
                }
                else if(randomNum == 2){
                    refLink.GetGame().getPlayState().GetHero().IncreaseLife();
                }
                else
                {
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



        bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset());
        bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset());

    }


    @Override
    public void Draw(Graphics g) {

        if(!slashed)
        //cutia se schimba dupa nivel
        {
            if(State.GetState().getClass() == Level1.class)
                g.drawImage(bonus, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), 64, 64, null);
            else if (State.GetState().getClass() == Level2.class) {
                g.drawImage(lvl2_bonus, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), 64, 64, null);

            } else if (State.GetState().getClass() == Level3.class) {
                g.drawImage(lvl3_bonus, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), 64, 64, null);

            }
        }

        for(int i = 0; i<stars; i++)
        {
            g.drawImage(star, 200 + i* 25, 0 , 25, 25, null);
        }




    }

}
