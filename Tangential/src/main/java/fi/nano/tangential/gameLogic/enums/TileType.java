package fi.nano.tangential.gameLogic.enums;

/**
 * Pelimaailman ruudun tyyppi.
 * 
 * @author Nanofus
 */

public enum TileType {

    TILE(null),
        IMPASSABLE(TILE),
            WALL(IMPASSABLE),
            CHASM(IMPASSABLE),
            WATER(IMPASSABLE),
            PILLAR(IMPASSABLE),
                PILLAR_WITH_FLOOR(PILLAR),
                PILLAR_WITH_GRASS(PILLAR),
            DOOR(IMPASSABLE),
                DOOR_LEFT(DOOR),
            TREE(IMPASSABLE),
        PASSABLE(TILE),
            FLOOR(PASSABLE),
            LEVER(PASSABLE),
                LEVER_ON_FLOOR(LEVER),
            WINCIRCLE(PASSABLE),
            GRASS(PASSABLE),
            PATH(PASSABLE),
            ICE(PASSABLE);

    private TileType parent = null;

    private TileType(TileType parent) {
        this.parent = parent;
    }

    public boolean is(TileType other) {

        for (TileType t = this; t != null; t = t.parent) {
            if (other == t) {
                return true;
            }
        }

        return false;
    }
}
