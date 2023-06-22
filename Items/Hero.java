package PaooGame.Items;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import PaooGame.DataBaseWriter;
import PaooGame.RefLinks;
import PaooGame.States.Level1;
import PaooGame.States.Level2;
import PaooGame.States.Level3;
import PaooGame.States.State;
import PaooGame.Tiles.GroundLadderTile;
import PaooGame.Tiles.Tile;

import javax.swing.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.Items.Coin.score;
import static PaooGame.Items.Collisions.CanMoveHere;
import static PaooGame.Items.Constants.PlayerConstants.*;
import static java.awt.Color.GREEN;

//Implementeaza notiunea de erou/player (caracterul controlat de jucator).

public class Hero extends Character
{
    //sarit

    private boolean isJumping = false;
    private int jumpCurrentY = 0;
    private int jumpHeight = 148;
    private int jumpSpeed = 12;
    //afine transform pt flip

    private boolean isLeft = false;
    //power
    private boolean bubble_power = false;
    //falling
    private float gravity = 12f;


    //singleton

    private static Hero instance;

    public void setBubble_power(boolean bubble_power) {
        this.bubble_power = bubble_power;
    }

    public static int kills = 0;
    private boolean dead = false;
    private boolean abletoWin = false;


    private boolean isInvulnerable = false; // for bubble
    private long invulnerableStartTime;

    private int playerAction = IDLE; // animatii
    private int aniTick, aniIndex;

    private  boolean canClimb = false; // pt scara


    private boolean moving = true; // ca sa ma misc
    private int ofsetX = 45, ofsetY = 45; // ca sa centrez poza pt ca spriteul e mai mare cu spatiu transparent




    private Hero(RefLinks refLink, float x, float y)
    {

        super(refLink, x, y, 150, 150);




        bounds.width = 40;
        bounds.height = 70;



    }

//sinlgeton
    public static Hero getInstance(RefLinks refLink, float x, float y) {
        if (instance == null) {
            instance = new Hero(refLink, x, y);
        }
        return instance;
    }


    public boolean isAbletoWin() {
        return abletoWin;
    }

    public void setAbletoWin(boolean abletoWin) {
        this.abletoWin = abletoWin;
    }

