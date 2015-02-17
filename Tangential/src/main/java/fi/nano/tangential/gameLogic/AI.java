package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.enums.Stance;
import static fi.nano.tangential.gameLogic.enums.Stance.*;
import java.util.Random;

/**
 * Luokka, joka vastaa tekoälyn ohjaamien vihollishahmojen siirroista.
 *
 * @author Nanofus
 */
public class AI {

    Level level;
    Actor me;

    Stance stance = Wander;

    Random random = new Random();

    public AI(Actor me, Level level) {
        this.me = me;
        this.level = level;
    }

    /**
     * Metodi antaa AI:lle luvan tehdä siirtonsa.
     */
    public void MakeMove() {
        int action = random.nextInt(2);

        switch (action) {
            case 0:
                if (stance == Wander) {
                    MoveRandomly();
                } else if (stance == Chase) {
                    //Chase player
                } else if (stance == Flee) {
                    //Flee from player
                }
                break;
            case 1: //Skippaa vuoro
                SkipTurn();
                break;
        }

        //System.out.println("AI made a move!");
    }

    private void MoveRandomly() {
        int moveDirection = random.nextInt(4);
        switch (moveDirection) {
            case 0:
                me.Move(-1, 0);
                break;
            case 1:
                me.Move(1, 0);
                break;
            case 2:
                me.Move(0, 1);
                break;
            case 3:
                me.Move(0, -1);
                break;
        }
    }

    private void SkipTurn() {
        me.Rest();
    }

}
