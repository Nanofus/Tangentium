
package fi.nano.tangential.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Lataa pelin kuvat tiedostoista.
 * 
 * @author Nanofus
 */
public class ImageLoader {

    private BufferedImage floor;
    private BufferedImage wall;
    private BufferedImage chasm;
    private BufferedImage water;
    private BufferedImage grass;
    private BufferedImage tree;
    private BufferedImage path;
    private BufferedImage pillar;

    private BufferedImage skeleton;
    private BufferedImage lizardMan;
    private BufferedImage troll;
    private BufferedImage dragon;

    private BufferedImage player;

    private BufferedImage sword;
    private BufferedImage spear;
    private BufferedImage mace;
    private BufferedImage pyrospell;
    private BufferedImage icestaff;
    private BufferedImage wand;
    
    private BufferedImage weaponbackg;
    
    private BufferedImage swordicon;
    private BufferedImage spearicon;
    private BufferedImage maceicon;
    private BufferedImage pyrospellicon;
    private BufferedImage icestafficon;
    private BufferedImage wandicon;

    public ImageLoader() {
        try {
            floor = ImageIO.read(new File("graphics/floor2.png"));
            wall = ImageIO.read(new File("graphics/wall2.png"));
            chasm = ImageIO.read(new File("graphics/floor2.png"));
            water = ImageIO.read(new File("graphics/water2.png"));
            grass = ImageIO.read(new File("graphics/grass2.png"));
            tree = ImageIO.read(new File("graphics/tree2.png"));
            path = ImageIO.read(new File("graphics/path2.png"));
            pillar = ImageIO.read(new File("graphics/pillar2.png"));

            skeleton = ImageIO.read(new File("graphics/player2.png"));
            lizardMan = ImageIO.read(new File("graphics/player2.png"));
            troll = ImageIO.read(new File("graphics/player2.png"));
            dragon = ImageIO.read(new File("graphics/player2.png"));

            player = ImageIO.read(new File("graphics/player2.png"));

            sword = ImageIO.read(new File("graphics/sword2.png"));
            spear = ImageIO.read(new File("graphics/sword2.png"));
            mace = ImageIO.read(new File("graphics/sword2.png"));
            pyrospell = ImageIO.read(new File("graphics/sword2.png"));
            icestaff = ImageIO.read(new File("graphics/sword2.png"));
            wand = ImageIO.read(new File("graphics/sword2.png"));
            
            weaponbackg = ImageIO.read(new File("graphics/weaponbackg.png"));
            
            swordicon = ImageIO.read(new File("graphics/swordicon.png"));
            spearicon = ImageIO.read(new File("graphics/spearicon.png"));
            maceicon = ImageIO.read(new File("graphics/maceicon.png"));
            pyrospellicon = ImageIO.read(new File("graphics/pyrospellicon.png"));
            icestafficon = ImageIO.read(new File("graphics/icestafficon.png"));
            wandicon = ImageIO.read(new File("graphics/wandicon.png"));
            
        } catch (IOException ex) {
            System.out.println("Graphics file not found! Exiting...");
            System.exit(1);
        }
    }

    public BufferedImage GetImage(String name) {
        switch(name) {
            case "Wall":
                return wall;
            case "Floor":
                return floor;
            case "Chasm":
                return chasm;
            case "Water":
                return water;
            case "Grass":
                return grass;
            case "Tree":
                return tree;
            case "Path":
                return path;
            case "Pillar":
                return pillar;
            
            case "Skeleton":
                return skeleton;
            case "Lizard Man":
                return lizardMan;
            case "Troll":
                return troll;
            case "Dragon":
                return dragon;
                
            case "Player":
                return player;
                
            case "Sword":
                return sword;
            case "Spear":
                return spear;
            case "Mace":
                return mace;
            case "Pyrospell":
                return pyrospell;
            case "Ice Staff":
                return icestaff;
            case "Wand":
                return wand;
                
            case "Weapon Background":
                return weaponbackg;
                
            case "Sword Icon":
                return swordicon;
            case "Spear Icon":
                return spearicon;
            case "Mace Icon":
                return maceicon;
            case "Pyrospell Icon":
                return pyrospellicon;
            case "Ice Staff Icon":
                return icestafficon;
            case "Wand Icon":
                return wandicon;
                
            default:
                return water;
        }
    }

}
