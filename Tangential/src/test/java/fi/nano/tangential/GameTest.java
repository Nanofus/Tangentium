
package fi.nano.tangential;

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
public class GameTest {
    
    public GameTest() {
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
    public void TestGameRandomGenerationLevelWidth() {
        Game game = new Game(100,200,20,20);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelWidth = levelRows[1].length();
        
        assertEquals(200,levelWidth);
    }
    
    @Test
    public void TestGameRandomGenerationLevelHeight() {
        Game game = new Game(100,200,20,20);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelHeight = levelRows.length;
        
        assertEquals(100,levelHeight);
    }
    
    @Test
    public void TestGameRandomGenerationLevelWidthNegativeValue() {
        Game game = new Game(-100,-200,20,20);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelWidth = levelRows[0].length();
        
        assertEquals(1,levelWidth);
    }
    
    @Test
    public void TestGameRandomGenerationLevelHeightZeroValue() {
        Game game = new Game(0,0,0,0);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelHeight = levelRows.length;
        
        assertEquals(1,levelHeight);
    }
    
    @Test
    public void TestGameRandomGenerationLevelWidthZeroValue() {
        Game game = new Game(0,0,0,0);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelWidth = levelRows[0].length();
        
        assertEquals(1,levelWidth);
    }
    
    @Test
    public void TestGameRandomGenerationLevelHeightNegativeValue() {
        Game game = new Game(-100,-200,20,20);
        
        Level level = game.GetLevel();
        String levelString = level.toString();
        
        String[] levelRows = levelString.split("\n");
        
        int levelHeight = levelRows.length;
        
        assertEquals(1,levelHeight);
    }
    
}
