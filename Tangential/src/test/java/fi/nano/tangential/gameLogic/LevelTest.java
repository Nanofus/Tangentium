package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.enums.DamageType;
import static fi.nano.tangential.gameLogic.enums.DamageType.*;
import static fi.nano.tangential.gameLogic.enums.TileAction.*;
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

    @Test
    public void LoadLevelFromFile() {
        Level level = new Level(null, "level1");

        assertEquals(FLOOR, level.GetTile(5, 5).GetType());

    }

    @Test
    public void GetEnemyActorInTile() {
        Level level = new Level(null, "level1");

        Actor enemy = new Actor(2, 2, "Skeleton", level, 1, 1, false, 0, 2, -2, -1, 1, -2);
        level.GetEntityManager().AddEnemy(enemy);

        assertEquals(enemy, level.GetActorInTile(2, 2));
    }

    @Test
    public void GetActorInTileFalse() {
        Level level = new Level(null, "level1");

        Actor enemy = new Actor(2, 2, "Skeleton", level, 1, 1, false, 0, 2, -2, -1, 1, -2);
        level.GetEntityManager().AddEnemy(enemy);

        assertEquals(null, level.GetActorInTile(3, 3));
    }

    @Test
    public void GetPlayerActorInTile() {
        Level level = new Level(null, "level1");

        assertEquals(level.GetPlayer(), level.GetActorInTile(1, 1));
    }

    @Test
    public void GetItemInTile() {
        Level level = new Level(null, "level1");

        Item item = new Item(2, 2, "TestSword", null, 1, DamageType.Slash);
        level.GetEntityManager().AddItem(item);

        assertEquals(item, level.GetItemInTile(2, 2));
    }

    @Test
    public void TestLevelPrint() {
        Level level = new Level(null, "level1");

        String levelAsString = level.toString();

        assertEquals('X', levelAsString.charAt(5));
    }

    @Test
    public void TestLeverActivation() {
        Level level = new Level(null, "level1");
        level.GetTile(20, 20).SetType(LEVER);
        level.GetTile(20, 20).SetTileAction(Changer);
        level.GetTile(20, 20).SetTileActionId(1);

        level.GetTile(21, 20).SetType(DOOR_LEFT);
        level.GetTile(21, 20).SetTileAction(Changing);
        level.GetTile(21, 20).SetTileActionId(1);

        level.ActivateTile(new Position(20, 20));

        assertEquals(FLOOR, level.GetTile(21, 20).GetType());
    }

    @Test
    public void TestGameOverAfterPlayerDying() {
        Level level = new Level(null, "level1");
        level.GetPlayer().LoseHealth(20);

        assertEquals(true, level.IsGameOver());
    }

    @Test
    public void TestGameOverAfterSettingManually() {
        Level level = new Level(null, "level1");
        level.SetGameOver(true);

        assertEquals(true, level.IsGameOver());
    }
    
    @Test
    public void TestDistanceDirect() {
        Level level = new Level(null, "level1");

        assertEquals(5,level.GetDistance(new Position(0,0), new Position(0,5)));
    }
    
    @Test
    public void TestDistanceDiagonal() {
        Level level = new Level(null, "level1");

        assertEquals(5,level.GetDistance(new Position(0,0), new Position(3,3)));
    }
}
