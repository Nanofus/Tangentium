package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.entities.Tile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Nanofus
 */
public class Level {

    private Tile[][] tiles;
    private int width;
    private int height;

    private ArrayList<Item> items;
    private ArrayList<Actor> enemies;

    private Actor player;

    Random random = new Random();

    public Level(int width, int height, int enemies, int items) {

        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();

        if (width < 2) {
            width = 1;
        }
        if (height < 2) {
            height = 1;
        }
        
        this.width = width;
        this.height = height;
        
        tiles = new Tile[width][height];
        
        if (enemies < 0) {
            enemies = 0;
        }
        if (items < 0) {
            items = 0;
        }

        //Random level generation, will use a better algorithm in the future
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile();
                tiles[i][j] = tile;
            }
        }

        if (GetHeight() > 1 && GetWidth() > 1) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (random.nextBoolean() == true && random.nextBoolean() == true && random.nextBoolean() == true) {
                        tiles[i][j].SetType(TileType.WALL);
                    }
                }
            }
        }

        System.out.println();
        SpawnItems(items);
        System.out.println();
        SpawnPlayer(0, 0);
        System.out.println();
        SpawnEnemies(enemies);
        System.out.println();

    }

    public Level(String levelName) throws FileNotFoundException {

        Scanner in = new Scanner(new FileReader("levels/" + levelName + ".txt"));

    }

    public ArrayList<Item> GetItems() {
        return items;
    }

    public ArrayList<Actor> GetEnemies() {
        return enemies;
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

    private void SpawnPlayer(int x, int y) {
        player = new Actor(x, y, "Player", 5, true);
    }

    private void SpawnItems(int items) {
        if (width > 1 && height > 1) {
            while (items > 0) {
                int x = random.nextInt(GetWidth());
                int y = random.nextInt(GetHeight());

                if (GetTile(x, y).GetType() != TileType.WALL) {
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
        items.add(item);
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
        Actor enemy = new Actor(x, y, "Skeleton", 1, false);
        enemies.add(enemy);
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
                    for (Actor enemy : enemies) {
                        Position actorPos = enemy.GetPosition();
                        if (actorPos.x == i && actorPos.y == j) {
                            sb.append(enemy.GetSymbol());
                            charChosen = true;
                        }
                    }
                }
                if (!charChosen) {
                    for (Item item : items) {
                        Position itemPos = item.GetPosition();
                        if (itemPos.x == i && itemPos.y == j) {
                            sb.append(item.GetSymbol());
                            charChosen = true;
                        }
                    }
                }
                if (!charChosen) {
                    if (GetTile(i, j).GetType() == TileType.WALL) {
                        sb.append('X');
                    } else {
                        sb.append('.');
                    }
                    charChosen = true;
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
