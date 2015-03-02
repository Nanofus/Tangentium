package fi.nano.tangentium.gameLogic.entities;

import fi.nano.tangentium.gameLogic.enums.TileAction;
import fi.nano.tangentium.gameLogic.enums.TileType;

/**
 * A tile in the game world
 *
 * @author Nanofus
 */
public class Tile {

    private TileType tile;
    private TileAction action;
    private TileType changeType;

    private int actionId = 0;
    private boolean active = false;
    private TileType originalType;

    private Character symbol = 'Ö';

    public Tile() {

    }

    public TileType GetType() {
        return tile;
    }

    public Character GetSymbol() {
        return symbol;
    }

    public TileAction GetAction() {
        return action;
    }

    public int GetActionId() {
        return actionId;
    }

    public boolean IsActive() {
        return active;
    }

    public TileType GetChangeType() {
        return changeType;
    }

    /**
     * Sets the tile type the tile returns to after it's been activated and deactivated.
     * @param type Old tile type
     */
    public void SetOriginalType(TileType type) {
        originalType = type;
    }

    public TileType GetOriginalType() {
        return originalType;
    }

    /**
     * Sets the tile type. Can be used dynamically.
     *
     * @param type Tilen tyyppi
     */
    public void SetType(TileType type) {
        tile = type;

        switch (tile) {
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
            case DOOR_RIGHT:
                symbol = 'b';
            case GRASS:
                symbol = ',';
            case PATH:
                symbol = ';';
            case TREE:
                symbol = 'T';
        }
    }

    /**
     * Sets the tile's special action.
     *
     * @param action Special action
     */
    public void SetAction(TileAction action) {
        this.action = action;
    }

    /**
     * Sets the tile's special action's ID.
     *
     * @param id ID
     */
    public void SetActionId(int id) {
        this.actionId = id;
    }

    /**
     * Sets the tile activated or not activated.
     * @param b True = activated, false = not activated
     */
    public void SetActive(boolean b) {
        active = b;
    }
    
    /**
     * Sets the tile to which the tile changes when activated.
     * @param type Tile type
     */
    public void SetChangeType(TileType type) {
        changeType = type;
    }

    @Override
    public String toString() {
        return "Tile symbol: " + symbol;
    }

}
