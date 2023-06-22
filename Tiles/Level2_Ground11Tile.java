package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground11Tile extends Tile {
    public Level2_Ground11Tile(int id) {
        super(Assets.lvl2_ground_11, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
