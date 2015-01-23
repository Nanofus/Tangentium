package fi.nano.tangential.gameLogic;

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
