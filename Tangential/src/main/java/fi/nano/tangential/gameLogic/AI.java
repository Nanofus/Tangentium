
package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;

/**
 * Luokka, joka vastaa tekoälyn ohjaamien vihollishahmojen siirroista.
 * 
 * @author Nanofus
 */
public class AI {

    Level level;
    Actor me;
    
    public AI(Actor me, Level level) {
        this.me = me;
        this.level = level;
    }
    
    public void MakeMove() {
        //System.out.println("AI made a move!");
    }
    
}
