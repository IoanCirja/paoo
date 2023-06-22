package PaooGame.States;

import PaooGame.DataBaseWriter;
import PaooGame.Game;
import PaooGame.Items.*;
import PaooGame.Items.Box;
import PaooGame.Items.Character;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;


public class Level1 extends State
{
    public Hero GetHero() {
        return hero;
    }
    private ArrayList<Button> buttons;
    private boolean paused = false;

    private Hero hero;
    private Map map;





    private int[][] coordinates = {{38, 2}, {44, 2}, {49, 1}, {9, 6}, {15, 7}, {16, 7}, {21, 2}, {27, 2}, {31, 7}, {17, 11}, {18, 11}, {19, 11}, {20, 11}, {21, 11}, {22, 11}, {23, 11}, {38, 11}, {39, 11},
    {40, 11}, {20, 15}, {21, 15}, {38, 2}, {10, 16}, {36, 16}, {21, 15}, {39, 20}, {39, 19}, {20, 25}, {14, 25}, {26, 25}, {45, 21}, {59, 2}, {67, 2}, {59, 8}, {60, 16}, {70, 21},
    {69, 28}, {69, 27}, {73, 2}, {81, 14}, {94, 4}, {88, 14}, {98, 19}, {86, 14}};
    private int[][] coordinates_kicker = {{10, 6}, {22, 2}, {22,11}, {39,11}, {67,2}, {61,8}, {61,16}, {71,15}, {80,2}, {83, 9}, {97, 4}, {61,16}, {59, 21}, {68,26}, {33,21}};
    private int[][] coordinates_archer = {{99,19}, {99,14}, {99,9}, {99,4}, {51,0}, {29,1}, {49, 8}};
    private int[][] coordinates_box = {{81, 9}, {96,4}, {80,14}, {79, 19}, {11, 11}, {16, 2}, {29, 2}, {32, 11}, {40, 2}, {46, 2}, {51, 1}, {65, 2}, {55, 8}, {79, 24}, {84, 24}, {80, 24}, {28, 16}, {8, 16},
            {1, 24}, {16, 21}, {35, 21}, {50, 21}, {60, 26}, {61, 25}, {64, 16}};
    private int [][] getCoordinates_bubbles = {{12, 9}, {24, 15}, {42, 6}, {63, 19}, {20,24}, {90,12}};


    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Kicker> kickers = new ArrayList<>();
    private ArrayList<Magician> magicians = new ArrayList<>();
    private ArrayList<Archer> archers = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private ArrayList<Heart> hearts = new ArrayList<>();


    public Map getMap() {
        return map;
    }

    public Level1(RefLinks refLink)
    {

            ///Apel al constructorului clasei de baza
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(1210, 0, 45, 45, ""));
        buttons.add(new Button(1145, 0, 45, 45, "."));
        buttons.add(new Button(1100, 0, 45, 45, ","));
            ///Construieste harta jocului
        map = new Map(refLink);
        map.LoadWorld1();
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = Hero.getInstance(refLink, 0, 0); // for singleton





        magicians.add(new Magician(refLink, (6-1)*64, (16-1)*64));
        magicians.add(new Magician(refLink, (3-1)*64, (11-1)*64));
        magicians.add(new Magician(refLink, (16-1)*64, (7-1)*64));
        magicians.add(new Magician(refLink, (20-1)*64, (10-1)*64));
        magicians.add(new Magician(refLink, (30-1)*64, (20-1)*64));
        magicians.add(new Magician(refLink, (5-1)*64, (25-1)*64));
        magicians.add(new Magician(refLink, (51-1)*64, (26-1)*64));
        magicians.add(new Magician(refLink, (47-1)*64, (8-1)*64));
        magicians.add(new Magician(refLink, (76-1)*64, (9-1)*64));
        magicians.add(new Magician(refLink, (88-1)*64, (14-1)*64));








        for (int[] coord : coordinates) {
            coins.add(new Coin(refLink, (coord[0] * 64), (coord[1] * 64) + 20));
            coins.add(new Coin(refLink, (coord[0] * 64) + 20, (coord[1] * 64) + 20));
            coins.add(new Coin(refLink, (coord[0] * 64) + 40, (coord[1] * 64) +20));
        }

