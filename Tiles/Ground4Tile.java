package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground4Tile extends Tile {
    public Ground4Tile(int id) {
        super(Assets.ground_4, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
