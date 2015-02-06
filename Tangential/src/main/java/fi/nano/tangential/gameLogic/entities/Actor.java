package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.AI;
import fi.nano.tangential.gameLogic.enums.DamageType;
import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.Position;

/**
 * Pelihahmoa hallitseva luokka. Voi olla controlled eli pelaajahahmo tai
 * ei-controlled, jolloin ohjaus annetaan tekoälylle ja saadaan vihollinen.
 *
 * @author Nanofus
 */
public class Actor extends Entity {

    private Item wieldedItem;

    private int hitPoints = 1;

    private int stun = 0;

    private int slashResistance = 0;
    private int pierceResistance = 0;
    private int crushResistance = 0;
    private int burnResistance = 0;
    private int freezeResistance = 0;
    private int arcaneResistance = 0;

    private boolean controlled = false;
    private boolean aiEnabled = false;

    private AI ai;

    public Actor(int x, int y, String name, Level level, int hp, boolean controlled, int slashResistance, int pierceResistance, int crushResistance, int burnResistance, int freezeResistance, int arcaneResistance) {
        super(x, y, name, level);

        this.hitPoints = hp;
        this.slashResistance = slashResistance;
        this.pierceResistance = pierceResistance;
        this.crushResistance = crushResistance;
        this.burnResistance = burnResistance;
        this.freezeResistance = freezeResistance;
        this.arcaneResistance = arcaneResistance;

        this.controlled = controlled;

        if (!controlled) {
            ai = new AI(this, level);
            aiEnabled = true;
        } else {
            controlled = true;
        }

        /*if (controlled) {
         System.out.println("Spawned player at " + GetPosition());
         } else {
         System.out.println("Spawned enemy '" + name + "' at " + GetPosition());
         }*/
    }

    public void EquipItem(Item item) {
        if (stun < 1) {
            if (wieldedItem != null) {
                wieldedItem.SetEquipped(false);
                wieldedItem.SetPosition(GetPosition().x, GetPosition().y);
            }
            wieldedItem = item;
            item.SetEquipped(true);
        } else {
            LowerStun();
        }
    }

    @Override
    public void Move(int x, int y) {
        if (stun < 1) {
            super.Move(x, y);
        } else {
            LowerStun();
        }
    }
    
    private void LowerStun() {
        stun = stun - 1;
    }

    public int GetHealth() {
        return hitPoints;
    }

    public Item GetWeapon() {
        return wieldedItem;
    }

    public AI GetAI() {
        return ai;
    }

    public boolean HasAI() {
        if (aiEnabled) {
            return true;
        } else {
            return false;
        }
    }

    public void LoseHealth(int amount) {
        hitPoints = hitPoints - amount;

        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    public void AddStun(int duration) {
        stun = duration;
    }

    public int GetStun() {
        return stun;
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
