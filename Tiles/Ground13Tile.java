package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground13Tile extends Tile {
    public Ground13Tile(int id) {
        super(Assets.ground_13, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
