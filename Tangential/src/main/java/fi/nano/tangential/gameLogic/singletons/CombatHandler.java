package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.singletons.EntityManager;
import static fi.nano.tangential.gameLogic.enums.DamageType.SLASH;
import fi.nano.tangential.gameLogic.entities.Actor;

/**
 *
 * @author Nanofus
 */
public class CombatHandler {

    private final EntityManager entityManager;

    public CombatHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void Hit(Actor actor1, Actor actor2) {

        int actor2Resistance = actor2.GetResistance(actor1.GetWeapon().GetDamageType());

        int damage = actor1.GetWeapon().GetPower() - actor2Resistance;
        
        actor2.LoseHealth(damage);

        if (actor2.GetHealth() < 1) {
            DestroyActor(actor2);
        }
        
        System.out.println(actor1 + " hit " + actor2 + " and did "+damage+" damage!");

    }

    public void DestroyActor(Actor actor) {
        System.out.println("'" + actor + "' destroyed!");
        entityManager.DestroyActor(actor);
    }

}
