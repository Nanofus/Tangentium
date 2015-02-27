/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import static fi.nano.tangential.gameLogic.enums.DamageType.Pierce;
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
public class EntityManagerTest {

    public EntityManagerTest() {
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
    public void TestItemAddition() {
        EntityManager entityManager = new EntityManager();

        Item item = new Item(1, 1, "TestItem", null, 5, Pierce);

        entityManager.AddItem(item);

        assertEquals(1, entityManager.GetItems().size());
    }

    @Test
    public void TestActorAddition() {
        EntityManager entityManager = new EntityManager();

        Actor enemy = new Actor(1, 1, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        entityManager.AddEnemy(enemy);

        assertEquals(1, entityManager.GetEnemies().size());
    }

    @Test
    public void TestItemRemoval() {
        EntityManager entityManager = new EntityManager();

        Item item = new Item(1, 1, "TestItem", null, 5, Pierce);

        entityManager.AddItem(item);
        entityManager.DestroyItem(item);

        assertEquals(0, entityManager.GetItems().size());
    }

    @Test
    public void TestActorRemoval() {
        EntityManager entityManager = new EntityManager();

        Actor enemy = new Actor(1, 1, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        entityManager.AddEnemy(enemy);
        entityManager.DestroyActor(enemy);

        assertEquals(0, entityManager.GetEnemies().size());
    }

}
