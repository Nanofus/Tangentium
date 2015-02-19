
package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.enums.TileAction;
import fi.nano.tangential.gameLogic.enums.TileType;

/**
 * Pelimaailman ruutu. On tiettyä tyyppiä (TileType).
 * 
 * @author Nanofus
 */
public class Tile {
    private TileType tile;
    private TileAction action;
    
    private int actionId = 0;
    
    private Character symbol = 'Ö';
    
    public Tile() {
        
    }
    
    public TileType GetType() {
        return tile;
    }
    
    public Character GetSymbol() {
        return symbol;
    }
    
    /**
     * Asettaa tilen tietyn tyyppiseksi. Voidaan käyttää tason luonnissa tai sen muokkaamisessa dynaamisesti.
     * 
     * @param type Tilen tyyppi
     */
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
            case PILLAR_WITH_FLOOR:
                symbol = 'I';
            case PILLAR_WITH_GRASS:
                symbol = 'G';
            case LEVER_ON_FLOOR:
                symbol = 'l';
            case DOOR_LEFT:
                symbol = 'd';
            case GRASS:
                symbol = ',';
            case PATH:
                symbol = ';';
            case TREE:
                symbol = 'T';
        }
    }
    
    /**
     * Määrittelee tilen erikoislaatuisen luonteen
     * 
     * @param action Erikoistoiminto
     * @param id ID esimerkiksi vipuja varten
     */
    public void SetTileAction(TileAction action) {
        this.action = action;
    }
    
    /**
     * Määrittelee tilen erikoistoiminnon ID:n
     * 
     * @param id ID-numero
     */
    public void SetTileActionId(int id) {
        this.actionId = id;
    }
    
    @Override
    public String toString() {
        return "Tile symbol: "+symbol;
    }
    
}