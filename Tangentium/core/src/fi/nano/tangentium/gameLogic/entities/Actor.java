package fi.nano.tangentium.gameLogic.entities;

import fi.nano.tangentium.gameLogic.AI;
import fi.nano.tangentium.gameLogic.enums.DamageType;
import fi.nano.tangentium.gameLogic.Entity;
import fi.nano.tangentium.gameLogic.Level;
import fi.nano.tangentium.gameLogic.enums.Stance;
import fi.nano.tangentium.gameLogic.singletons.CombatHandler;

/**
 * Characters in the game world.
 *
 * @author Nanofus
 */
public class Actor extends Entity {

    private CombatHandler combatHandler;

    private Item wieldedItem;
    
    private int faction = 0;

    private int hitPoints = 1;
    private int maxHitPoints = 1;

    private int stun = 0;
    private int weaponDelay = 0;

    private int slashResistance = 0;
    private int pierceResistance = 0;
    private int crushResistance = 0;
    private int burnResistance = 0;
    private int freezeResistance = 0;
    private int arcaneResistance = 0;

    private boolean controlled = false;
    private boolean aiEnabled = false;

    private AI ai;

    /**
     * Creates a new Actor.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param name Name
     * @param level Game level
     * @param hp Health in the beginning
     * @param maxhp Maximum health
     * @param controlled Who controls the Actor
     * @param slashResistance Slash damage resistance
     * @param pierceResistance Pierce damage resistance
     * @param crushResistance Crush damage resistance
     * @param burnResistance Burn damage resistance
     * @param freezeResistance Freeze damage resistance
     * @param arcaneResistance Arcane damage resistance
     */
    public Actor(int x, int y, String name, int faction, Level level, int hp, int maxhp, boolean controlled, int slashResistance, int pierceResistance, int crushResistance, int burnResistance, int freezeResistance, int arcaneResistance) {
        super(x, y, name, level);

        if (level != null) {
            this.combatHandler = level.GetCombatHandler();
        }
        
        this.faction = faction;
        this.hitPoints = hp;
        this.maxHitPoints = maxhp;
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
        }