        for (int[] coord : coordinates_kicker) {
            kickers.add((new Kicker(refLink,((coord[0] -1) * 64), ((coord[1]-1) * 64))));

        }
        for (int[] coord : coordinates_archer) {
            archers.add((new Archer(refLink,((coord[0] -1) * 64), ((coord[1]-1) * 64))));

        }
        for (int[] coord : coordinates_box) {
            boxes.add((new Box(refLink,((coord[0]) * 64), ((coord[1]) * 64))));

        }
        for (int[] coord : getCoordinates_bubbles) {
            bubbles.add((new Bubble(refLink,((coord[0]) * 64), ((coord[1]) * 64))));
            hearts.add((new Heart(refLink,((coord[0]) * 64), ((coord[1]) * 64))));
        }
        //DataBaseWriter.deleteAllLoads();



    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        boolean ButtonHovered = false;

        Point mousePos = MouseInfo.getPointerInfo().getLocation();

        int windowX = refLink.GetGame().getGameWindow().getX(); // X position of the game window
        int windowY = refLink.GetGame().getGameWindow().getY(); // Y position of the game window

        int mouseX = (int) (mousePos.getX() - windowX);
        int mouseY = (int) (mousePos.getY() - windowY) - 40;

        for (Button button : buttons) {
            button.update(mouseX, mouseY);

            if (button.contains(mouseX, mouseY)) {
                if (button.getText().equals("")) {
                    Timer exitTimer = new Timer(500, e -> State.SetState(refLink.GetGame().getMenuState()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();
                    refLink.GetGame().resetLevels(refLink);
                    hero.SetX(0);
                    hero.SetY(0);
                    Box.stars = 0;
                    score = 0;
                    Hero.kills = 0;
                    hero.setLife(Character.DEFAULT_LIFES);
                }else if (button.getText().equals(",")) {

                    Timer exitTimer = new Timer(500, e ->  DataBaseWriter.writeToDatabaseLoad(1,score, hero.getKills(), Box.getStars(), hero.GetLife()));
                    exitTimer.setRepeats(false);
                    exitTimer.start();

                }else if(button.getText().equals("."))
                {
                    ButtonHovered = true;
                    if (button.getText().equals(".") && !paused) {
                        paused = true;
                    }
                    else paused = false;

                }


            }
        }

        if(!paused && !ButtonHovered)
        {
            map.Update();
            hero.Update();



            for(Coin coin: coins)
            {
                coin.Update();

            }

            for(Kicker kicker: kickers)
            {
                kicker.Update();

            }
            for(Archer archer: archers)
            {
                archer.Update();

            }

            for(Magician magician: magicians)
            {
                magician.Update();

            }
            for(Box box:boxes)
            {
                box.Update();
            }

            for(Bubble bubble:bubbles)
            {
                bubble.Update();
            }

            for(Heart bubble:hearts)
            {
                bubble.Update();
            }





            if(hero.isAbletoWin() && Box.getStars() >= 2 && State.GetState().getClass() == Level1.class)
            {
                State.SetState(refLink.GetGame().getLvl2());
                refLink.SetMap(((Level2) refLink.GetGame().getLvl2()).getMap());

                hero.SetX(0);
                hero.SetY(24 * 64);
                hero.setAbletoWin(false);

            }


        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {



        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("res/level_1/Environment/Background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background,0,0, 1280,640, null);


        map.Draw(g);


            hero.Draw(g);




        for(Kicker kicker: kickers)
        {
            kicker.Draw(g);

        }

        for(Magician magician: magicians)
        {
            magician.Draw(g);

        }
        for(Archer archer: archers)
        {
            archer.Draw(g);

        }
        for(Box box:boxes)
        {
            box.Draw(g);
        }

        for(Bubble bubble:bubbles)
        {
            bubble.Draw(g);
        }

        for(Heart bubble:hearts)
        {
            bubble.Draw(g);
        }




        for(Coin coin: coins)
        {
            coin.Draw(g);

        }


        g.drawImage(grey, 530, 0, 236, 50, null);
        Font cartoonFont = new Font("Comic Sans MS", Font.BOLD, 16); // increase font size

        g.setFont(cartoonFont);
        g.setColor(new Color(100, 65, 23)); // yellowish color

        g.drawString("  Coins: " + score, 530, 30);
        g.drawString("            | Kills: "+ hero.getKills(), 580, 30);// change y-coordinate to 50

        for(int i = 0; i<hero.GetLife(); i++)
        {
            g.drawImage(life, i* 25, 0 , 25, 25, null);
        }


        g.drawImage( light, (int) (6296 - refLink.GetGame().getCamera().GetxOffset()), (int) (1496 - refLink.GetGame().getCamera().GetyOffset()), 128,128, null);

        g.drawImage(pause, 1160, 0, 45, 45, null);
        g.drawImage(x, 1220, 0, 45, 45, null);
        g.drawImage(load, 1100, 0, 45, 45, null);


        for (Button button : buttons) {
            button.draw(g);
        }

    }



}
