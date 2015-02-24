package fi.nano.tangential;

import fi.nano.tangential.gameLogic.*;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.enums.Direction;
import fi.nano.tangential.ui.Window;
import java.util.ArrayList;

/**
 * Luokka pitää sisällään varsinaisen pelin luonnin ja toiminnan.
 */
public class Game {

    private Level level;
    private Window window;

    private int currentLevel = 0;
    private ArrayList<String> levels;

    private boolean gameWon = false;

    int turn = 0;

    /**
     * Konstruktori lataa Level-tyyppisen tason tekstitiedostosta.
     *
     * @param levelName Ladattavan tekstitiedoston nimi ilman tiedostopäätettä
     * (.txt)
     */
    public Game(ArrayList<String> levelName) {
        levels = levelName;

        System.out.println("\nGenerating game from file...");

        level = new Level(this, levels.get(currentLevel));

        /*System.out.println("\nGenerated level:\n----\n");

         System.out.println(level);

         System.out.println("\n----");*/
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
            window.RestartLevel(level);
        }
    }

    /**
     * Siirtää pelaajan seuraavalle tasolle tai voittaa pelin jos viimeinen taso
     *
     */
    public void AdvanceLevel() {
        currentLevel++;
        if (currentLevel < levels.size()) {
            String nextLevel = levels.get(currentLevel);
            level = new Level(this, nextLevel);
            window.RestartLevel(level);
        } else {
            System.out.println("GAME WON");
            level.SetGameOver(true);
            gameWon = true;
            window.WinGame();
        }
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
                PassTurn();
            } else {
                if (level.GetPlayer().GetStun() > 0) {
                    level.GetPlayer().AddStun(-1);
                    PassTurn();
                }
            }

        } else {
            RestartGame();
        }
    }

    /**
     * Pelaaja yrittää poimia esineen.
     */
    public void PlayerUse() {
        if (!level.IsGameOver()) {
            level.GetPlayer().EquipItemInTile();
            level.GetPlayer().UseActionTile();
            //PassTurn();                           //Tasapainosyistä vuoroa ei skipata aseen poiminnassa, koska muuten voi vain odottaa viereen tulevia vihuja
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
