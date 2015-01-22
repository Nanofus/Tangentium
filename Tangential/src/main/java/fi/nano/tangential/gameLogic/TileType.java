
package fi.nano.tangential.gameLogic;

// Hierarkkisen enumin toteutus. Mallia otettu: http://alexradzin.blogspot.hk/2010/10/hierarchical-structures-with-java-enums_05.html

public enum TileType {
    
    TILE(null),
        IMPASSABLE (TILE),
            WALL (IMPASSABLE),
            CHASM (IMPASSABLE),
            WATER (IMPASSABLE),
        PASSABLE (TILE),
            FLOOR (PASSABLE),
            ICE (PASSABLE);
            
    private TileType parent = null;
    private TileType(TileType parent) {
        this.parent = parent;
    }
    
    public boolean is(TileType other) {
    if (other == null) {
        return false;
    }
    
    for (TileType t = this;  t != null;  t = t.parent) {
        if (other == t) {
            return true;
        }
    }
    return false;
}
}
