package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground10Tile extends Tile {
    public Level3_Ground10Tile(int id) {
        super(Assets.lvl3_ground_10, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
