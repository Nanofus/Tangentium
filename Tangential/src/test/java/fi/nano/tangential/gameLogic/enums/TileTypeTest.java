package fi.nano.tangential.gameLogic.enums;

import fi.nano.tangential.gameLogic.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nanofus
 */
// Testiluokka TileType-enumin is()-metodin testaamiseksi.
public class TileTypeTest {

    public TileTypeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void PassableIsPassable() {
        Level level = new Level(null,"level1");

        level.GetTile(0, 0).SetType(TileType.FLOOR);

        boolean isPassable = false;

        if (level.GetTile(0, 0).GetType().is(TileType.PASSABLE)) {
            isPassable = true;
        }

        assertEquals(true, isPassable);
    }

    @Test
    public void ImpassableIsImpassable() {
        Level level = new Level(null,"level1");

        level.GetTile(0, 0).SetType(TileType.WALL);

        boolean isImpassable = false;

        if (level.GetTile(0, 0).GetType().is(TileType.IMPASSABLE)) {
            isImpassable = true;
        }

        assertEquals(true, isImpassable);
    }
}
