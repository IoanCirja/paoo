package PaooGame.Items;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.sql.Ref;
//coliziuni de tip aabb verifica colturile

public class Collisions {

        public static boolean CanMoveHere(float x, float y, float width, float height, RefLinks refLinks) {
            if (!IsSolid(x, y, refLinks))
                if (!IsSolid(x + width, y + height, refLinks))
                    if (!IsSolid(x + width, y, refLinks))
                        if (!IsSolid(x, y + height, refLinks))
                            return true;
            return false;
        }

        private static boolean IsSolid(float x, float y, RefLinks refLinks) {
            if (x < 0 || x >=  6400)
                return true;
            if (y < 0 || y >= 1920)
                return true;

            float xIndex = x/64;
            float yIndex = y/64;


            if(refLinks.GetMap().GetTile((int) yIndex,(int) xIndex).IsSolid())

                return true;
            return false;
        }
}

