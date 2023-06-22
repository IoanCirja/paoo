package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_Brick1Tile extends Tile {
    public Level2_Brick1Tile(int id) {
        super(Assets.lvl2_brick_1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
