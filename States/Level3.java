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
import static PaooGame.Items.Hero.kills;


public class Level3 extends State
{

    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;

    int[][] coordinates =  {{12,1}, {8,1}, {9,6}, {16,6}, {3,16}, {10,16}, {13,21}, {8,21}, {1,26}, {2,21}, {22,1}, {22,6}, {22,11}, {22,16}, {22,21}, {22,26}, {35,1}, {33,1}, {33,6}, {35,6}, {33,11}, {35,11}, {33,16}, {35,16}, {33,21}, {35,21}, {33,26}, {35,26}, {43,6}, {46,11}, {45,16}, {41,21}};

    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Button> buttons;
    private int[][] coordinates_kicker = {{14,1}, {35,6}, {48,1}, {14,11}, {31,16}, {21,21}, {26,26}, {46,26}, {89,1}, {89,21}, {89, 11}};
    private int[][] coordinates_archer = {{40, 6}, {46,21}, {26,16}, {45,26}};
    private int[][] coordinates_box = {{12,1}, {8,1}, {9,6}, {16,6}, {3,16}, {10,16}, {13,21}, {8,21}, {1,26}, {2,21}, {22,1}, {22,6}, {22,11}, {22,16}, {22,21}, {22,26}, {35,1}, {33,1}, {33,6}, {35,6}, {33,11}, {35,11}, {33,16}, {35,16}, {33,21}, {35,21}, {33,26}, {35,26}, {43,6}, {46,11}, {45,16}, {41,21}};
    private int[][] coordinates_mage = {{20,1}, {1,6}, {1,16}, {21,11}, {0,26}, {13,21}, {25,26}, {40,11}};
    private int[][] coordinates_karps = {{45,11}, {46,21}, {26,16}, {45,26}, {15,16}};
    private int [][] getCoordinates_bubbles = {{17,1}, {17,11}, {17,21}, {28,1}, {28,5}, {38,15}, {28, 24}, {39, 4}, {39,14}, {39, 24}, {57, 5}, {56, 10}, {58, 20}, {82,5}, {82,11}, {83,20}, {69,5}, {70,24}, {94,23}};


    private ArrayList<Kicker> kickers = new ArrayList<>();
    private ArrayList<Magician> magicians = new ArrayList<>();
    private ArrayList<Archer> archers = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private ArrayList<Heart> hearts = new ArrayList<>();


    private ArrayList<Karp> karps = new ArrayList<>();
    private Eater shield;
    private Boss boss;
    private boolean paused = false;







    /*!< Referinta catre harta curenta.*/


    public Map getMap() {
        return map;
    }



    /*! \fn public Level1(RefLinks refLink)
                    \brief Constructorul de initializare al clasei

                    \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
                 */
    public Level3(RefLinks refLink)
    {

        ///Apel al constructorului clasei de baza
        super(refLink);
        buttons = new ArrayList<>();
        buttons.add(new Button(1210, 0, 45, 45, ""));
        buttons.add(new Button(1145, 0, 45, 45, "."));
        buttons.add(new Button(1100, 0, 45, 45, ","));
        ///Construieste harta jocului
        map = new Map(refLink);
        map.LoadWorld3();
        //refLink.SetMap(map);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.


        ///Construieste eroul
        hero = Hero.getInstance(refLink, 0, 0); // for singleton

        boss = new Boss(refLink, 4500, 1536);
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

                    Timer exitTimer = new Timer(500, e ->  DataBaseWriter.writeToDatabaseLoad(3,score, hero.getKills(), Box.getStars(), hero.GetLife()));
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
            boss.Update();

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


            if (hero.isAbletoWin() && Box.getStars() >= 7 && score >= 150 && boss.isDead()) {
                System.out.println("i can win");
                State.SetState(refLink.GetGame().getEnd());

                DataBaseWriter.writeToDatabaseScores(score, kills, refLink.GetGame().getPlayState().GetHero().GetLife(), Box.stars, "Surprisingly, did not die -> Normal");


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
            background = ImageIO.read(new File("res/level_3/Background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background,0,0, 1280,640, null);


        map.Draw(g);
        boss.Draw(g);



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
