package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Level2_WoodenBarrelTile extends Tile {
    public Level2_WoodenBarrelTile(int id) {
        super(Assets.lvl2_wooden_barrel, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
