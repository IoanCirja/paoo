package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground12Tile extends Tile {
    public Level2_Ground12Tile(int id) {
        super(Assets.lvl2_ground_12, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
