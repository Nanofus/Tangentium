/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic;

import fi.nano.tangential.Game;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.enums.Direction;
import static fi.nano.tangential.gameLogic.enums.Stance.*;
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
    public void TestStanceChangeFromWanderToChase() {
        ArrayList<String> levels = new ArrayList<String>();
        levels.add("level1");
        Game game = new Game(null,levels);
        
        Actor enemy = new Actor(3, 3, "Skeleton", game.GetLevel(), 1, 1, false, 0, 2, -2, -1, 1, -2);
        
        game.GetLevel().GetEntityManager().AddEnemy(enemy);
        
        game.MovePlayer(Direction.UP);
        
        assertEquals(Chase,enemy.GetAI().GetStance());
    }
    
    @Test
    public void TestStanceChangeFromChaseToWander() {
        ArrayList<String> levels = new ArrayList<String>();
        levels.add("level1");
        Game game = new Game(null,levels);
        
        Actor enemy = new Actor(5, 5, "Skeleton", game.GetLevel(), 1, 1, false, 0, 2, -2, -1, 1, -2);
        
        game.GetLevel().GetEntityManager().AddEnemy(enemy);
        
        game.MovePlayer(Direction.UP);
        
        enemy.Move(20, 0);
        
        game.MovePlayer(Direction.UP);
        
        assertEquals(Wander,enemy.GetAI().GetStance());
    }
    
    @Test
    public void MoveAIRandomly() {
        ArrayList<String> levels = new ArrayList<String>();
        levels.add("level1");
        Game game = new Game(null,levels);
        
        Actor enemy = new Actor(3, 3, "Skeleton", game.GetLevel(), 1, 1, false, 0, 2, -2, -1, 1, -2);
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
