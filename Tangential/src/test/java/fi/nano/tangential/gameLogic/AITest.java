/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;
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
public class AITest {

    public AITest() {
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
    public void MoveAIRandomly() {
        Level level = new Level("level1");
        
        Actor enemy = new Actor(3, 3, "Skeleton", level, 1, false, 0, 2, -2, -1, 1, -2);
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.AddStun(2);
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        enemy.GetAI().MakeMove();
        assertEquals(true,enemy.HasAI());
    }
}
