package fi.nano.tangential;

import fi.nano.tangential.gameLogic.*;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.enums.Direction;
import fi.nano.tangential.ui.Window;

/**
 * Luokka pitää sisällään varsinaisen pelin luonnin ja toiminnan.
 */
public class Game {

    private Level level;
    private String levelName;
    private Window window;

    int turn = 0;

    boolean gameRunning = true;
    boolean isPlayersTurn = false;

    /**
     * Konstruktori lataa Level-tyyppisen tason tekstitiedostosta.
     *
     * @param levelName Ladattavan tekstitiedoston nimi ilman tiedostopäätettä
     * (.txt)
     */
    public Game(String levelName) {
        System.out.println("\nGenerating game from file...");

        this.levelName = levelName;
        level = new Level(levelName);

        /*System.out.println("\nGenerated level:\n----\n");

         System.out.println(level);

         System.out.println("\n----");*/
    }

    public Level GetLevel() {
        return level;
    }

    public void SetWindow(Window window) {
        this.window = window;
    }

    private void RestartGame() {
        level = new Level(levelName);
        window.RestartLevel(level);
    }

    /**
     * Pelaajan liikkuminen inputista.
     *
     * @param dir Suunta johon liikutaan.
     */
    public void MovePlayer(Direction dir) {
        if (!level.IsGameOver()) {
            if (level.GetPlayer().GetStun() == 0) {
                int x = 0;
                int y = 0;

                boolean canMove = false;

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

                level.GetPlayer().Move(x, y);
            } else {
                level.GetPlayer().AddStun(-1);
            }

            PassTurn();
        } else {
            RestartGame();
        }
    }

    /**
     * Pelaaja yrittää poimia esineen.
     */
    public void PlayerPickItem() {
        if (!level.IsGameOver()) {
            for (Item item : level.GetItems()) {
                if (item.GetPosition().is(level.GetPlayer().GetPosition()) && !item.IsEquipped()) {
                    level.GetPlayer().EquipItem(item);
                    System.out.println("Player picked up item " + item.GetName());
                    PassTurn();
                    break;
                }
            }

            PassTurn();
        } else {
            RestartGame();
        }
    }

    /**
     * Pelaaja kääntää kameraa.
     *
     * @param direction Suunta.
     */
    public void RotateCamera(Direction direction) {
        window.RotateCamera(direction);
    }

    private void PassTurn() {
        if (!level.IsGameOver()) {
            AIMove();
            window.Refresh();
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
