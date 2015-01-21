package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.ItemType;
import fi.nano.tangential.gameLogic.Position;

/**
 *
 * @author Nanofus
 */
public class Item {

    private String name = "Default";
    private Character symbol = 'd';
    
    private Position position;

    private int power = 1;
    private ItemType type = ItemType.SLASH; //The types are SLASH, PIERCE, CRUSH, BURN, FREEZE and ARCANE

    public Item(int x, int y, int power, ItemType type) {
        position = new Position(x, y);

        this.power = power;
        this.type = type;

        switch (type) {
            case SLASH:
                name = "Sword";
                symbol = 't';
                break;
            case PIERCE:
                name = "Spear";
                symbol = 'r';
                break;
            case CRUSH:
                name = "Mace";
                symbol = 'm';
                break;
            case BURN:
                name = "Pyrospell";
                symbol = 'p';
                break;
            case FREEZE:
                name = "Ice Staff";
                symbol = 'i';
                break;
            case ARCANE:
                name = "Wand";
                symbol = 'w';
                break;
        }

        System.out.println("Generated item '" + name + "' with power " + power + " at " + position);
    }

    public Character GetSymbol() {
        return symbol;
    }

    public Position GetPosition() {
        return position;
    }

}
