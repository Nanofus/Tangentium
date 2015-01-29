
package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;

/**
 *
 * @author Nanofus
 */
public class AI {

    Actor me;
    
    public AI(Actor me) {
        this.me = me;
    }
    
    public void MakeMove() {
        System.out.println("AI made a move!");
    }
    
}
