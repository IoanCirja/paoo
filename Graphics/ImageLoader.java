package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;
//tratare exceptii citire imagini
public class ImageLoader
{
    public static BufferedImage LoadImage(String path)
    {

        try
        {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

