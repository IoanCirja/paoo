package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground11Tile extends Tile {
    public Ground11Tile(int id) {
        super(Assets.ground_11, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
