package fi.nano.tangentium.fileProcessing;

import fi.nano.tangentium.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the list of levels
 *
 * @author Nanofus
 */
public class LevelListReader {

    ArrayList<String> levelList;

    /**
     * @param errorDialog Dialog for error messages
     */
    public LevelListReader(ErrorDialog errorDialog) {
        Scanner in = null;
        levelList = new ArrayList<>();

        try {
            in = new Scanner(new File("levels/level_list.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Level list file not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            levelList.add(in.nextLine());
        }

    }

    public ArrayList<String> GetLevelList() {
        return levelList;
    }
}
