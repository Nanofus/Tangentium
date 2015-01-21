
package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Tile;
import java.util.Random;

/**
 *
 * @author Nanofus
 */
public class Level {
    private Tile[][] tiles;
    private int width;
    private int height;
    
    public Level(int width, int height) {
        
        this.width = width;
        this.height = height;
        
        tiles = new Tile[width][height];
        
        //Random level generation, will use a better algorithm / read the level from a file eventually
        Random random = new Random();
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile();
                tiles[i][j]=tile;
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (random.nextBoolean()==true && random.nextBoolean()==true && random.nextBoolean()==true) {
                    tiles[i][j].SetTile(TileType.WALL);
                }
            }
        }
        
    }
    
    public Tile GetTile(int x, int y) {
        return tiles[x][y];
    }
    
    public int GetWidth() {
        return width;
    }
    
    public int GetHeight() {
        return height;
    }
    
}
