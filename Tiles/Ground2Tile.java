package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground2Tile extends Tile {
    public Ground2Tile(int id) {
        super(Assets.ground_2, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
