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
    public Level(int width, int height, boolean spawnWalls) {

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
                    if (spawnWalls) {
                        if (random.nextBoolean() == true && random.nextBoolean() == true && random.nextBoolean() == true) {
                            tiles[i][j].SetType(TileType.WALL);
                        } else {
                            tiles[i][j].SetType(TileType.FLOOR);
                        }
                    } else {
                        tiles[i][j].SetType(TileType.FLOOR);
                    }
                }
            }
        }

        System.out.println();
        //SpawnItems(this.items);
        System.out.println();
        SpawnPlayer(1, 1);
        System.out.println();
        //SpawnEnemies(this.enemies);
        System.out.println();

    }

    /**
     * Konstruktori lataa tason tekstitiedostosta.
     *
     * @param levelName Ladattavan tekstitiedoston nimi ilman tiedostopäätettä
     * (.txt)
     *
     */
    public Level(String levelName) {

        Init();

        LevelReader levelReader = new LevelReader(levelName);

        ArrayList<String> levelArray = levelReader.GetLevel();
        ArrayList<String> actorArray = levelReader.GetActors();
        ArrayList<String> itemArray = levelReader.GetItems();

        height = levelArray.size();
        width = levelArray.get(2).length();

        tiles = new Tile[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile();
                Character tileChar = levelArray.get(j).charAt(i);
                Character actorChar = actorArray.get(j).charAt(i);
                Character itemChar = itemArray.get(j).charAt(i);

                switch (tileChar) {
                    case '.':
                        tiles[i][j].SetType(FLOOR);
                        break;
                    case 'X':
                        tiles[i][j].SetType(WALL);
                        break;
                    case ' ':
                        tiles[i][j].SetType(CHASM);
                        break;
                    case '~':
                        tiles[i][j].SetType(WATER);
                        break;
                    case '_':
                        tiles[i][j].SetType(ICE);
                        break;
                    case 'I':
                        tiles[i][j].SetType(PILLAR_WITH_FLOOR);
                        break;
                    case 'G':
                        tiles[i][j].SetType(PILLAR_WITH_GRASS);
                        break;
                    case ',':
                        tiles[i][j].SetType(GRASS);
                        break;
                    case ';':
                        tiles[i][j].SetType(PATH);
                        break;
                    case 'T':
                        tiles[i][j].SetType(TREE);
                        break;
                    default:
                        tiles[i][j].SetType(CHASM);
                        break;
                }

                switch (actorChar) {
                    case '@':
                        SpawnPlayer(i, j);
                        break;
                    case 'S':
                        SpawnEnemy(i, j, "Skeleton");
                        break;
                    case 'T':
                        SpawnEnemy(i, j, "Troll");
                        break;
                    case 'L':
                        SpawnEnemy(i, j, "Lizard Man");
                        break;
                    case 'D':
                        SpawnEnemy(i, j, "Dragon");
                        break;
                }

                switch (itemChar) {
                    case 's':
                        SpawnItem(i, j, "Sword", 1);
                        break;
                    case 'r':
                        SpawnItem(i, j, "Spear", 1);
                        break;
                    case 'm':
                        SpawnItem(i, j, "Mace", 1);
                        break;
                    case 'p':
                        SpawnItem(i, j, "Pyrospell", 1);
                        break;
                    case 'i':
                        SpawnItem(i, j, "Ice Staff", 1);
                        break;
                    case 'w':
                        SpawnItem(i, j, "Wand", 1);
                        break;
                }
            }
        }

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

    /**
     * Hakee Actorin tietystä tilestä.
     *
     * @param x
     * @param y
     * @return Tilessä seisova actor
     */
    public Actor GetActorInTile(int x, int y) {
        Actor chosen = null;

        for (Actor actor : entityManager.GetEnemies()) {
            if (actor.GetPosition().x == x && actor.GetPosition().y == y) {
                chosen = actor;
            }
        }

        if (player.GetPosition().x == x && player.GetPosition().y == y) {
            chosen = player;
        }

        return chosen;
    }
    
    /**
     * Hakee Actorin tietystä tilestä.
     *
     * @param pos Sijainti kentällä
     * @return Tilessä seisova actor
     */
    public Actor GetActorInTile(Position pos) {
        return GetActorInTile(pos.x,pos.y);
    }

    /**
     * Hakee esineen tietystä tilestä.
     *
     * @param x
     * @param y
     * @return Tilessä oleva esine
     */
    public Item GetItemInTile(int x, int y) {
        Item chosen = null;

        for (Item item : entityManager.GetItems()) {
            if (item.GetPosition().x == x && item.GetPosition().y == y) {
                if (!item.IsEquipped()) {
                    chosen = item;
                }
            }
        }

        return chosen;
    }
    
    /**
     * Hakee Itemin tietystä tilestä.
     * 
     * @param pos Sijainti kentällä
     * @return 
     */
    public Item GetItemInTile(Position pos) {
        return GetItemInTile(pos.x,pos.y);
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

    public EntityManager GetEntityManager() {
        return entityManager;
    }

    private void SpawnPlayer(int x, int y) {
        player = new Actor(x, y, "Player", this, combatHandler, 5, 5, true, 0, 0, 0, 0, 0, 0);
    }

    private void SpawnItem(int x, int y, String name, int power) {
        DamageType type;
        switch (name) {
            case "Sword":
                type = DamageType.Slash;
                break;
            case "Spear":
                type = DamageType.Pierce;
                break;
            case "Mace":
                type = DamageType.Crush;
                break;
            case "Pyrospell":
                type = DamageType.Burn;
                break;
            case "Ice Staff":
                type = DamageType.Freeze;
                break;
            case "Wand":
                type = DamageType.Arcane;
                break;

            default:
                type = DamageType.Slash;
                break;
        }

        Item item = new Item(x, y, name, this, power, type);
        entityManager.AddItem(item);
    }

    private void SpawnEnemy(int x, int y, String name) {

        Actor enemy = null;

        switch (name) {
            case "Skeleton":
                enemy = new Actor(x, y, "Skeleton", this, combatHandler, 1, 1, false, 0, 2, -2, -1, 1, -2);
                break;
            case "Troll":
                enemy = new Actor(x, y, "Troll", this, combatHandler, 4, 4, false, 2, 0, 1, -2, 0, -1);
                break;
            case "Lizard Man":
                enemy = new Actor(x, y, "Lizard Man", this, combatHandler, 2, 2, false, -2, -2, 0, 1, 2, 1);
                break;
            case "Dragon":
                enemy = new Actor(x, y, "Dragon", this, combatHandler, 10, 10, false, 0, 0, 0, 2, 2, 2);
                break;
            default:
                enemy = new Actor(x, y, "UNDEFINED", this, combatHandler, 1, 1, false, 0, 0, 0, 0, 0, 0);
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
                            if (!item.IsEquipped()) {
                                sb.append(item.GetSymbol());
                                charChosen = true;
                            }
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
