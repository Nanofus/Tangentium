package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.singletons.EntityManager;
import static fi.nano.tangential.gameLogic.enums.DamageType.Slash;
import fi.nano.tangential.gameLogic.entities.Actor;

/**
 * Taistelusysteemin sisältävä luokka.
 *
 * @author Nanofus
 */
public class CombatHandler {

    private final EntityManager entityManager;
    
    private int defaultStun = 3;
    private int defaultWeaponDelay = 4;

    public CombatHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Actor lyö toista ja tekee vahinkoa aseensa voiman verran.
     *
     * @param attacker Lyöjä
     * @param target Kohde
     */
    public void Hit(Actor attacker, Actor target) {
        if (attacker.GetWeapon() != null) {
            int targetResistance = target.GetResistance(attacker.GetWeapon().GetDamageType());

            int damage = attacker.GetWeapon().GetPower() - targetResistance;

            if (damage >= 0) {
                target.LoseHealth(damage);
            }

            System.out.println("'" + attacker + "' hit '" + target + "' and did '" + damage + "' damage!");

            if (target.GetHealth() < 1) {
                DestroyActor(target);
            }

            target.AddStun(defaultStun);
            attacker.AddWeaponDelay(defaultWeaponDelay);

        } else {
            System.out.println("Attacker has no weapon!");
        }
    }

    /**
     * Poistaa actorin pelikentältä.
     *
     * @param actor Kohde-actor
     */
    public void DestroyActor(Actor actor) {
        System.out.println("'" + actor + "' destroyed!");
        entityManager.DestroyActor(actor);
    }

}
