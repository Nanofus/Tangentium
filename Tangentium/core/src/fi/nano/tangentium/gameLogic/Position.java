package fi.nano.tangentium.gameLogic;

/**
 * Class used for location information.
 *
 * @author Nanofus
 */
public class Position {

    /**
     * X-coordinate
     */
    public int x;
    /**
     * Y-coordinate
     */
    public int y;

    /**
     * Creates a Position from given coordinates.
     * @param x X-coordinate
     * @param y Y-coordinate
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
     * Compares two positions to see if they are the same
     *
     * @param other Position B
     * @return Is the position same?
     */
    public boolean is(Position other) {
        if (other.x == x && other.y == y) {
            return true;
        } else {
            return false;
        }
    }

}
