
package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.singletons.CombatHandler;
import fi.nano.tangential.gameLogic.singletons.EntityManager;
import static fi.nano.tangential.gameLogic.enums.DamageType.*;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
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
public class CombatHandlerTest {
    
    public CombatHandlerTest() {
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
    public void TestAttackWithNonLethalDamage() {
        EntityManager entityManager = new EntityManager();
        CombatHandler handler = new CombatHandler(entityManager);
        
        Actor enemy1 = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        Actor enemy2 = new Actor(0, 1, "Troll", 3, false, 2, 0, 1, -2, 0, -1);
        
        Item weapon1 = new Item(0, 0, "Test Slash Weapon", 1, PIERCE);
        
        enemy1.EquipItem(weapon1);
        
        handler.Hit(enemy1, enemy2);
        
        assertEquals(2,enemy2.GetHealth());
    }
    
    @Test
    public void TestAttackWithLethalDamage() {
        EntityManager entityManager = new EntityManager();
        CombatHandler handler = new CombatHandler(entityManager);
        
        Actor enemy1 = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        Actor enemy2 = new Actor(0, 1, "Troll", 3, false, 2, 0, 1, -2, 0, -1);
        
        Item weapon1 = new Item(0, 0, "Test Slash Weapon", 20, PIERCE);
        
        enemy1.EquipItem(weapon1);
        
        handler.Hit(enemy1, enemy2);
        
        assertEquals(0,enemy2.GetHealth());
    }
    
    @Test
    public void TestResistancesWithNonLethalDamage() {
        EntityManager entityManager = new EntityManager();
        CombatHandler handler = new CombatHandler(entityManager);
        
        Actor enemy1 = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        Actor enemy2 = new Actor(0, 1, "Troll", 3, false, 2, 0, 1, -2, 0, -1);
        
        Item weapon1 = new Item(0, 0, "Test Slash Weapon", 1, ARCANE);
        
        enemy1.EquipItem(weapon1);
        
        handler.Hit(enemy1, enemy2);
        
        assertEquals(1,enemy2.GetHealth());
    }
    
    @Test
    public void TestResistancesWithLethalDamage() {
        EntityManager entityManager = new EntityManager();
        CombatHandler handler = new CombatHandler(entityManager);
        
        Actor enemy1 = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        Actor enemy2 = new Actor(0, 1, "Troll", 3, false, 2, 0, 1, -2, 0, -1);
        
        Item weapon1 = new Item(0, 0, "Test Slash Weapon", 5, SLASH);
        
        enemy1.EquipItem(weapon1);
        
        handler.Hit(enemy1, enemy2);
        
        assertEquals(0,enemy2.GetHealth());
    }
}
