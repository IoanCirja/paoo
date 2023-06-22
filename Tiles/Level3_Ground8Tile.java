package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground8Tile extends Tile {
    public Level3_Ground8Tile(int id) {
        super(Assets.lvl3_ground_8, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
