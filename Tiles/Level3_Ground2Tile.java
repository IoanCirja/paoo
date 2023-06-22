package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground2Tile extends Tile {
    public Level3_Ground2Tile(int id) {
        super(Assets.lvl3_ground_2, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
