package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.enums.DamageType;
import fi.nano.tangential.gameLogic.Position;

/**
 * Pelimaailman esine. Hahmo voi poimia esineen käyttöönsä.
 * 
 * @author Nanofus
 */
public class Item extends Entity {

    private int power = 1;
    private DamageType type = DamageType.SLASH; //The types are SLASH, PIERCE, CRUSH, BURN, FREEZE and ARCANE
    
    private boolean equipped = false;

    public Item(int x, int y, String name, int power, DamageType type) {
        super(x,y,name);

        this.power = power;
        this.type = type;

        System.out.println("Generated item '" + name + "' with power " + power + " at " + GetPosition());
    }
    
    public DamageType GetDamageType() {
        return type;
    }
    
    public int GetPower() {
        return power;
    }

    public void SetEquipped(boolean b) {
        equipped = b;
    }
    
    public boolean IsEquipped() {
        return equipped;
    }

}
