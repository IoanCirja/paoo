package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground6Tile extends Tile {
    public Level2_Ground6Tile(int id) {
        super(Assets.lvl2_ground_6, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
