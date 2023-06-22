package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;


import static PaooGame.Graphics.Assets.*;

//banuti pt scor

public class Coin extends Item{

    private int aniTick, aniIndex;
    private float aniSpeed = 2f;



    public static int score = 0; // scor ...creste si cand omor un inamic + 10
    private   boolean collected = false;


    public Coin(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 32, 32);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 32;
        this.bounds.height = 32;

    }

    @Override
    public void Update() {

        updateAnimationTick();
        if(!collected)
        {
            if(this.bounds.intersects(refLink.GetGame().getPlayState().GetHero().getBounds()))
            {
                collected = true;
                score++;
            }

        }


        bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset());
        bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset());













    }


    @Override
    public void Draw(Graphics g) {

        if(! collected)
        {
            BufferedImage image = coins[aniIndex];

            g.drawImage(image, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);





        }







    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5) {
                aniIndex = 0;
            }
        }
    }

}