        /*if (controlled) {
         System.out.println("Spawned player at " + GetPosition());
         } else {
         System.out.println("Spawned enemy '" + name + "' at " + GetPosition());
         }*/
    }

    /**
     * Actor uses an item. If it's a weapon, it's equipped. If it's a potion,
     * it's used.
     *
     * @param item Item in tile
     */
    public void UseItem(Item item) {

        if (stun < 1) {
            if (item.GetName().equals("Health Potion")) {
                if (GetHealth() != GetMaxHealth()) {
                    ChangeHealth(item.GetPower());
                    GetLevel().GetEntityManager().DestroyItem(item);
                }
            } else if (item.GetName().equals("Buff Potion")) {
                if (GetWeapon() != null) {
                    GetWeapon().AddPower(item.GetPower());
                    GetLevel().GetEntityManager().DestroyItem(item);
                }
            } else if (!item.IsEquipped()) {
                DropItem();
                wieldedItem = item;
                item.SetEquipped(true);
                System.out.println("'" + GetName() + "' picked up item " + item.GetName());
                LowerWeaponDelay();
            } else {
                LowerStun();
            }
        }
    }

    /**
     * Actor uses the item in the tile.
     */
    public void UseItemInTile() {
        Item item = GetLevel().GetItemInTile(GetPosition());
        if (item != null) {
            UseItem(item);
        } else {
            LowerStun();
        }
    }

    /**
     * Actor uses a tile action, such as a lever.
     */
    public void UseActionTile() {
        if (stun < 1) {
            GetLevel().ActivateTile(GetPosition());
            LowerWeaponDelay();
        } else {
            LowerStun();
        }
    }

    private void DropItem() {
        if (wieldedItem != null) {
            wieldedItem.SetEquipped(false);
            wieldedItem.SetPosition(GetPosition().x, GetPosition().y);
        }
    }

    @Override
    public boolean Move(int x, int y) {
        boolean actionDone;
        if (stun < 1) {
            Actor targetInTile = GetLevel().GetActorInTile(this.GetPosition().x + x, this.GetPosition().y + y);
            if (targetInTile != null && weaponDelay < 1) {
                if (!targetInTile.HasAI() || !this.HasAI()) {
                    combatHandler.Hit(this, GetLevel().GetActorInTile(this.GetPosition().x + x, this.GetPosition().y + y));
                }
            }
            actionDone = super.Move(x, y);
        } else {
            actionDone = true;
        }

        if (actionDone) {
            Rest();
        }

        return actionDone;
    }

    /**
     * Actor rests at the end of its turn.
     */
    public void Rest() {
        LowerStun();
        LowerWeaponDelay();
    }

    private void LowerStun() {
        if (stun > 0) {
            stun = stun - 1;
        }
    }

    private void LowerWeaponDelay() {
        if (weaponDelay > 0) {
            weaponDelay = weaponDelay - 1;
        }
    }

    public int GetHealth() {
        return hitPoints;
    }

    public int GetMaxHealth() {
        return maxHitPoints;
    }

    public Item GetWeapon() {
        return wieldedItem;
    }

    public AI GetAI() {
        return ai;
    }

    /**
     * Gets the Actor's stance. Only AI controlled Actors have stances.
     *
     * @return Stance of the Actor, if not AI controlled Stance.None is
     * returned.
     */
    public Stance GetStance() {
        if (HasAI()) {
            return ai.GetStance();
        } else {
            return Stance.None;
        }
    }

    public boolean HasAI() {
        return aiEnabled;
    }

    /**
     * Changes the Actor's health by a set amount.
     *
     * @param amount Amount (negative does damage, positive heals)
     */
    public void ChangeHealth(int amount) {
        hitPoints = hitPoints + amount;

        if (hitPoints < 0) {
            hitPoints = 0;
        }
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
    }

    /**
     * Stuns the actor for a set time.
     *
     * @param duration Stun in turns.
     */
    public void AddStun(int duration) {
        stun = stun + duration;
    }

    /**
     * Prevents the Actor from attacking for a set amount of turns.
     *
     * @param duration Attack prevention in turns.
     */
    public void AddWeaponDelay(int duration) {
        weaponDelay = weaponDelay + duration;
    }

    public int GetStun() {
        return stun;
    }

    public int GetWeaponDelay() {
        return weaponDelay;
    }

    public int GetResistance(DamageType damageType) {
        switch (damageType) {
            case Slash:
                return slashResistance;
            case Pierce:
                return pierceResistance;
            case Crush:
                return crushResistance;
            case Burn:
                return burnResistance;
            case Freeze:
                return freezeResistance;
            case Arcane:
                return arcaneResistance;
        }
        return 0;
    }

    /**
     * Gets the list of weaknesses for UI use.
     *
     * @return Weaknesses as a string
     */
    public String GetWeakness() {
        int smallestValue;
        String smallestInString = "";

        smallestValue = slashResistance;
        if (smallestValue > pierceResistance) {
            smallestValue = pierceResistance;
        }
        if (smallestValue > crushResistance) {
            smallestValue = crushResistance;
        }
        if (smallestValue > burnResistance) {
            smallestValue = burnResistance;
        }
        if (smallestValue > freezeResistance) {
            smallestValue = freezeResistance;
        }
        if (smallestValue > arcaneResistance) {
            smallestValue = arcaneResistance;
        }

        if (smallestValue == slashResistance) {
            smallestInString = smallestInString + ", Slash";
        }
        if (smallestValue == pierceResistance) {
            smallestInString = smallestInString + ", Pierce";
        }
        if (smallestValue == crushResistance) {
            smallestInString = smallestInString + ", Crush";
        }
        if (smallestValue == burnResistance) {
            smallestInString = smallestInString + ", Burn";
        }
        if (smallestValue == freezeResistance) {
            smallestInString = smallestInString + ", Freeze";
        }
        if (smallestValue == arcaneResistance) {
            smallestInString = smallestInString + ", Arcane";
        }

        smallestInString = smallestInString.substring(2); //Remove the first comma and space.

        return smallestInString;
    }

    /**
     * Gets the list of strengths for UI use.
     *
     * @return Strengths as a string
     */
    public String GetStrength() {
        int biggestValue;
        String biggestInString = "";

        biggestValue = slashResistance;
        if (biggestValue < pierceResistance) {
            biggestValue = pierceResistance;
        }
        if (biggestValue < crushResistance) {
            biggestValue = crushResistance;
        }
        if (biggestValue < burnResistance) {
            biggestValue = burnResistance;
        }
        if (biggestValue < freezeResistance) {
            biggestValue = freezeResistance;
        }
        if (biggestValue < arcaneResistance) {
            biggestValue = arcaneResistance;
        }

        if (biggestValue == slashResistance) {
            biggestInString = biggestInString + ", Slash";
        }
        if (biggestValue == pierceResistance) {
            biggestInString = biggestInString + ", Pierce";
        }
        if (biggestValue == crushResistance) {
            biggestInString = biggestInString + ", Crush";
        }
        if (biggestValue == burnResistance) {
            biggestInString = biggestInString + ", Burn";
        }
        if (biggestValue == freezeResistance) {
            biggestInString = biggestInString + ", Freeze";
        }
        if (biggestValue == arcaneResistance) {
            biggestInString = biggestInString + ", Arcane";
        }

        biggestInString = biggestInString.substring(2);

        return biggestInString;
    }
}
