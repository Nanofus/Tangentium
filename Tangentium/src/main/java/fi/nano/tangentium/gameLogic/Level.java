package fi.nano.tangentium.gameLogic;

import fi.nano.tangentium.Game;
import fi.nano.tangentium.fileProcessing.LevelReader;
import fi.nano.tangentium.gameLogic.enums.TileType;
import fi.nano.tangentium.gameLogic.enums.DamageType;
import fi.nano.tangentium.gameLogic.singletons.CombatHandler;
import fi.nano.tangentium.gameLogic.singletons.EntityManager;
import fi.nano.tangentium.gameLogic.entities.Actor;
import fi.nano.tangentium.gameLogic.entities.Item;
import fi.nano.tangentium.gameLogic.entities.Tile;
import static fi.nano.tangentium.gameLogic.enums.DamageType.*;
import fi.nano.tangentium.gameLogic.enums.Direction;
import static fi.nano.tangentium.gameLogic.enums.Direction.*;
import fi.nano.tangentium.gameLogic.enums.TileAction;
import static fi.nano.tangentium.gameLogic.enums.TileType.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that contains the level's information
 *
 * @author Nanofus
 */
public class Level {

    private final Game game;

    private EntityManager entityManager;
    private CombatHandler combatHandler;

    private final Tile[][] tiles;
    private int width;
    private int height;

    private int enemies;
    private int items;

    private Actor player;
    private Actor[] enemyActors;

    Random random = new Random();

    private boolean gameOver = false;

    /**
     * @param game Game instance that created the level
     * @param levelName Name of the level
     *
     */
    public Level(Game game, String levelName) {
        this.game = game;

        Init();

        LevelReader levelReader = new LevelReader(levelName);

        ArrayList<String> levelArray = levelReader.GetLevel();

        height = levelArray.size();
        width = levelArray.get(0).length();

        tiles = new Tile[width][height];

        SpawnObjects(levelReader);

        System.out.println("--- Game ready ---");
    }

