package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground9Tile extends Tile {
    public Level3_Ground9Tile(int id) {
        super(Assets.lvl3_ground_9, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
