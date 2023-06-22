package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground6Tile extends Tile {
    public Level3_Ground6Tile(int id) {
        super(Assets.lvl3_ground_6, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
