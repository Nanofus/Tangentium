package fi.nano.tangential.gameLogic;

/**
 * Apuluokka sijaintitiedon käsittelyyn. Yksinkertaisuuden nimissä x- ja
 * y-parametreja on tarkoitus muuttaa suoraan, ilman setteriä.
 *
 * @author Nanofus
 */
public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean is(Position other) {
        if(other.x == x && other.y == y) {
            return true;
        } else {
            return false;
        }
    }

}
