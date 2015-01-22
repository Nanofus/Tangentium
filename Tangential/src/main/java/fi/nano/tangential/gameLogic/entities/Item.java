package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.ItemType;
import fi.nano.tangential.gameLogic.Position;

/**
 *
 * @author Nanofus
 */
public class Item extends Entity {

    private int power = 1;
    private ItemType type = ItemType.SLASH; //The types are SLASH, PIERCE, CRUSH, BURN, FREEZE and ARCANE

    public Item(int x, int y, String name, int power, ItemType type) {
        super(x,y,name);

        this.power = power;
        this.type = type;

        System.out.println("Generated item '" + name + "' with power " + power + " at " + GetPosition());
    }

}
