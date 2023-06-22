package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.States.Level1;
import PaooGame.States.Level2;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.awt.Color.blue;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    public int getWidth() {
        return width;
    }


    private int width;          /*!< Latimea hartii in numar de dale.*/

    public int getHeight() {
        return height;
    }

    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int[][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/


    /*! \fn public Map(RefLinks refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
         */
    public Map(RefLinks refLink) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        ///Se calculeaza marginile hartii in functie de pozitia camerei
        //xStart va fi max dintre 0 si xOffset/Width pentru a preveni iesirea camerei din fereastra de joc...samd
        int xStart = (int) Math.max(0, refLink.GetGame().getCamera().GetxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (refLink.GetGame().getCamera().GetxOffset() + refLink.GetGame().GetWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, refLink.GetGame().getCamera().GetyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (refLink.GetGame().getCamera().GetyOffset() + refLink.GetGame().GetWidth()) / Tile.TILE_HEIGHT + 1);
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                GetTile(y, x).Draw(g,
                        (int) (x * Tile.TILE_WIDTH - refLink.GetGame().getCamera().GetxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - refLink.GetGame().getCamera().GetyOffset()));



            }
        }
    }


    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= height || y >= width) {
            return Tile.invisible;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.invisible;
        }
        return t;
    }


    public void LoadWorld1() {

        {

            width = 100;
            height = 30;

            tiles = new int[height][width];

            File file = new File("res/maps/level_1.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (int y = 0; y < 30; y++) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int x = 0; x < 100; x++) {
                    tiles[y][x] = Integer.parseInt(values[x]);
                }
            }
            scanner.close();


        }


    }

    public void LoadWorld2() {


            width = 100;
            height = 30;

            tiles = new int[height][width];

            File file = new File("res/maps/level_2.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (int y = 0; y < 30; y++) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int x = 0; x < 100; x++) {
                    tiles[y][x] = Integer.parseInt(values[x]);
                }
            }
            scanner.close();


        }

        public void LoadWorld3 ()
        {
                width = 100;
                height = 30;

                tiles = new int[height][width];

                File file = new File("res/maps/level_3.txt");
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                for (int y = 0; y < 30; y++) {
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    for (int x = 0; x < 100; x++) {
                        tiles[y][x] = Integer.parseInt(values[x]);
                    }
                }
                scanner.close();





        }
    }






