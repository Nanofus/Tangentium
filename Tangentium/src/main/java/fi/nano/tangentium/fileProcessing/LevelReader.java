package fi.nano.tangentium.fileProcessing;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads a level file.
 *
 * @author Nanofus
 */
public class LevelReader {

    private final ArrayList<String> level;
    private final ArrayList<String> actors;
    private final ArrayList<String> items;
    private final ArrayList<String> actionIds;

    /**
     * Loads the level from a file.
     *
     * @param levelName Level folder name
     */
    public LevelReader(String levelName) {
        Scanner in = null;
        FileReader fileReader = new FileReader();

        System.out.println("Reading level...");
        level = fileReader.ReadFile("data/levels/" + levelName + "/level.txt");
        System.out.println("Reading actors...");
        actors = fileReader.ReadFile("data/levels/" + levelName + "/actors.txt");
        System.out.println("Reading items...");
        items = fileReader.ReadFile("data/levels/" + levelName + "/items.txt");
        System.out.println("Reading tile action ID:s...");
        actionIds = fileReader.ReadFile("data/levels/"+ levelName + "/actionids.txt");
                
        //Fix for the mysterious question mark in the beginning of the level file
        level.set(0, level.get(0).substring(1));
        actors.set(0, actors.get(0).substring(1));
        items.set(0, items.get(0).substring(1));
        actionIds.set(0, actionIds.get(0).substring(1));
    }

    public ArrayList<String> GetLevel() {
        return level;
    }

    public ArrayList<String> GetActors() {
        return actors;
    }

    public ArrayList<String> GetItems() {
        return items;
    }

    public ArrayList<String> GetActionIdArray() {
        return actionIds;
    }

}
