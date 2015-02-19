/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.Position;
import fi.nano.tangential.gameLogic.entities.Actor;
import static fi.nano.tangential.gameLogic.enums.DamageType.*;
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
public class ActorTest {

    public ActorTest() {
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
    public void TestHealthLoss() {
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 5, false, 0, 2, -2, -1, 1, -2);

        enemy.LoseHealth(1);

        assertEquals(0, enemy.GetHealth());
    }

    @Test
    public void TestHealthGain() {
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 5, false, 0, 2, -2, -1, 1, -2);

        enemy.LoseHealth(-1);

        assertEquals(2, enemy.GetHealth());
    }

    @Test
    public void TestHealthNoChange() {
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 5, false, 0, 2, -2, -1, 1, -2);

        enemy.LoseHealth(0);

        assertEquals(1, enemy.GetHealth());
    }

    @Test
    public void EquipItemTest() {
        Item item = new Item(0, 0, "TestItem", null, 5, Pierce);
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        enemy.EquipItem(item);

        assertEquals(true, item.IsEquipped());
    }

    @Test
    public void EquipItemWhileStillHasPreviousItem() {
        Item item = new Item(0, 0, "TestItem", null, 5, Pierce);
        Item item2 = new Item(0, 0, "TestItem2", null, 4, Pierce);
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        enemy.EquipItem(item);
        enemy.EquipItem(item2);

        boolean correctItem = false;
        if (!item.IsEquipped() && item2.IsEquipped()) {
            correctItem = true;
        }

        assertEquals(true, correctItem);
    }

    @Test
    public void TestIfHasAITrue() {
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        assertEquals(true, enemy.HasAI());
    }

    @Test
    public void TestIfHasAIFalse() {
        Actor enemy = new Actor(0, 0, "Skeleton", null, 1, 1, true, 0, 2, -2, -1, 1, -2);

        assertEquals(false, enemy.HasAI());
    }

    @Test
    public void TestStunAddPositive() {
        Actor enemy = new Actor(4, 4, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        enemy.AddStun(5);

        enemy.GetAI().MakeMove();

        assertEquals(4, enemy.GetStun());
    }

    @Test
    public void TestMoveWhileStunned() {
        Actor enemy = new Actor(4, 4, "Skeleton", null, 1, 1, false, 0, 2, -2, -1, 1, -2);

        enemy.AddStun(5);

        enemy.Move(1, 0);

        Position expectedPosition = new Position(4, 4);
        boolean positionsMatch = false;
        if (expectedPosition.is(enemy.GetPosition())) {
            positionsMatch = true;
        }

        assertEquals(true, positionsMatch);
    }

    @Test
    public void TestMoveWhileNotStunned() {
        Level level = new Level(null, "level1");

        Actor enemy = new Actor(4, 4, "Skeleton", level, 1, 1, false, 0, 2, -2, -1, 1, -2);

        enemy.Move(1, 0);

        Position expectedPosition = new Position(5, 4);
        boolean positionsMatch = false;
        if (expectedPosition.is(enemy.GetPosition())) {
            positionsMatch = true;
        }

        assertEquals(true, positionsMatch);
    }
}
