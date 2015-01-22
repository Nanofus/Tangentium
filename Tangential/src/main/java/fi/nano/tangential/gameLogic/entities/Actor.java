package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Entity;
import fi.nano.tangential.gameLogic.Position;

/**
 *
 * @author Nanofus
 */
public class Actor extends Entity {

    private Item wieldedItem;

    private int hitPoints = 1;

    private boolean controlled = false;
    private boolean aiEnabled = false;

    public Actor(int x, int y, String name, int hp, boolean controlled) {
        super(x,y,name);

        this.hitPoints = hp;

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

    
}
