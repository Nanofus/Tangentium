package fi.nano.tangentium.fileProcessing;

import java.util.ArrayList;

public class SpriteListReader {

    ArrayList<String> actorList;
    ArrayList<String> tileList;
    ArrayList<String> itemList;
    ArrayList<String> itemIconList;

    public SpriteListReader() {
        FileReader fileReader = new FileReader();
        actorList = fileReader.ReadFile("data/actors.txt");
        tileList = fileReader.ReadFile("data/tiles.txt");
        itemList = fileReader.ReadFile("data/items.txt");
        itemIconList = fileReader.ReadFile("data/itemIcons.txt");
    }

    public ArrayList<String> GetActorList() {
        return actorList;
    }

    public ArrayList<String> GetTileList() {
        return tileList;
    }

    public ArrayList<String> GetItemList() {
        return itemList;
    }

    public ArrayList<String> GetItemIconList() {
        return itemIconList;
    }
}
