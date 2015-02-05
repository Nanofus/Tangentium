package fi.nano.tangential;

import fi.nano.tangential.gameLogic.*;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.enums.Direction;
import java.io.FileNotFoundException;

/**
 * Luokka pitää sisällään varsinaisen pelin luonnin ja toiminnan.
 */
public class Game {

    private Level level;

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

        level = new Level(levelName);

        System.out.println("\nGenerated level:\n----\n");

        System.out.println(level);

        System.out.println("\n----");

    }

    /**
     * Konstruktori luo Level-tyyppisen tason parametrien perusteella.
     *
     * @param width Tason leveys
     * @param height Tason korkeus
     * @param enemies Vihollisten määrä
     * @param items Esineiden määrä
     */
    public Game(int width, int height, int enemies, int items) {
        System.out.println("\nGenerating game randomly...");

        level = GenerateLevel(width, height, enemies, items);

        System.out.println("\nGenerated level:\n----\n");

        System.out.println(level);

        System.out.println("\n----");

    }

    private Level GenerateLevel(int width, int height, int enemies, int items) {
        return new Level(width, height, enemies, items);
    }

    public Level GetLevel() {
        return level;
    }

    /**
     * Käynnistää pelilogiikan.
     */
    public void RunGame() {

        while (gameRunning) {
            //System.out.println("New turn!");

            //Player's turn
            //System.out.println("Player's turn");
            while (isPlayersTurn) {

            }

            //AI turn
            //System.out.println("AI's turn");
            for (Actor enemy : level.GetActors()) {
                if (enemy.HasAI()) {
                    enemy.GetAI().MakeMove();
                }
            }

            turn++;

            //temp solution
            /*if (turn > 200) {
             gameRunning = false;
             }*/
        }
    }

    /**
     * Pelaajan liikkuminen inputista.
     */
    public void MovePlayer(Direction dir) {

    }

    /**
     * Pelaaja yrittää poimia esineen.
     */
    public void PickItem() {
        for (Item item : level.GetItems()) {
            if (item.GetPosition() == level.GetPlayer().GetPosition() && !item.IsEquipped()) {
                level.GetPlayer().EquipItem(item);
                System.out.println("Player picked up item "+item.GetName());
            }
        }
    }

}
