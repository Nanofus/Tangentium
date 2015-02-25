package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.AI;
import fi.nano.tangential.gameLogic.enums.DamageType;
import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.enums.Stance;
import fi.nano.tangential.gameLogic.singletons.CombatHandler;

/**
 * Pelihahmoa hallitseva luokka. Voi olla controlled eli pelaajahahmo tai
 * ei-controlled, jolloin ohjaus annetaan tekoälylle ja saadaan vihollinen.
 *
 * @author Nanofus
 */
public class Actor extends Entity {

    private CombatHandler combatHandler;

    private Item wieldedItem;

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

    public Actor(int x, int y, String name, Level level, int hp, int maxhp, boolean controlled, int slashResistance, int pierceResistance, int crushResistance, int burnResistance, int freezeResistance, int arcaneResistance) {
        super(x, y, name, level);

        if (level != null) {
            this.combatHandler = level.GetCombatHandler();
        }

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
        } else {
            controlled = true;
        }

        /*if (controlled) {
         System.out.println("Spawned player at " + GetPosition());
         } else {
         System.out.println("Spawned enemy '" + name + "' at " + GetPosition());
         }*/
    }

    /**
     * Antaa esineen actorin käyttöön ja pudottaa vanhan nykyiseen sijaintiin.
     *
     * @param item Käyttöön otettava esine
     */
    public void EquipItem(Item item) {

        if (stun < 1) {
            if ("Health Potion".equals(item.GetName())) {
                if (GetHealth() != GetMaxHealth()) {
                    LoseHealth(-item.GetPower());
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
     * Pelaaja ottaa käyttöön esineen joka ruudussa on
     */
    public void EquipItemInTile() {
        Item item = GetLevel().GetItemInTile(GetPosition());
        if (item != null) {
            EquipItem(item);
        } else {
            LowerStun();
        }
    }

    /**
     * Pelaaja käyttää vipua tai muuta vastaavaa joka ruudussa on
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
    public void Move(int x, int y) {
        if (stun < 1) {
            Actor targetInTile = GetLevel().GetActorInTile(this.GetPosition().x + x, this.GetPosition().y + y);
            if (targetInTile != null && weaponDelay < 1) {
                if (!targetInTile.HasAI() || !this.HasAI()) {
                    combatHandler.Hit(this, GetLevel().GetActorInTile(this.GetPosition().x + x, this.GetPosition().y + y));
                }
            }
            super.Move(x, y);
            LowerWeaponDelay();
        } else {
            LowerStun();
        }
    }

    public void Rest() {
        LowerStun();
    }

    private void LowerStun() {
        if (stun > 0) {
            stun = stun - 1;
        }
        LowerWeaponDelay();
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
     * Vähentää - tai lisää - actorin terveyttä tietyllä määrällä.
     *
     * @param amount Määrä (positiivinen tekee vahinkoa, negatiivinen parantaa)
     */
    public void LoseHealth(int amount) {
        hitPoints = hitPoints - amount;

        if (hitPoints < 0) {
            hitPoints = 0;
        }
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
    }

    /**
     * Lamauttaa actorin hetkeksi
     *
     * @param duration Lamautuksen kesto vuoroissa.
     */
    public void AddStun(int duration) {
        stun = stun + duration;
    }

    /**
     * Estää actoria lyömästä hetkeen
     *
     * @param duration Lyönnineston kesto vuoroissa.
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

        smallestInString = smallestInString.substring(2); //Poista eka pilkku ja väli

        return smallestInString;
    }

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
