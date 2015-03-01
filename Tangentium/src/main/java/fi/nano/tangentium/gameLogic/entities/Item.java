package fi.nano.tangentium.gameLogic.entities;

import fi.nano.tangentium.gameLogic.Entity;
import fi.nano.tangentium.gameLogic.Level;
import fi.nano.tangentium.gameLogic.enums.DamageType;

/**
 * An item in the game world.
 *
 * @author Nanofus
 */
public class Item extends Entity {

    private int power = 1;
    private DamageType type = DamageType.Slash; //The types are SLASH, PIERCE, CRUSH, BURN, FREEZE and ARCANE

    private boolean equipped = false;

    /**
     * Creates a new Item.
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param name Item name
     * @param level Game level
     * @param power Item power
     * @param type Item damage type
     */
    public Item(int x, int y, String name, Level level, int power, DamageType type) {
        super(x, y, name, level);

        this.power = power;
        this.type = type;

        //System.out.println("Generated item '" + name + "' with power " + power + " at " + GetPosition());
    }

    public DamageType GetDamageType() {
        return type;
    }

    public int GetPower() {
        return power;
    }

    /**
     * Buff (or debuff) the item's power by a set amount.
     *
     * @param p Amount
     */
    public void AddPower(int p) {
        power = power + p;
    }

    /**
     * Sets the item as equipped or unequipped. If it is equipped, it's not drawn by renderer.
     *
     * @param b Is the item in use?
     */
    public void SetEquipped(boolean b) {
        equipped = b;
    }

    public boolean IsEquipped() {
        return equipped;
    }

}
