
package fi.nano.tangential.fileProcessing;

import java.io.File;
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
            in = new Scanner(new File("levels/" + levelName + ".txt"),"UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Level file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            level.add(in.nextLine());
        }
        
        System.out.println("Reading actors...");
        
        try {
            in = new Scanner(new File("levels/" + levelName + "_actors.txt"),"UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Actors file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            actors.add(in.nextLine());
        }
        
        System.out.println("Reading items...");
        
        try {
            in = new Scanner(new File("levels/" + levelName + "_items.txt"),"UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Items file not found! Exiting...");
            System.exit(1);
        }
        
        while(in.hasNext()) {
            items.add(in.nextLine());
        }
        
        /*System.out.println(level);
        System.out.println(actors);
        System.out.println(items);*/
        
        //Fix sille että tiedostojen alkuun tulee ylimääräinen kysymysmerkki jostain merkistöenkoodauksen mysteerisyystä
        level.set(0, level.get(0).substring(1));
        actors.set(0, actors.get(0).substring(1));
        items.set(0, items.get(0).substring(1));
        
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