    @Override
    public void Update() {

        if(!dead)
        {
            if (life >= DEFAULT_LIFES) // nr max de vieti
                life = DEFAULT_LIFES;
            GetInput();
            //miscare

            if (moving) {
                Move();
                bounds.x = (int) (x - refLink.GetGame().getCamera().GetxOffset() + ofsetX);
                bounds.y = (int) (y - refLink.GetGame().getCamera().GetyOffset() + ofsetY);

            }


            if (refLink.GetKeyManager().left || refLink.GetKeyManager().right) {
                playerAction = RUNNING;
            } else if (refLink.GetKeyManager().jump) {
                playerAction = JUMP;
            } else if (refLink.GetKeyManager().attack) {
                playerAction = SLASH;


            } else if (refLink.GetKeyManager().up || refLink.GetKeyManager().down) {
                playerAction = CLIMB;
            } else {
                playerAction = IDLE;
            }

            updateAnimationTick();

            refLink.GetGame().getCamera().followHero(this); // camera

                // parcurg mapa sa vad daca sunt pe spikeuri
            int xStart = (int) Math.max(0, refLink.GetGame().getCamera().GetxOffset() / Tile.TILE_WIDTH);
            int xEnd = (int) Math.min(width, (refLink.GetGame().getCamera().GetxOffset() + refLink.GetGame().GetWidth()) / Tile.TILE_WIDTH + 1);
            int yStart = (int) Math.max(0, refLink.GetGame().getCamera().GetyOffset() / Tile.TILE_HEIGHT);
            int yEnd = (int) Math.min(height, (refLink.GetGame().getCamera().GetyOffset() + refLink.GetGame().GetWidth()) / Tile.TILE_HEIGHT + 1);

            for (int y = yStart; y < yEnd; y++) {
                for (int x = xStart; x < xEnd; x++) {

                    if (refLink.GetMap().GetTile(y, x).GetId() == 29 || refLink.GetMap().GetTile(y, x).GetId() == 129 || refLink.GetMap().GetTile(y, x).GetId() == 229) // if it is a spike
                    {
                        Rectangle spikes = new Rectangle(
                                (int) (x * Tile.TILE_WIDTH - refLink.GetGame().getCamera().GetxOffset()),
                                (int) (y * Tile.TILE_HEIGHT - refLink.GetGame().getCamera().GetyOffset()),
                                64, 64);


                        if (bounds.intersects(spikes) && !bubble_power) {
                            hurtHero();

                        }

                    }


                }
            }
            //urcat pe scara
            if (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 7 ||
                    (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 14) ||
                    (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 107) ||
                    (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 114) ||
                    (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 207) ||
                    (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 214)
            ) {
                if (((refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 7) && refLink.GetKeyManager().up) ||
                        ((refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 107) && refLink.GetKeyManager().up) ||
                        ((refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 207) && refLink.GetKeyManager().up)


                ) // saritura de la finalul scarii
                    y = y - 100;

                canClimb = true;
            } else {
                canClimb = false;
            }

            //ating statuia -> can win + cerinte scor stele
            if (refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 30 || refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 130 || refLink.GetMap().GetTile((int) ((y + 75) / 64), (int) ((x + 75) / 64)).GetId() == 230) {

                abletoWin = true;
                System.out.println(abletoWin);
            }

            //pt bubble power invulnerabil 7 sec
            if (isInvulnerable && System.currentTimeMillis() > invulnerableStartTime + 1000) {
                isInvulnerable = false;
            }

            //sarituri

            if(refLink.GetKeyManager().jump && !isJumping && !CanMoveHere(x + ofsetX, y + yMove + ofsetY + gravity, bounds.width, bounds.height, refLink)) {
                isJumping = true;
            }
            if(isJumping) {
                if(jumpCurrentY >= jumpHeight) {
                    jumpCurrentY = 0;
                    isJumping = false;
                }
                else {
                    jumpCurrentY+=jumpSpeed;
                    if(CanMoveHere(x + ofsetX, y + ofsetY - jumpSpeed, bounds.width, bounds.height, refLink))
                        y -= jumpSpeed;
                }
            }



            // animatii cand moare
            if (aniIndex >= GetSpriteAmount(DIE) && playerAction == DIE) {
                aniIndex = 0;
                playerAction = IDLE;
            }

            if (life <= 0) {
                playerAction = DIE;
            }

            if (playerAction == DIE && aniIndex >= GetSpriteAmount(playerAction)) {

                dead = true;

                    // write to database cand moare + that note :))
                if (State.GetState().getClass().equals(Level1.class)) {
                    DataBaseWriter.writeToDatabaseScores(kills, score, refLink.GetGame().getPlayState().GetHero().GetLife(), Box.stars, "Died at Lvl 1 -> Incompetent");

                } else if (State.GetState().getClass().equals(Level2.class)) {
                    DataBaseWriter.writeToDatabaseScores(kills, score, refLink.GetGame().getPlayState().GetHero().GetLife(), Box.stars, "Died at Lvl 2 -> Easily Handicapped");
                } else if (State.GetState().getClass().equals(Level3.class)) {
                    DataBaseWriter.writeToDatabaseScores(kills, score, refLink.GetGame().getPlayState().GetHero().GetLife(), Box.stars, "Died at Lvl 3 -> Almost Normal");
                }





            }
            //cazut

            if(!isJumping && CanMoveHere(x +ofsetX, y + yMove + ofsetY +gravity, bounds.width, bounds.height, refLink))
            {
                playerAction = FALLING;
                aniIndex = 0;
                y+=gravity;
            }

            //climbing

            if (refLink.GetKeyManager().up && canClimb) {
                playerAction= CLIMB;

            }




        }


    }




