package fi.nano.tangentium.gameLogic.singletons;

import fi.nano.tangentium.gameLogic.singletons.EntityManager;
import static fi.nano.tangentium.gameLogic.enums.DamageType.Slash;
import fi.nano.tangentium.gameLogic.entities.Actor;

/**
 * Taistelusysteemin sisältävä luokka.
 *
 * @author Nanofus
 */
public class CombatHandler {

    private final EntityManager entityManager;

    private int defaultStun = 3;
    private int defaultWeaponDelay = 4;

    /**
     * Creates a new CombatHandler.
     * Needs an EntityManager to remove Actors from the game.
     * @param entityManager EntityManager instance
     */
    public CombatHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * An Actor hits another and deals damage.
     *
     * @param attacker Attacker
     * @param target Target
     */
    public void Hit(Actor attacker, Actor target) {
        if (attacker.GetWeapon() != null && target.GetStun() < 1) {
            int targetResistance = target.GetResistance(attacker.GetWeapon().GetDamageType());

            int damage = attacker.GetWeapon().GetPower() - targetResistance;

            if (damage >= 0) {
                target.ChangeHealth(-damage);
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
     * Removes an actor from the game world.
     *
     * @param actor Removed actor
     */
    public void DestroyActor(Actor actor) {
        System.out.println("'" + actor + "' destroyed!");
        entityManager.DestroyActor(actor);
    }

}
