package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground13Tile extends Tile {
    public Level2_Ground13Tile(int id) {
        super(Assets.lvl2_ground_13, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
