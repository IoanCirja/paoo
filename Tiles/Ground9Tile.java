package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground9Tile extends Tile {
    public Ground9Tile(int id) {
        super(Assets.ground_9, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
