package fi.nano.tangential;

import fi.nano.tangential.gameLogic.*;
import java.io.FileNotFoundException;

public class Game {

    private Level level;

    public Game(String levelName) {
        System.out.println("\nGenerating game from file...");
        
        //SIIRRÄ LUKUVASTUU OMALLE LUOKALLEEN
        
        boolean successfullyLoaded = ReadLevel(levelName);
        
        if (successfullyLoaded) {
            System.out.println("\nGenerated level:\n----\n");

            System.out.println(level);

            System.out.println("\n----");

            System.out.println("\nStarting game.");
        } else {
            System.out.println("Level could not be loaded! Exiting...");
            
            System.exit(1);
        }
    }
    
    public Game(int width, int height, int enemies, int items) {
        System.out.println("\nGenerating game randomly...");
        
        GenerateLevel(width, height, enemies, items);
        
        System.out.println("\nGenerated level:\n----\n");
        
        System.out.println(level);
        
        System.out.println("\n----");

        System.out.println("\nStarting game.");
    }
    
    private boolean ReadLevel(String levelName) {
        try {
            level = new Level(levelName);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private void GenerateLevel(int width, int height, int enemies, int items) {
        level = new Level(width, height, enemies, items);
    }
    
    public Level GetLevel() {
        return level;
    }
}
