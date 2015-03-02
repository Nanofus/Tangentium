package fi.nano.tangentium.gameLogic.enums;

/**
 * The different tile types.
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
                DOOR_RIGHT(DOOR),
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

    /**
     * Compares two tile types in the hierarchy to see if this tile is the other's subtype.
     * @param other Other tile type
     * @return Is a subtype of the other?
     */
    public boolean is(TileType other) {

        for (TileType t = this; t != null; t = t.parent) {
            if (other == t) {
                return true;
            }
        }

        return false;
    }
}
