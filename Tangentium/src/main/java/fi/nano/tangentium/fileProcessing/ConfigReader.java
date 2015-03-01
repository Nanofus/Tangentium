package fi.nano.tangentium.fileProcessing;

import fi.nano.tangentium.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the config file
 * @author Nanofus
 */
public class ConfigReader {

    ArrayList<String> config = new ArrayList();

    /**
     * Konstruktori reads the config file (config.txt)
     */
    public ConfigReader(ErrorDialog errorDialog) {
        Scanner in = null;

        try {
            in = new Scanner(new File("config.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("Config file not found! Exiting...");
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