    public int getPlayerAction() {
        return playerAction;
    }

    private void GetInput()


    {
        // miscareeeeee
        xMove = 0;
        yMove = 0;
        moving = false;



        //keyboard

        if (refLink.GetKeyManager().down) {
            yMove = speed;
        }
        if (refLink.GetKeyManager().up && canClimb) {
            playerAction= CLIMB;
            yMove = - 20;
        }

        if (refLink.GetKeyManager().left) {
            isLeft = true;
            xMove= -speed;

        }
        if (refLink.GetKeyManager().right) {
            isLeft = false;
            xMove= speed;

        }

        //COLIZIUNIIIIII

        if (CanMoveHere(x + xMove + ofsetX, y + yMove + ofsetY, bounds.width, bounds.height, refLink)) {
            moving = true;
        }

    }

    public int getAniIndex() {
        return aniIndex;
    }

    @Override
    public void Draw(Graphics g)
    {
        if(!dead)
        {

            BufferedImage img = sifAnimations[playerAction][aniIndex];
            if(!isInvulnerable)
            {

                if (isLeft) {
                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    tx.translate(-img.getWidth(null), 0);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    img = op.filter(img, null);
                }


                g.drawImage(img, (int) (x - refLink.GetGame().getCamera().GetxOffset()),
                        (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);




                if(bubble_power)
                {
                    g.drawImage(bubble, bounds.x - 30, bounds.y - 30, 120, 120, null);
                }


            }
            else { // PT POZE INTOARSE LA STANGA
                if((System.currentTimeMillis() - invulnerableStartTime) % 200 < 100)
                {


                    if (isLeft) {
                        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                        tx.translate(-img.getWidth(null), 0);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        img = op.filter(img, null);
                    }


                    g.drawImage(img, (int) (x - refLink.GetGame().getCamera().GetxOffset()),
                            (int) (y - refLink.GetGame().getCamera().GetyOffset()), width, height, null);

                    if(bubble_power)
                    {
                        g.drawImage(bubble, bounds.x - 30, bounds.y - 30, 120, 120, null);
                    }


                }

            }

        }
        else
        { // cand moare sa arate well someone died

            int windowWidth = 1280; // Game window width
            int windowHeight = 640; // Game window height

            int imageWidth = 380; // Width of the image
            int imageHeight = 150; // Height of the image

            int imageX = (windowWidth - imageWidth) / 2;
            int imageY = (windowHeight - imageHeight) / 2;

            g.drawImage(grey, imageX, imageY, imageWidth, imageHeight, null);
            Font cartoonFont = new Font("Comic Sans MS", Font.BOLD, 30);

            g.setFont(cartoonFont);
            g.setColor(new Color(100, 65, 23));

            g.drawString("Well, someone died...", imageX + 20, imageY + 75);

            //dupa 5 sec ma pune in menustate si reseteaza jocu
            Timer task = new Timer(5000, e -> {
                dead = false;
                State.SetState(refLink.GetGame().getMenuState());
                refLink.GetGame().resetLevels(refLink);
                this.SetX(0);
                this.SetY(0);
                Box.stars = 0;
                score = 0;
                Hero.kills = 0;
                this.setLife(Character.DEFAULT_LIFES);


            });

            task.setRepeats(false);
            task.start();

        }

    }


    public boolean isBubble_power() {
        return bubble_power;
    }

    private void updateAnimationTick()
    {
        // parcurgere vector de aniamtii
        aniTick++;
        float aniSpeed = 0.5f;
        if (aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction))
            {
                aniIndex = 0;
            }


        }
    }

    public  int getKills() {
        return kills;
    }

    public void hurtHero()
    {
        //da pumni la hero
        if (!isInvulnerable) {
            life--;
            isInvulnerable = true;
            invulnerableStartTime = System.currentTimeMillis();
        }

    }



    public void IncreaseKills(){kills++;} // kills++;

}
