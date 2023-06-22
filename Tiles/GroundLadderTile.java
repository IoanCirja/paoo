package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class GroundLadderTile extends Tile {
    private static boolean solid = true;
    public GroundLadderTile(int id) {
        super(Assets.ground_ladder, id);
    }

}
