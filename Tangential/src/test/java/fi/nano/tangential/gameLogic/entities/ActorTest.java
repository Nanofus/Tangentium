/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic.entities;

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
        Actor enemy = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        
        enemy.LoseHealth(1);
        
        assertEquals(0,enemy.GetHealth());
    }
    
    @Test
    public void TestHealthGain() {
        Actor enemy = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        
        enemy.LoseHealth(-1);
        
        assertEquals(2,enemy.GetHealth());
    }
    
    @Test
    public void TestHealthNoChange() {
        Actor enemy = new Actor(0, 0, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2);
        
        enemy.LoseHealth(0);
        
        assertEquals(1,enemy.GetHealth());
    }
}
