package fi.nano.tangential.gameLogic;

/**
 * Luokka, joka toimii pohjana Item- ja Actor-tyyppisille olioille. Sisältää entiteetin nimen, symbolin ja sijainnin.
 * 
 * @author Nanofus
 */
public class Entity {

    private String name;
    private Character symbol;

    private Position position;

    public Entity(int x, int y, String name) {
        position = new Position(x, y);
        
        this.name = name;

        switch (name) {

            //Actor names
            case "Player":
                symbol = '@';
                break;
            case "Skeleton":
                symbol = 'S';
                break;
            case "Troll":
                symbol = 'T';
                break;
            case "Lizard Man":
                symbol = 'L';
                break;
            case "Dragon":
                symbol = 'D';
                break;

            //Item names
            case "Sword":
                symbol = 's';
                break;
            case "Spear":
                symbol = 'r';
                break;
            case "Mace":
                symbol = 'm';
                break;
            case "Pyrospell":
                symbol = 'p';
                break;
            case "Ice Staff":
                symbol = 'i';
                break;
            case "Wand":
                symbol = 'w';
                break;

            //NPC weapons
            case "Dragonfire":
                symbol = 'd';
                break;
        }

    }
    
    public void Move(int x, int y) {
        position.x = position.x + x;
        position.y = position.y + y;
    }

    public Character GetSymbol() {
        return symbol;
    }

    public Position GetPosition() {
        return position;
    }

}
