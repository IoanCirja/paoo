package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground6Tile extends Tile {
    public Ground6Tile(int id) {
        super(Assets.ground_6, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
