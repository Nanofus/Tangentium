package fi.nano.tangential;

import fi.nano.tangential.gameLogic.*;
import fi.nano.tangential.gameLogic.entities.Actor;
import java.io.FileNotFoundException;

public class Game {

    private Level level;

    int turn = 0;

    boolean gameRunning = true;

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

        RunGame();
    }

    public Game(int width, int height, int enemies, int items) {
        System.out.println("\nGenerating game randomly...");

        GenerateLevel(width, height, enemies, items);

        System.out.println("\nGenerated level:\n----\n");

        System.out.println(level);

        System.out.println("\n----");

        System.out.println("\nStarting game.");

        RunGame();
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

    private void RunGame() {
        
        while (gameRunning) {
            System.out.println("New turn!");

            //Player's turn
            System.out.println("Player's turn");

            //AI turn
            System.out.println("AI's turn");
            for (Actor enemy : level.GetActors()) {
                if (enemy.HasAI()) {
                    enemy.GetAI().MakeMove();
                }
            }
            
            turn++;
            
            //temp solution
            if (turn>200) {
                gameRunning = false;
            }
        }
    }

}
