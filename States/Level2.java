package PaooGame.States;

import PaooGame.DataBaseWriter;
import PaooGame.Graphics.Assets;
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



public class Level2 extends State
{

    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;
    private ArrayList<Button> buttons;

    int[][] coordinates = {{6, 1}, {6, 5}, {10, 5}, {17, 5}, {7, 9}, {9, 13}, {19, 13}, {5, 17}, {21, 17}, {5, 21}, {7, 25}, {33, 3}, {29, 9}, {45, 26}, {10, 3}, {19, 5}, {23, 9}, {9, 10}, {20, 13}, {9, 17}, {5, 20}, {11, 25}, {40, 26}, {95, 2}, {93, 7}, {97, 14}, {19, 21}, {20, 21}, {16, 25}, {17, 25}, {18, 25}, {51, 24}, {56, 24}, {61, 24}, {66, 24}, {71, 24}, {81, 24}, {80, 19}, {81, 19}, {82, 19}, {83, 19}, {84, 19}, {85, 19}, {77, 6}, {91, 24}, {64, 17}, {57, 14}, {48, 17}, {49, 17}, {50, 17}, {48, 18}, {49, 18}, {50, 18}, {48, 19}, {49, 19}, {50, 19}};

    private ArrayList<Coin> coins = new ArrayList<>();
    private int[][] coordinates_kicker = {{23,25}, {18,17}, {23,9}, {38,5}, {38,9}, {39,14}, {38,17}, {37,22}, {57,19}, {57,3}, {67,25}, {82,25}, {78,6}, {85,13}, {96,17}};
    private int[][] coordinates_archer = {{39,6}, {39,10}, {38,14}, {39,18}, {39,22}, {25,5}, {50,12}, {97,14}, {71,9}};
    private int[][] coordinates_box = {{7,25}, {11,21}, {10,17}, {10,16}, {9,16}, {9,17}, {23,13}, {18,25}, {33,26}, {9,1}, {10,1}, {11,1}, {12,1}, {74,18}, {74,19}, {63,17}, {53,19}, {53,18}, {53,17}, {53,3}, {97,10}, {93,7}, {99,19}};
    private int[][] coordinates_mage = {{5,21}, {6,13}, {7,13}, {9,9}, {4,5}, {29,26}, {47,18}, {56,14}, {50,3}, {73,11}, {93,2}};
    private int[][] coordinates_karps = {{38,22}, {37,18}, {37,14}, {37,10}, {37,6}, {23,13}, {99,110}, {97,2}, {71,4}, {92,16}};
    private int [][] getCoordinates_bubbles = {{14,24}, {15,8}, {33,25}, {31,13}, {52,1}, {72,2}, {86,5}, {84,11}};


    private ArrayList<Kicker> kickers = new ArrayList<>();
    private ArrayList<Magician> magicians = new ArrayList<>();
    private ArrayList<Archer> archers = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private boolean paused = false;
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private ArrayList<Heart> hearts = new ArrayList<>();

    private ArrayList<Karp> karps = new ArrayList<>();
    private Eater shield;
    public Map getMap() {
        return map;
    }






    /*!< Referinta catre harta curenta.*/






    /*! \fn public Level1(RefLinks refLink)
                    \brief Constructorul de initializare al clasei

                    \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
                 */
    public Level2(RefLinks refLink)
    {

        ///Apel al constructorului clasei de baza
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(1210, 0, 45, 45, ""));
        buttons.add(new Button(1145, 0, 45, 45, "."));
        buttons.add(new Button(1100, 0, 45, 45, ","));
        ///Construieste harta jocului
        map = new Map(refLink);
        map.LoadWorld2();
        //refLink.SetMap(map);

        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.


        ///Construieste eroul
        hero = Hero.getInstance(refLink, 0, 0); // for singleton
        hero.setAbletoWin(false);

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
        for (int[] coord : coordinates_mage) {
            magicians.add((new Magician(refLink,((coord[0] -1) * 64), ((coord[1]-1) * 64))));

        }

        for (int[] coord : coordinates_karps) {
            karps.add((new Karp(refLink,((coord[0] -1) * 64), ((coord[1]-1) * 64))));

        }
        shield = new Eater(refLink, 2300, -300);





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

                    Timer exitTimer = new Timer(500, e ->  DataBaseWriter.writeToDatabaseLoad(2,score, hero.getKills(), Box.getStars(), hero.GetLife()));
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
        if(!paused && !ButtonHovered) {


            map.Update();
            hero.Update();
            for (Coin coin : coins) {
                coin.Update();

            }

            for (Kicker kicker : kickers) {
                kicker.Update();
                if (shield.getBounds().intersects(kicker.getBounds())) {
                    kicker.SetInvisible(true);
                }

            }
            for (Archer archer : archers) {
                archer.Update();
                if (shield.getBounds().intersects(archer.getBounds())) {
                    archer.SetInvisible(true);
                }

            }

            for (Magician magician : magicians) {
                magician.Update();

            }
            for (Box box : boxes) {
                box.Update();
            }

            for (Bubble bubble : bubbles) {
                bubble.Update();
            }

            for (Heart bubble : hearts) {
                bubble.Update();
            }
            for (Karp karp : karps) {
                karp.Update();
                if (shield.getBounds().intersects(karp.getBounds())) {
                    karp.SetInvisible(true);
                }
            }

            if (hero.isAbletoWin() && Box.getStars() >= 5 && score >= 100) {

                System.out.println("i can win");
                State.SetState(refLink.GetGame().getLvl3());
                refLink.SetMap(((Level3) refLink.GetGame().getLvl3()).getMap());
                hero.SetX(0);
                hero.SetY(0);
                hero.setAbletoWin(false);


            }
            shield.Update();
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
            background = ImageIO.read(new File("res/level_2/Background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background,0,0, 1280,640, null);


        map.Draw(g);



            hero.Draw(g);

        for(Coin coin: coins)
        {
            coin.Draw(g);

        }

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
        for(Karp karp:karps)
        {
            karp.Draw(g);
        }

        for(Bubble bubble:bubbles)
        {
            bubble.Draw(g);
        }

        for(Heart bubble:hearts)
        {
            bubble.Draw(g);
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


        g.drawImage( light, (int) (6296 - refLink.GetGame().getCamera().GetxOffset()), (int) (62 - refLink.GetGame().getCamera().GetyOffset()), 128,128, null);
        shield.Draw(g);

        g.drawImage(pause, 1160, 0, 45, 45, null);
        g.drawImage(x, 1220, 0, 45, 45, null);
        g.drawImage(load, 1100, 0, 45, 45, null);





        for (Button button : buttons) {
            button.draw(g);
        }

    }



}
