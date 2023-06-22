package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground11Tile extends Tile {
    public Level3_Ground11Tile(int id) {
        super(Assets.lvl3_ground_11, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
