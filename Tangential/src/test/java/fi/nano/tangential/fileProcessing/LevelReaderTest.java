package fi.nano.tangential.fileProcessing;



import fi.nano.tangential.gameLogic.Level;
import java.util.ArrayList;
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

public class LevelReaderTest {

    public LevelReaderTest() {
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
    public void TestLevelReading() {
        LevelReader levelReader = new LevelReader(null,"testlevel");
        ArrayList<String> level = levelReader.GetLevel();
        
        assertEquals("~~~~~~~~",level.get(5));
    }

}
