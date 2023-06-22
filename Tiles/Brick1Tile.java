package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Brick1Tile extends Tile {
    public Brick1Tile(int id) {
        super(Assets.brick_1, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
