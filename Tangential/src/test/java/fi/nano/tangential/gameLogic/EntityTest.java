package fi.nano.tangential.gameLogic;

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
//Testiluokka Entity-luokan ja sitä extendaavien luokkien testaamiseksi. Move()-metodin testaaminen voi tuntua sen yksinkertaisuuden vuoksi hieman turhalta, mutta metodi
//voi monimutkaistua tulevaisuudessa.
public class EntityTest {

    public EntityTest() {
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
    public void MoveWithPositiveValues() {
        Level level = new Level("test");
        Entity entity = new Entity(5, -1, "Testientity", level);

        entity.Move(5, 6);

        assertEquals("(10,5)", entity.GetPosition().toString());
    }

    @Test
    public void MoveWithZeroValues() {
        Level level = new Level("test");
        Entity entity = new Entity(5, 1, "Testientity", level);

        entity.Move(0, 0);

        assertEquals("(5,1)", entity.GetPosition().toString());
    }

    @Test
    public void MoveWithNegativeValues() {
        Level level = new Level("test");
        Entity entity = new Entity(5, 1, "Testientity", level);

        entity.Move(-1, -1);

        assertEquals("(5,1)", entity.GetPosition().toString());
    }
}
