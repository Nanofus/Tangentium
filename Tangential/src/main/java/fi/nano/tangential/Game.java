package fi.nano.tangential;

import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.Position;
import fi.nano.tangential.gameLogic.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {

    private Level level;

    public Game(String levelName) {
        System.out.println("\nGenerating game from file...");
        
        ReadLevel(levelName);
        
        System.out.println("\nGenerated level:\n----\n");
        
        System.out.println(level);
        
        System.out.println("\n----");

        System.out.println("\nStarting game.");
    }
    
    public Game(int width, int height, int enemies, int items) {
        System.out.println("\nGenerating game randomly...");
        
        GenerateLevel(width, height, enemies, items);
        
        System.out.println("\nGenerated level:\n----\n");
        
        System.out.println(level);
        
        System.out.println("\n----");

        System.out.println("\nStarting game.");
    }
    
    private void ReadLevel(String levelName) {
        try {
            level = new Level(levelName);
        } catch (FileNotFoundException e) {
            System.out.println("Level could not be loaded! Exiting...");
            System.exit(1);
        }
    }

    private void GenerateLevel(int width, int height, int enemies, int items) {
        level = new Level(width, height, enemies, items);
    }
}
