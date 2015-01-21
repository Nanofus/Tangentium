
package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.TileType;

/**
 *
 * @author Nanofus
 */
public class Tile {
    private TileType tile;
    
    public Tile() {
        
    }
    
    public TileType GetType() {
        return tile;
    }
    
    public void SetTile(TileType type) {
        tile = type;
    }
    
}
