package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;


import static PaooGame.Graphics.Assets.*;


public class Heart extends Item{

// inimile care imi cresc viataa
    public boolean isCollected() {
        return collected;
    }

    private   boolean collected = false;


    public Heart(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 32, 32);


        bounds.x = (int) (x);
        bounds.y = (int) (y);


        this.bounds.width = 32;
        this.bounds.height = 32;

    }

    @Override
    public void Update() {


        if(!collected)
        {
            if(this.bounds.intersects(refLink.GetGame().getPlayState().GetHero().getBounds()))
            {
                collected = true;
                refLink.GetGame().getPlayState().GetHero().IncreaseLife();
            }

        }


        bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset());
        bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset());













    }


    @Override
    public void Draw(Graphics g) {

        if(! collected)
        {
            g.drawImage(life, (int) (x - refLink.GetGame().getCamera().GetxOffset()), (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);
        }

    }

}
