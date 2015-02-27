package fi.nano.tangential.fileProcessing;

import fi.nano.tangential.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lukee listan pelin tasoista
 *
 * @author Nanofus
 */
public class LevelListReader {

    ArrayList<String> levelList;

    /**
     * 
     * @param errorDialog Virhedialogi virheilmoituksia varten
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

    /**
     * Lukee tiedoston, joka lisältää listan pelin tasoista
     *
     * @return Lista pelin tasoista
     */
    public ArrayList<String> GetLevelList() {
        return levelList;
    }
}
