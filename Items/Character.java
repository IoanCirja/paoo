package PaooGame.Items;

import PaooGame.RefLinks;
/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */

public abstract class Character extends Item
{
    public static final int DEFAULT_LIFES            = 7;
    public static final float DEFAULT_SPEED         = 12.0f;
    public static final int DEFAULT_CREATURE_WIDTH  = 200;
    protected boolean inv = false;
    public static final int DEFAULT_CREATURE_HEIGHT = 200;

    protected int life;
    protected float speed;
    protected float xMove;
    protected float yMove;
        /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */

    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x,y, width, height);

        life    = DEFAULT_LIFES;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    public void Move()
    {
        MoveX();
        MoveY();
    }

    public void MoveX()
    {
        x += xMove;
    }

    public void MoveY()
    {
        y += yMove;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int GetLife()
    {
        return life;
    }

    public float GetSpeed()
    {
        return speed;
    }

    public void IncreaseLife()
    {
        this.life++;
    }

    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    public float GetXMove()
    {
        return xMove;
    }
    public float GetYMove()
    {
        return yMove;
    }

    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }

    public void SetInvisible(boolean ins) { inv = ins;}
}

