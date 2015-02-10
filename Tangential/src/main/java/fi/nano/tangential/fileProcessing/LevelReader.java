
package fi.nano.tangential.fileProcessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka joka lukee tason tekstitiedostosta.
 * @author Nanofus
 */
public class LevelReader {
    
    private ArrayList<String> level;
    private ArrayList<String> actors;
    private ArrayList<String> items;
    
    /**
     * Konstruktori lataa tason tekstitiedostosta.
     *
     * @param   levelName   Ladattavan tekstitiedoston nimi ilman tiedostopäätettä (.txt)
     */
    public LevelReader(String levelName) {
        Scanner in = null;
        
        level = new ArrayList();
        actors = new ArrayList();
        items = new ArrayList();
        
        System.out.println("Reading level...");
        
        try {
            in = new Scanner(new FileReader("levels/" + levelName + ".txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Level file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            level.add(in.nextLine());
        }
        
        System.out.println("Reading actors...");
        
        try {
            in = new Scanner(new FileReader("levels/" + levelName + "_actors.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Actors file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            actors.add(in.nextLine());
        }
        
        System.out.println("Reading items...");
        
        try {
            in = new Scanner(new FileReader("levels/" + levelName + "_items.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Items file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            items.add(in.nextLine());
        }
        
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
    
}
