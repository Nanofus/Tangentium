package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.enums.DamageType;

/**
 * Pelimaailman esine. Hahmo voi poimia esineen käyttöönsä.
 *
 * @author Nanofus
 */
public class Item extends Entity {

    private int power = 1;
    private DamageType type = DamageType.Slash; //The types are SLASH, PIERCE, CRUSH, BURN, FREEZE and ARCANE

    private boolean equipped = false;

    /**
     * Luo esineen annetuilla parametreillä
     * @param x X-sijainti
     * @param y Y-sijainti
     * @param name Esineen nimi - potion-nimet luovat potion-esineen
     * @param level Pelin taso
     * @param power Esineen voima/teho
     * @param type Esineen vahinkotyyppi
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
     * Lisää (tai negatiivisilla arvoilla vähentää) aseen voimaa pysyvästi
     *
     * @param p Määrä
     */
    public void AddPower(int p) {
        power = power + p;
    }

    /**
     * Merkitsee, onko esine käytössä. Tosi piilottaa esineen kentältä, epätosi
     * näyttää sen taas.
     *
     * @param b Onko esine käytössä
     */
    public void SetEquipped(boolean b) {
        equipped = b;
    }

    /**
     * Palauttaa tiedon onko esine käytössä.
     * @return Onko käytössä
     */
    public boolean IsEquipped() {
        return equipped;
    }

}