    private void SpawnObjects(LevelReader levelReader) {
        ArrayList<String> levelArray = levelReader.GetLevel();
        ArrayList<String> actorArray = levelReader.GetActors();
        ArrayList<String> itemArray = levelReader.GetItems();
        ArrayList<String> tileActionIdArray = levelReader.GetActionIdArray();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile();
                Character tileChar = levelArray.get(j).charAt(i);
                Character actorChar = actorArray.get(j).charAt(i);
                Character itemChar = itemArray.get(j).charAt(i);
                Character tileActionIdChar = tileActionIdArray.get(j).charAt(i);

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
                    case 'd':
                        tiles[i][j].SetType(DOOR_LEFT);
                        tiles[i][j].SetTileAction(TileAction.Activated);
                        break;
                    case 'b':
                        tiles[i][j].SetType(DOOR_RIGHT);
                        tiles[i][j].SetTileAction(TileAction.Activated);
                        break;
                    case 'l':
                        tiles[i][j].SetType(LEVER_ON_FLOOR);
                        tiles[i][j].SetTileAction(TileAction.Activator);
                        break;
                    case '!':
                        tiles[i][j].SetType(WINCIRCLE);
                        tiles[i][j].SetTileAction(TileAction.Activator);
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

                if (tileActionIdChar != '-') {
                    tiles[i][j].SetTileActionId(Integer.parseInt(tileActionIdChar.toString()));
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

                    case 'h':
                        SpawnItem(i, j, "Health Potion", 5);
                        break;
                    case 'n':
                        SpawnItem(i, j, "Buff Potion", 1);
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
     * Gets an actor from a tile.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Actor in tile (null if empty)
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
     * Gets an actor from a tile.
     *
     * @param pos Position in the level
     * @return Actor in tile (null if empty)
     */
    public Actor GetActorInTile(Position pos) {
        return GetActorInTile(pos.x, pos.y);
    }

    /**
     * Gets an item from a tile.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Item in tile (null if empty)
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
     * Gets an item from a tile.
     *
     * @param pos Position in the level
     * @return Item in tile (null if empty)
     */
    public Item GetItemInTile(Position pos) {
        return GetItemInTile(pos.x, pos.y);
    }

    /**
     * Activates a tile's action
     *
     * @param pos Tile position
     */
    public void ActivateTile(Position pos) {
        Tile tileInPos = GetTile(pos.x, pos.y);
        if (tileInPos.GetAction() == TileAction.Activator) {
            if (tileInPos.GetType().is(WINCIRCLE)) {
                game.AdvanceLevel();
            } else {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        Tile tile = tiles[i][j];
                        if (tile.GetAction() == TileAction.Activated) {
                            if (tile.GetActionId() == tileInPos.GetActionId()) {
                                TileType type = tile.GetType();
                                if (type.is(DOOR)) {
                                    tile.SetOriginalActionChangeType(type);
                                    tileInPos.SetActionActive(true);
                                    tile.SetType(tile.GetActionChangeType());
                                    System.out.println("'" + type + "' opened to '" + tile.GetActionChangeType() + "'!");
                                } else if (type.is(PASSABLE)) {
                                    tileInPos.SetActionActive(false);
                                    tile.SetType(tile.GetOriginalActionChangeType());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculates the distance between two tiles.
     *
     * @param pos1 Position 1
     * @param pos2 Position 2
     * @return Distance in tiles
     */
    public int GetDistance(Position pos1, Position pos2) {
        int distX = Math.abs(pos1.x - pos2.x);
        int distY = Math.abs(pos1.y - pos2.y);

        return distX + distY;
    }

    /**
     * Returns the direction in which tile B is from tile A.
     *
     * @param pos1 Tile A position
     * @param pos2 Tile B position
     * @return Direction of B from A
     */
    public Direction PositionDirection(Position pos1, Position pos2) {
        int distX = pos1.x - pos2.x;
        int distY = pos1.y - pos2.y;
        Direction dir;

        if (Math.abs(distX) > Math.abs(distY)) {
            if (distX < 0) {
                dir = RIGHT;
            } else {
                dir = LEFT;
            }
        } else {
            if (distY < 0) {
                dir = DOWN;
            } else {
                dir = UP;
            }
        }

        return dir;
    }

    public boolean IsGameOver() {
        if (player.GetHealth() <= 0) {
            gameOver = true;
        }
        return gameOver;
    }

    public void SetGameOver(boolean value) {
        gameOver = value;
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

    public CombatHandler GetCombatHandler() {
        return combatHandler;
    }

    /**
     * UI needs to check if in the first level
     *
     * @return Game instance
     */
    public Game GetGame() {
        return game;
    }

    private void SpawnPlayer(int x, int y) {
        player = new Actor(x, y, "Player", this, 5, 5, true, 0, 0, 0, 0, 0, 0);
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

            case "Health Potion":
                type = DamageType.None;
                break;
            case "Buff Potion":
                type = DamageType.None;
                break;

            default:
                type = DamageType.None;
                break;
        }

        Item item = new Item(x, y, name, this, power, type);
        entityManager.AddItem(item);
    }

    private void SpawnEnemy(int x, int y, String name) {
        Actor enemy;
        Item enemyWeapon;

        switch (name) {
            case "Skeleton":
                enemy = new Actor(x, y, "Skeleton", this, 2, 2, false, 0, 2, -2, -1, 1, -2);
                enemyWeapon = new Item(0, 0, "Skeleton Sword", this, 1, Slash);
                enemy.UseItem(enemyWeapon);
                break;
            case "Troll":
                enemy = new Actor(x, y, "Troll", this, 5, 5, false, 2, 0, 1, -2, 0, -1);
                enemyWeapon = new Item(0, 0, "Troll's Club", this, 1, Crush);
                enemy.UseItem(enemyWeapon);
                break;
            case "Lizard Man":
                enemy = new Actor(x, y, "Lizard Man", this, 3, 3, false, -2, -2, 0, 1, 2, 1);
                enemyWeapon = new Item(0, 0, "Reptilian Blade", this, 1, Slash);
                enemy.UseItem(enemyWeapon);
                break;
            case "Dragon":
                enemy = new Actor(x, y, "Dragon", this, 10, 10, false, 0, 0, 0, 2, 2, 2);
                enemyWeapon = new Item(0, 0, "Breath of Fire", this, 1, Burn);
                enemy.UseItem(enemyWeapon);
                break;
            default:
                enemy = new Actor(x, y, "UNDEFINED", this, 1, 1, false, 0, 0, 0, 0, 0, 0);
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
