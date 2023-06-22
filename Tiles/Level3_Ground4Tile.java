package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Ground4Tile extends Tile {
    public Level3_Ground4Tile(int id) {
        super(Assets.lvl3_ground_4, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
