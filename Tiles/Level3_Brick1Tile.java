package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_Brick1Tile extends Tile {
    public Level3_Brick1Tile(int id) {
        super(Assets.lvl3_brick_1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
