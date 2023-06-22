package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level3_WoodenBarrelTile extends Tile {
    public Level3_WoodenBarrelTile(int id) {
        super(Assets.lvl3_wooden_barrel, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
