package fi.nano.tangential.gameLogic;

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
public class LevelTest {

    public LevelTest() {
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
    public void TestLevelRandomGenerationLevelWidth() {
        Level level = new Level(100, 200, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelWidth = levelRows[1].length();

        assertEquals(200, levelWidth);
    }

    @Test
    public void TestLevelRandomGenerationLevelHeight() {
        Level level = new Level(100, 200, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelHeight = levelRows.length;

        assertEquals(100, levelHeight);
    }

    @Test
    public void TestGameRandomGenerationLevelWidthNegativeValue() {
        Level level = new Level(-100, 200, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelWidth = levelRows.length;

        assertEquals(1, levelWidth);
    }

    @Test
    public void TestGameRandomGenerationLevelWidthZeroValue() {
        Level level = new Level(0, 200, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelWidth = levelRows.length;

        assertEquals(1, levelWidth);
    }

    @Test
    public void TestGameRandomGenerationLevelHeightZeroValue() {
        Level level = new Level(100, 0, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelHeight = levelRows[0].length();

        assertEquals(1, levelHeight);
    }

    @Test
    public void TestGameRandomGenerationLevelHeightNegativeValue() {
        Level level = new Level(100, -200, 20, 20);

        String levelString = level.toString();

        String[] levelRows = levelString.split("\n");

        int levelHeight = levelRows[0].length();

        assertEquals(1, levelHeight);
    }

}
