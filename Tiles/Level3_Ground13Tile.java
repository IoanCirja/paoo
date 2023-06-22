package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground13Tile extends Tile {
    public Level3_Ground13Tile(int id) {
        super(Assets.lvl3_ground_13, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
