package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground12Tile extends Tile {
    public Ground12Tile(int id) {
        super(Assets.ground_12, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
