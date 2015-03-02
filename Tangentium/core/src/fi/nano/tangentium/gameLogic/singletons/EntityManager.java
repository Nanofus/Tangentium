package fi.nano.tangentium.gameLogic.singletons;

import fi.nano.tangentium.gameLogic.entities.Actor;
import fi.nano.tangentium.gameLogic.entities.Item;
import java.util.ArrayList;

/**
 * Storage of Actors and Items in a Level. In charge of removing Items and Actors from the game.
 * 
 * @author Nanofus
 */
public class EntityManager {

    private ArrayList<Item> items;
    private ArrayList<Actor> enemies;

    public EntityManager() {
        this.items = new ArrayList<Item>();
        this.enemies = new ArrayList<Actor>();
    }

    public ArrayList<Item> GetItems() {
        return items;
    }

    public ArrayList<Actor> GetEnemies() {
        return enemies;
    }

    public void AddEnemy(Actor enemy) {
        enemies.add(enemy);
    }
    
    public void AddItem(Item item) {
        items.add(item);
    }
    
    /**
     * Removes an Actor from the game.
     * 
     * @param actor Removed actor
     */
    public void DestroyActor(Actor actor) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) == actor) {
                enemies.remove(i);
            }
        }
    }
    
    /**
     * Removes an Item from the game.
     * 
     * @param item Removed item
     */
    public void DestroyItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                items.remove(i);
            }
        }
    }
}
