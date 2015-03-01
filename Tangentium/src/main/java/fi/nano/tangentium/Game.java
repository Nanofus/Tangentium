package fi.nano.tangentium;

import fi.nano.tangentium.gameLogic.Level;
import fi.nano.tangentium.gameLogic.entities.Actor;
import fi.nano.tangentium.gameLogic.enums.Direction;
import fi.nano.tangentium.ui.ErrorDialog;
import fi.nano.tangentium.ui.Window;
import java.util.ArrayList;

/**
 * Game generation and functionality
 */
public class Game {

    private Level level;
    private Window window;

    private int currentLevel = 0;
    private final ArrayList<String> levels;

    private boolean gameWon = false;

    int turn = 0;

    /**
     * Loads a Level from a file
     *
     * @param levelNames List of levels
     */
    public Game(ArrayList<String> levelNames) {
        levels = levelNames;

        System.out.println("\nGenerating game from file...");

        level = new Level(this, levels.get(currentLevel));
    }

    public Level GetLevel() {
        return level;
    }

    public int GetLevelID() {
        return currentLevel;
    }

    public void SetWindow(Window window) {
        this.window = window;
    }

    private void RestartGame() {
        if (!gameWon) {
            level = new Level(this, levels.get(currentLevel));
            window.NewLevel(level);
        }
    }

    /**
     * Makes the player advance to the next level - or win the game, if in the
     * last level
     */
    public void AdvanceLevel() {
        currentLevel++;
        if (currentLevel < levels.size()) {
            String nextLevel = levels.get(currentLevel);
            level = new Level(this, nextLevel);
            window.NewLevel(level);
        } else {
            System.out.println("GAME WON");
            level.SetGameOver(true);
            gameWon = true;
            window.WinGame();
            new ErrorDialog().ShowError("You won the game!\n\nThe game will now close. Thanks for playing!");
            System.exit(1);
        }
    }

    /**
     * Player movement
     *
     * @param dir Direction
     */
    public void MovePlayer(Direction dir) {
        if (!level.IsGameOver()) {
            int x = 0;
            int y = 0;

            switch (dir) {
                case LEFT:
                    x = -1;
                    break;
                case RIGHT:
                    x = 1;
                    break;
                case UP:
                    y = -1;
                    break;
                case DOWN:
                    y = 1;
                    break;
            }

            boolean actionDone = level.GetPlayer().Move(x, y);
            if (actionDone) {
                PassTurn();
            }
        } else {
            RestartGame();
        }
    }

    /**
     * Player tries to pick up an item
     */
    public void PlayerUse() {
        if (!level.IsGameOver()) {
            level.GetPlayer().UseItemInTile();
            level.GetPlayer().UseActionTile();
        } else {
            RestartGame();
        }
    }

    /**
     * Player rotates the camera
     *
     * @param dir Direction
     */
    public void RotateCamera(Direction dir) {
        window.RotateCamera(dir);
    }

    private void PassTurn() {
        if (!level.IsGameOver()) {
            AIMove();
            if (window != null) {
                window.Refresh();
            }
            turn++;
        }
    }

    private void AIMove() {
        if (!level.IsGameOver()) {
            for (Actor enemy : level.GetActors()) {
                if (enemy.HasAI()) {
                    enemy.GetAI().MakeMove();
                }
            }
        }
    }

}
