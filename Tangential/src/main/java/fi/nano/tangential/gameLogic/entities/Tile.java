
package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.enums.TileType;

/**
 *
 * @author Nanofus
 */
public class Tile {
    private TileType tile;
    private Character symbol;
    
    public Tile() {
        
    }
    
    public TileType GetType() {
        return tile;
    }
    
    public Character GetSymbol() {
        return symbol;
    }
    
    public void SetType(TileType type) {
        tile = type;
        
        switch(tile) {
            case WALL:
                symbol = 'X';
                break;
            case CHASM:
                symbol = ' ';
                break;
            case WATER:
                symbol = '~';
                break;
            case FLOOR:
                symbol = '.';
                break;
            case ICE:
                symbol = '_';
                break;
        }
    }
    
}
