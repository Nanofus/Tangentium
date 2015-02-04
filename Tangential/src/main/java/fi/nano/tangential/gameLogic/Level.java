package fi.nano.tangential.gameLogic;

import fi.nano.tangential.fileProcessing.LevelReader;
import fi.nano.tangential.gameLogic.enums.TileType;
import fi.nano.tangential.gameLogic.enums.DamageType;
import fi.nano.tangential.gameLogic.singletons.CombatHandler;
import fi.nano.tangential.gameLogic.singletons.EntityManager;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.entities.Tile;
import static fi.nano.tangential.gameLogic.enums.TileType.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Luokka, joka vastaa pelin tasosta ja tason sisällöstä.
 *
 * @author Nanofus
 */
public class Level {

    private EntityManager entityManager;
    private CombatHandler combatHandler;

    private Tile[][] tiles;
    private int width;
    private int height;

    private int enemies;
    private int items;

    private Actor player;

    Random random = new Random();

    /**
     * Konstruktori luo tason parametrien perusteella.
     *
     * @param width Tason leveys
     * @param height Tason korkeus
     * @param enemies Vihollisten määrä
     * @param items Esineiden määrä
     */
    public Level(int width, int height, int enemies, int items) {

        Init();

        this.width = width;
        this.height = height;

        this.enemies = enemies;
        this.items = items;

        ValidateParametres();

        tiles = new Tile[this.width][this.height];

        //Random level generation, will use a better algorithm in the future
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Tile tile = new Tile();
                tiles[i][j] = tile;
            }
        }

        if (GetHeight() > 1 && GetWidth() > 1) {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (random.nextBoolean() == true && random.nextBoolean() == true && random.nextBoolean() == true) {
                        tiles[i][j].SetType(TileType.WALL);
                    } else {
                        tiles[i][j].SetType(TileType.FLOOR);
                    }
                }
            }
        }

        System.out.println();
        SpawnItems(this.items);
        System.out.println();
        SpawnPlayer(1, 1);
        System.out.println();
        SpawnEnemies(this.enemies);
        System.out.println();

    }

    /**
     * Konstruktori lataa tason tekstitiedostosta.
     *
     * @param levelName Ladattavan tekstitiedoston nimi ilman tiedostopäätettä
     * (.txt)
     */
    public Level(String levelName) {

        Init();
        
        this.enemies = 10;
        this.items = 10;

        LevelReader levelReader = new LevelReader(levelName);
        ArrayList<String> levelArray = levelReader.GetLevel();
        
        height = levelArray.size();
        width = levelArray.get(0).length();
        
        tiles = new Tile[width][height];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile();
                switch (levelArray.get(j).charAt(i)) {
                    case '.':
                        tiles[i][j].SetType(FLOOR);
                    case 'X':
                        tiles[i][j].SetType(WALL);
                    default:
                        tiles[i][j].SetType(CHASM);
                }
            }
        }
        
        System.out.println(tiles[5][2]);
        
        System.out.println();
        SpawnItems(this.items);
        System.out.println();
        SpawnPlayer(1, 1);
        System.out.println();
        SpawnEnemies(this.enemies);
        System.out.println();
        

    }

    private void Init() {
        entityManager = new EntityManager();
        combatHandler = new CombatHandler(entityManager);
    }

    private void ValidateParametres() {
        if (width < 2) {
            width = 1;
        }
        if (height < 2) {
            height = 1;
        }

        if (enemies < 1) {
            enemies = 0;
        }
        if (items < 1) {
            items = 0;
        }
    }

    public Actor GetPlayer() {
        return player;
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

    public ArrayList<Actor> GetActors() {
        return entityManager.GetEnemies();
    }

    public ArrayList<Item> GetItems() {
        return entityManager.GetItems();
    }

    private void SpawnPlayer(int x, int y) {
        player = new Actor(x, y, "Player", 5, true, 0, 0, 0, 0, 0, 0, this);
    }

    private void SpawnItems(int items) {
        if (width > 1 && height > 1) {
            while (items > 0) {
                int x = random.nextInt(GetWidth());
                int y = random.nextInt(GetHeight());

                if (GetTile(x, y).GetType().is(TileType.PASSABLE)) {
                    SpawnItem(x, y);
                    items--;
                }
            }
        }
    }

    private void SpawnItem(int x, int y) {
        int power = 1;
        DamageType type = DamageType.SLASH;

        if (random.nextInt(8) == 7) {
            power = 2;
        }
        if (random.nextInt(19) == 18) {
            power = 3;
        }

        int itemType = random.nextInt(6);
        String itemName;
        switch (itemType) {
            case 0:
                itemName = "Sword";
                type = DamageType.SLASH;
                break;
            case 1:
                itemName = "Spear";
                type = DamageType.PIERCE;
                break;
            case 2:
                itemName = "Mace";
                type = DamageType.CRUSH;
                break;
            case 3:
                itemName = "Pyrospell";
                type = DamageType.BURN;
                break;
            case 4:
                itemName = "Ice Staff";
                type = DamageType.FREEZE;
                break;
            case 5:
                itemName = "Wand";
                type = DamageType.ARCANE;
                break;

            default:
                itemName = "UNDEFINED";
                type = DamageType.SLASH;
                break;
        }

        Item item = new Item(x, y, itemName, power, type);
        entityManager.AddItem(item);
    }

    private void SpawnEnemies(int enemies) {
        if (width > 1 && height > 1) {
            while (enemies > 0) {
                int x = random.nextInt(GetWidth());
                int y = random.nextInt(GetHeight());

                if (GetTile(x, y).GetType() != TileType.WALL) {
                    SpawnEnemy(x, y);
                    enemies--;
                }
            }
        }
    }

    private void SpawnEnemy(int x, int y) {
        int randomValue = random.nextInt(100);
        Actor enemy = new Actor(x, y, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2, this);

        if (randomValue < 40) {
            enemy = new Actor(x, y, "Skeleton", 1, false, 0, 2, -2, -1, 1, -2, this);
        } else if (randomValue < 70) {
            enemy = new Actor(x, y, "Troll", 1, false, 2, 0, 1, -2, 0, -1, this);
        } else if (randomValue < 95) {
            enemy = new Actor(x, y, "Lizard Man", 1, false, -2, -2, 0, 1, 2, 1, this);
        } else if (randomValue < 100) {
            enemy = new Actor(x, y, "Dragon", 1, false, 0, 0, 0, 2, 2, 2, this);
        }

        entityManager.AddEnemy(enemy);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < GetWidth(); i++) {
            for (int j = 0; j < GetHeight(); j++) {
                boolean charChosen = false;
                Position playerPos = player.GetPosition();
                if (playerPos.x == i && playerPos.y == j) {
                    sb.append(player.GetSymbol());
                    charChosen = true;
                }
                if (!charChosen) {
                    for (Actor enemy : entityManager.GetEnemies()) {
                        Position actorPos = enemy.GetPosition();
                        if (actorPos.x == i && actorPos.y == j) {
                            sb.append(enemy.GetSymbol());
                            charChosen = true;
                        }
                    }
                }
                if (!charChosen) {
                    for (Item item : entityManager.GetItems()) {
                        Position itemPos = item.GetPosition();
                        if (itemPos.x == i && itemPos.y == j) {
                            sb.append(item.GetSymbol());
                            charChosen = true;
                        }
                    }
                }
                if (!charChosen) {
                    sb.append(GetTile(i, j).GetSymbol());
                    charChosen = true;
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
