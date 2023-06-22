package PaooGame.Graphics;

import java.awt.image.BufferedImage;
//crop la imagini
//Clasa retine o referinta catre o imagine formata din dale (sprite sheet)
//
//        Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
//        de la adresa (x * latimeDala, y * inaltimeDala)
public class SpriteSheet
{
    private BufferedImage       spriteSheet;
    private static final int    tileWidth   = 128;
    private static final int    tileHeight  = 128;

    public SpriteSheet(BufferedImage buffImg)
    {

        spriteSheet = buffImg;
    }


    public BufferedImage crop(int x, int y)
    {

        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
    public BufferedImage crop_900(int x, int y)
    {

        return spriteSheet.getSubimage(x * 900, y * 900, 900, 900);
    }
}
