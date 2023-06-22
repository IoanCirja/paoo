package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground12Tile extends Tile {
    public Level3_Ground12Tile(int id) {
        super(Assets.lvl3_ground_12, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
