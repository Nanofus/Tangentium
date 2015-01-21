package fi.nano.tangential;

import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.Position;
import fi.nano.tangential.gameLogic.*;
import java.util.*;

public class Game {

    private Level level;
    private ArrayList<Item> items;
    private ArrayList<Actor> enemies;

    private Actor player;
    
    private final Random random;

    public Game(int width, int height, int enemies, int items) {
        
        System.out.println("\nGenerating game...");
        
        random = new Random();
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        
        System.out.println("\nGenerating level...");
        
        GenerateLevel(width, height);

        System.out.println("\nGenerating enemies...");
        
        SpawnEnemies(enemies);
        
        System.out.println("\nGenerating items...");
        
        SpawnItems(items);
        
        System.out.println("\nSpawning player...");
        
        SpawnPlayer(0,0);
        
        System.out.println("\nGenerated level:");

        PrintLevelToConsole();
        
        System.out.println("\nStarting game.");
    }

    private void GenerateLevel(int width, int height) {
        level = new Level(width, height);
    }
    
    private void SpawnPlayer(int x, int y) {
        player = new Actor(x,y,"Player",5,true);
    }
    
    private void SpawnItems(int items) {
        while (items > 0) {
            int x = random.nextInt(level.GetWidth());
            int y = random.nextInt(level.GetHeight());

            if (level.GetTile(x, y).GetType()!=TileType.WALL) {
                SpawnItem(x,y);
                items--;
            }

        }
    }
    
    private void SpawnItem(int x, int y) {
        int power = 1;
        ItemType type = ItemType.SLASH;
        
        if (random.nextInt(8)==7) {
            power = 2;
        }
        if (random.nextInt(19)==18) {
            power = 3;
        }
        
        int itemType = random.nextInt(6);
        switch(itemType) {
            case 0:
                type = ItemType.SLASH;
                break;
            case 1:
                type = ItemType.PIERCE;
                break;
            case 2:
                type = ItemType.CRUSH;
                break;
            case 3:
                type = ItemType.BURN;
                break;
            case 4:
                type = ItemType.FREEZE;
                break;
            case 5:
                type = ItemType.ARCANE;
                break;
        }
        
        Item item = new Item(x,y,power,type);
        items.add(item);
    }
    
    private void SpawnEnemies(int enemies) {
        while (enemies > 0) {
            int x = random.nextInt(level.GetWidth());
            int y = random.nextInt(level.GetHeight());

            if (level.GetTile(x, y).GetType()!=TileType.WALL) {
                SpawnEnemy(x,y);
                enemies--;
            }

        }
    }

    private void SpawnEnemy(int x, int y) {
        Actor enemy = new Actor(x,y,"Skeleton",1,false);
        enemies.add(enemy);
    }
    
    private void PrintLevelToConsole() {
        System.out.println("----\n");
        
        for (int i = 0; i < level.GetWidth(); i++) {
            for (int j = 0; j < level.GetHeight(); j++) {
                boolean charChosen = false;
                for (Actor enemy : enemies) {
                    Position actorPos = enemy.GetPosition();
                    if (actorPos.x == i && actorPos.y == j) {
                        System.out.print(enemy.GetSymbol());
                        charChosen = true;
                    }
                }
                if (!charChosen) {
                    for (Item item : items) {
                        Position itemPos = item.GetPosition();
                        if (itemPos.x == i && itemPos.y == j) {
                            System.out.print(item.GetSymbol());
                            charChosen = true;
                        }
                    }
                }
                if (!charChosen) {
                    if (level.GetTile(i, j).GetType()==TileType.WALL) {
                        System.out.print('X');
                    } else {
                        System.out.print('.');
                    }
                    charChosen = true;
                }
            }
            System.out.print("\n");
        }
        
        System.out.println("\n----");
    }
}
