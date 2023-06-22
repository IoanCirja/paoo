package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground10Tile extends Tile {
    public Level2_Ground10Tile(int id) {
        super(Assets.lvl2_ground_10, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
