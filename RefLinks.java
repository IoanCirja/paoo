package PaooGame;

import PaooGame.Input.MouseManager;
import PaooGame.Maps.Map;
import PaooGame.Input.KeyManager;
/*! \class public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */

public class RefLinks
{
    private Game game;
    private Map map;

    public RefLinks(Game game)
    {
        this.game = game;
    }

    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }
    public MouseManager GetMouseManager() {return game.getMouseManager();}
    public int GetWidth()
    {
        return game.GetWidth();
    }

    public int GetHeight()
    {
        return game.GetHeight();
    }

    public Game GetGame()
    {
        return game;
    }

    public void SetGame(Game game)
    {
        this.game = game;
    }

    public Map GetMap()
    {
        return map;
    }

    public void SetMap(Map map)
    {
        this.map = map;
    }
}
