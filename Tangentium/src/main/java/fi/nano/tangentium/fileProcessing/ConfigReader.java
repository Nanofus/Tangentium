package fi.nano.tangentium.fileProcessing;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the config file
 * @author Nanofus
 */
public class ConfigReader {

    private ArrayList<String> config = new ArrayList();
    
    /**
     * Constructor reads the config file (config.txt)
     */
    public ConfigReader() {
        FileReader fileReader = new FileReader();
        Scanner in = null;

        config = fileReader.ReadFile("data/config.txt");
    }

    public ArrayList<String> GetConfig() {
        return config;
    }

    public int GetWindowWidth() {
        return Integer.parseInt(config.get(0));
    }

    public int GetWindowHeight() {
        return Integer.parseInt(config.get(1));
    }
}
