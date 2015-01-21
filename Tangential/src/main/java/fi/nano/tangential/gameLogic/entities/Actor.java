package fi.nano.tangential.gameLogic.entities;

import fi.nano.tangential.gameLogic.Position;

/**
 *
 * @author Nanofus
 */
public class Actor {

    private String name;
    private Character symbol;

    private Item wieldedItem;

    private int hitPoints = 1;

    private boolean controlled = false;
    private boolean aiEnabled = false;

    private Position position;

    public Actor(int x, int y, String name, int hp, boolean controlled) {
        position = new Position(x,y);

        this.hitPoints = hp;

        this.name = name;

        this.controlled = controlled;

        if (!controlled) {
            aiEnabled = true;
        }

        if (controlled) {
            System.out.println("Spawned player at "+position);
        } else {
            System.out.println("Spawned enemy '" + name + "' at "+position);
        }
        
        switch (name) {
            case "Player":
                symbol = '@';
                break;
            case "Skeleton":
                symbol = 'S';
                break;
        }
    }

    public Character GetSymbol() {
        return symbol;
    }
    
    public Position GetPosition() {
        return position;
    }
    
}
