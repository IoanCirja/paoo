package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_WoodenBoxTile extends Tile {
    public Level3_WoodenBoxTile(int id) {
        super(Assets.lvl3_wooden_box, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
