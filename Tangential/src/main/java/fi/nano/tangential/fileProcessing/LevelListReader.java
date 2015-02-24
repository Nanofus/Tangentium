package fi.nano.tangential.fileProcessing;

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

    public LevelListReader() {
    }

    /**
     * Lukee tiedoston, joka lisältää listan pelin tasoista
     * @return Lista pelin tasoista
     */
    public ArrayList<String> ReadLevelList() {
        Scanner in = null;
        ArrayList<String> levels = new ArrayList<>();

        try {
            in = new Scanner(new File("levels/level_list.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Level list file not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            levels.add(in.nextLine());
        }

        return levels;
    }
}
