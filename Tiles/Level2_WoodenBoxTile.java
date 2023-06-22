package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_WoodenBoxTile extends Tile {
    public Level2_WoodenBoxTile(int id) {
        super(Assets.lvl2_wooden_box, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
