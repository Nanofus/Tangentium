package fi.nano.tangentium.fileProcessing;

import fi.nano.tangentium.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads a level file.
 *
 * @author Nanofus
 */
public class LevelReader {

    private ArrayList<String> level;
    private ArrayList<String> actors;
    private ArrayList<String> items;
    private ArrayList<String> actionIds;

    /**
     * Loads the level from a file.
     *
     * @param errorDialog Error dialog
     * @param levelName Level folder name
     */
    public LevelReader(ErrorDialog errorDialog, String levelName) {
        Scanner in = null;

        level = new ArrayList();
        actors = new ArrayList();
        items = new ArrayList();
        actionIds = new ArrayList();

        System.out.println("Reading level...");

        try {
            in = new Scanner(new File("levels/" + levelName + "/level.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Level file for '"+levelName+"' not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            level.add(in.nextLine());
        }

        System.out.println("Reading actors...");

        try {
            in = new Scanner(new File("levels/" + levelName + "/actors.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Actors file for '"+levelName+"' not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            actors.add(in.nextLine());
        }

        System.out.println("Reading items...");

        try {
            in = new Scanner(new File("levels/" + levelName + "/items.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Items file for '"+levelName+"' not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            items.add(in.nextLine());
        }

        System.out.println("Reading tile action ID:s...");

        try {
            in = new Scanner(new File("levels/" + levelName + "/actionids.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Tile actions file for '"+levelName+"' not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            actionIds.add(in.nextLine());
        }
        
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
