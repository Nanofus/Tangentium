package fi.nano.tangentium.fileProcessing;

import java.util.ArrayList;

/**
 * Reads the list of levels
 *
 * @author Nanofus
 */
public class LevelListReader {

    ArrayList<String> levelList;

    public LevelListReader() {
        FileReader fileReader = new FileReader();
        levelList = fileReader.ReadFile("levels/level_list.txt");
    }

    public ArrayList<String> GetLevelList() {
        return levelList;
    }
}
