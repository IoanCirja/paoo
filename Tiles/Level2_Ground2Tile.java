package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground2Tile extends Tile {
    public Level2_Ground2Tile(int id) {
        super(Assets.lvl2_ground_2, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
