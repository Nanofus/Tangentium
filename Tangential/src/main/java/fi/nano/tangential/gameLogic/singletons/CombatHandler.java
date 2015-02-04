package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.singletons.EntityManager;
import static fi.nano.tangential.gameLogic.enums.DamageType.SLASH;
import fi.nano.tangential.gameLogic.entities.Actor;

/**
 * Taistelusysteemin sisältävä luokka.
 * 
 * @author Nanofus
 */
public class CombatHandler {

    private final EntityManager entityManager;

    public CombatHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void Hit(Actor attacker, Actor target) {

        int targetResistance = target.GetResistance(attacker.GetWeapon().GetDamageType());

        int damage = attacker.GetWeapon().GetPower() - targetResistance;
        
        target.LoseHealth(damage);

        if (target.GetHealth() < 1) {
            DestroyActor(target);
        }
        
        System.out.println(attacker + " hit " + target + " and did "+damage+" damage!");

    }

    public void DestroyActor(Actor actor) {
        System.out.println("'" + actor + "' destroyed!");
        entityManager.DestroyActor(actor);
    }

}
