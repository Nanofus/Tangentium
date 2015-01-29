package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.DamageType;
import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.Position;

/**
 *
 * @author Nanofus
 */
public class Actor extends Entity    {

    private Item wieldedItem;

    private int hitPoints = 1;
    
    private int slashResistance = 0;
    private int pierceResistance = 0;
    private int crushResistance = 0;
    private int burnResistance = 0;
    private int freezeResistance = 0;
    private int arcaneResistance = 0;

    private boolean controlled = false;
    private boolean aiEnabled = false;

    public Actor(int x, int y, String name, int hp, boolean controlled, int slashResistance, int pierceResistance, int crushResistance, int burnResistance, int freezeResistance, int arcaneResistance) {
        super(x,y,name);

        this.hitPoints = hp;
        this.slashResistance = slashResistance;
        this.pierceResistance = pierceResistance;
        this.crushResistance = crushResistance;
        this.burnResistance = burnResistance;
        this.freezeResistance = freezeResistance;
        this.arcaneResistance = arcaneResistance;
        
        this.controlled = controlled;

        if (!controlled) {
            aiEnabled = true;
        }

        if (controlled) {
            System.out.println("Spawned player at "+GetPosition());
        } else {
            System.out.println("Spawned enemy '" + name + "' at "+GetPosition());
        }
        
    }
    
    public int GetHealth() {
        return hitPoints;
    }
    
    public Item GetWeapon() {
        return wieldedItem;
    }

    public void LoseHealth(int amount) {
        hitPoints = hitPoints - amount;
    }

    public int GetResistance(DamageType damageType) {
        switch (damageType) {
            case SLASH:
                return slashResistance;
            case PIERCE:
                return pierceResistance;
            case CRUSH:
                return crushResistance;
            case BURN:
                return burnResistance;
            case FREEZE:
                return freezeResistance;
            case ARCANE:
                return arcaneResistance;
        }
        return 0;
    }
}
