package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground8Tile extends Tile {
    public Level2_Ground8Tile(int id) {
        super(Assets.lvl2_ground_8, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
