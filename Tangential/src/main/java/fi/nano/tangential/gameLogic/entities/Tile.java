/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.gameLogic.entities;

/**
 *
 * @author Nanofus
 */
public class Tile {
    private boolean wall = false;
    
    public Tile() {
        
    }
    
    public boolean isWall() {
        return wall;
    }
    
    public void setWall() {
        wall = true;
    }
    
    public void removeWall() {
        wall = false;
    }
    
}
