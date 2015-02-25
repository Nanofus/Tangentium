package fi.nano.tangential.fileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nanofus
 */
public class ConfigReader {

    ArrayList<String> config = new ArrayList();

    public ConfigReader() {
        Scanner in = null;

        try {
            in = new Scanner(new File("config.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Level file not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            config.add(in.nextLine());
        }
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
