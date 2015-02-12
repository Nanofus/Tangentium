package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import static fi.nano.tangential.gameLogic.enums.DamageType.*;
import static fi.nano.tangential.gameLogic.enums.TileType.*;
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

    /*@Test
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
     }*/
    
    @Test
    public void LoadLevelFromFile() {
        Level level = new Level("level1");

        assertEquals(FLOOR,level.GetTile(5,5).GetType());
        
    }
    
    @Test
    public void GetEnemyActorInTile() {
        Level level = new Level("level1");
        
        Actor enemy = new Actor(2, 2, "Skeleton", null, 1, false, 0, 2, -2, -1, 1, -2);
        level.GetEntityManager().AddEnemy(enemy);
        
        assertEquals(enemy,level.GetActorInTile(2, 2));
    }
    
    @Test
    public void GetActorInTileFalse() {
        Level level = new Level("level1");
        
        Actor enemy = new Actor(2, 2, "Skeleton", null, 1, false, 0, 2, -2, -1, 1, -2);
        level.GetEntityManager().AddEnemy(enemy);
        
        assertEquals(null,level.GetActorInTile(3, 3));
    }
    
    @Test
    public void GetPlayerActorInTile() {
        Level level = new Level("level1");
        
        assertEquals(level.GetPlayer(),level.GetActorInTile(1, 1));
    }
    
    @Test
    public void GetItemInTile() {
        Level level = new Level("level1");
        
        Item item = new Item(2, 2, "TestSword", null, 1, SLASH);
        level.GetEntityManager().AddItem(item);
        
        assertEquals(item,level.GetItemInTile(2, 2));
    }

    @Test
    public void TestLevelPrint() {
        Level level = new Level("level1");
        
        String levelAsString = level.toString();
        
        assertEquals('X',levelAsString.charAt(5));
    }
    
}
