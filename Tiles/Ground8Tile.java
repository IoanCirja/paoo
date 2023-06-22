package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground8Tile extends Tile {
    public Ground8Tile(int id) {
        super(Assets.ground_8, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
