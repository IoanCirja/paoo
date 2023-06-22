/*package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

public class Camera {
    public int xWindow;
    public int yWindow;
    public int xHero;
    public int yHero;
    private RefLinks refLinks;

    public float getWidth() {
        return width;
    }

    private float width, height;
    public int xHeroDisplay;
    public int yHeroDisplay;

    public Camera(RefLinks refLinks, float width, float height) {
        this.width = width;
        this.height = height;
        this.refLinks = refLinks;
    }

    public void Update(float x, float y) {
        /*if(x < refLinks.GetWidth() / 2 + width/2 || x > refLinks.GetMap().getWidth()* Tile.TILE_WIDTH - refLinks.GetWidth()/2+width/2)
            xHeroDisplay = (int) x;
        else
            xHeroDisplay = (int) (refLinks.GetWidth() / 2 - width/2);

        if(y < refLinks.GetHeight() / 2 + height/2 || y > refLinks.GetMap().getHeight()* Tile.TILE_HEIGHT - refLinks.GetHeight()/2+height/2)
            yHeroDisplay = (int) y;
        else
            yHeroDisplay = (int) (refLinks.GetHeight() / 2 - height/2);*/

        /*xWindow = (int) (x - refLinks.GetWidth() / 2 + width/2);
        yWindow = (int) (y - refLinks.GetHeight() / 2 + height/2);
        if(xWindow < 0)
            xWindow = 0;
        if(yWindow < 0)
            yWindow = 0;
        if(xWindow > refLinks.GetMap().getWidth()* Tile.TILE_WIDTH - refLinks.GetWidth())
            xWindow = refLinks.GetMap().getWidth()* Tile.TILE_WIDTH - refLinks.GetWidth();
        if(yWindow > refLinks.GetMap().getHeight()* Tile.TILE_HEIGHT - refLinks.GetHeight())
            yWindow = refLinks.GetMap().getHeight()* Tile.TILE_HEIGHT - refLinks.GetHeight();*/

        /*xHero = (int) (xWindow + refLinks.GetWidth()/2 - width/2);
        yHero = (int) (yWindow + refLinks.GetHeight()/2 - height/2);
        if(xHero < refLinks.GetWidth()/2 - width/2)
            xHero = (int) (refLinks.GetWidth()/2 - width/2);
        else
            xHero = (int) x;
        if(yHero < refLinks.GetHeight()/2 - height/2)
            yHero = (int) (refLinks.GetHeight()/2 - height/2);
        else yHero = (int) y;

        if(xHero > refLinks.GetWidth()/2 - width/2)
            xWindow = (int) (xHero + width/2 - refLinks.GetWidth()/2);
        else
            xWindow = 0;
        if(yHero > refLinks.GetHeight()/2 - height/2)
            yWindow = (int) (yHero + height/2 - refLinks.GetHeight()/2);
        else
            yWindow = 0;*

        if (x < refLinks.GetWidth() / 2 - width / 2) {
            xHeroDisplay = (int) x;
            xWindow = 0;
        }
        else {
            if (x > refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth() / 2 - width / 2)
            {
                xHeroDisplay = (int) (x - refLinks.GetMap().getWidth() * Tile.TILE_WIDTH + refLinks.GetWidth());
                xWindow = refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth();
            }
            else
            {
                xHeroDisplay = (int) (refLinks.GetWidth() / 2 - width / 2);
                xWindow = (int) (x + width / 2 - refLinks.GetWidth() / 2);
            }
        }

        if (y < refLinks.GetHeight() / 2 - height / 2) {
            yHeroDisplay = (int) y;
            yWindow = 0;
        }
        else {
            if (y > refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight() / 2 - height / 2) {
                yHeroDisplay = (int) (y - refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT + refLinks.GetHeight());
                yWindow = refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight();
            }
            else {
                yHeroDisplay = (int) (refLinks.GetHeight() / 2 - height / 2);
                yWindow = (int) (y + height / 2 - refLinks.GetHeight() / 2);
            }
        }
        xHero = (int) x;
        yHero = (int) y;
    }
}
*/

package PaooGame.Items;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

//camera care e implementata calculand un xofset si yofset + limite

public class Camera {

    private float xOffset;
    private float yOffset;

    private RefLinks refLinks;


    public Camera(RefLinks refLinks, float xOffset, float yOffset) {
        this.refLinks = refLinks;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    public void followHero(Item hero) {
        xOffset = hero.GetX() + hero.GetWidth()/2 - refLinks.GetWidth()/2;
        yOffset = hero.GetY() + hero.GetHeight()/2 - refLinks.GetHeight()/2;
        checkCameraLimits();
    }

    public void checkCameraLimits() {

        if(xOffset < 0)
            xOffset = 0;
        else if(xOffset > refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth())
            xOffset = refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth();

        if(yOffset < 0)
            yOffset = 0;
        else if(yOffset > refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT  - refLinks.GetHeight())
            yOffset = refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight();
    }


    public float GetxOffset() {
        return xOffset;
    }


    public float GetyOffset()
    {
        return yOffset;
    }


}

