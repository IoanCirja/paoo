package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Ground10Tile extends Tile {
    public Ground10Tile(int id) {
        super(Assets.ground_10, id);
    }



    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
