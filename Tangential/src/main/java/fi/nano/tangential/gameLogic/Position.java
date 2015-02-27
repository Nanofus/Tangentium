package fi.nano.tangential.gameLogic;

/**
 * Apuluokka sijaintitiedon käsittelyyn. Yksinkertaisuuden nimissä x- ja
 * y-parametreja on tarkoitus muuttaa suoraan, ilman setteriä.
 *
 * @author Nanofus
 */
public class Position {

    /**
     * X-koordinaatti
     */
    public int x;
    /**
     * Y-koordinaatti
     */
    public int y;

    /**
     * Luo sijainnin annetuilla koordinaateilla
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Vertaa kahta sijaintia ja kertoo ovatko ne samat.
     *
     * @param other Toinen sijainti
     * @return Onko sama sijainti
     */
    public boolean is(Position other) {
        if (other.x == x && other.y == y) {
            return true;
        } else {
            return false;
        }
    }

}
