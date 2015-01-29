package fi.nano.tangential.gameLogic.singletons;

import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import java.util.ArrayList;

/**
 *
 * @author Nanofus
 */
public class EntityManager {

    private ArrayList<Item> items;
    private ArrayList<Actor> enemies;

    public EntityManager() {
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
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
    
    
    public void DestroyActor(Actor actor) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) == actor) {
                enemies.remove(i);
            }
        }
    }
}
