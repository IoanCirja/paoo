package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Ground4Tile extends Tile {
    public Level2_Ground4Tile(int id) {
        super(Assets.lvl2_ground_4, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
