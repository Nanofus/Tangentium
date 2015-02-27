package fi.nano.tangential.fileProcessing;

import fi.nano.tangential.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka joka lukee pelin ikkunan resoluution tekstitiedostosta
 * @author Nanofus
 */
public class ConfigReader {

    ArrayList<String> config = new ArrayList();

    /**
     * Konstruktori lukee config-tekstitiedoston (config.txt)
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

    /**
     * Palauttaa ruudun halutun leveyden
     * @return Ruudun leveys
     */
    public int GetWindowWidth() {
        return Integer.parseInt(config.get(0));
    }

    /**
     * Palauttaa ruudun halutun korkeuden
     * @return Ruudun korkeus
     */
    public int GetWindowHeight() {
        return Integer.parseInt(config.get(1));
    }
}
