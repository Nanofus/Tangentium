
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

    public ImageLoader() {
        try {
            floor = ImageIO.read(new File("graphics/floor.png"));
            wall = ImageIO.read(new File("graphics/wall.png"));
            chasm = ImageIO.read(new File("graphics/chasm.png"));
            water = ImageIO.read(new File("graphics/water.png"));
            grass = ImageIO.read(new File("graphics/grass.png"));
            tree = ImageIO.read(new File("graphics/tree.png"));
            path = ImageIO.read(new File("graphics/path.png"));
            pillar = ImageIO.read(new File("graphics/pillar.png"));

            skeleton = ImageIO.read(new File("graphics/skeleton.png"));
            lizardMan = ImageIO.read(new File("graphics/lizardman.png"));
            troll = ImageIO.read(new File("graphics/troll.png"));
            dragon = ImageIO.read(new File("graphics/dragon.png"));

            player = ImageIO.read(new File("graphics/player.png"));

            sword = ImageIO.read(new File("graphics/sword.png"));
            spear = ImageIO.read(new File("graphics/spear.png"));
            mace = ImageIO.read(new File("graphics/mace.png"));
            pyrospell = ImageIO.read(new File("graphics/pyrospell.png"));
            icestaff = ImageIO.read(new File("graphics/icestaff.png"));
            wand = ImageIO.read(new File("graphics/wand.png"));
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
                return wall;
            case "Tree":
                return floor;
            case "Path":
                return chasm;
            case "Pillar":
                return water;
            
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
                
            default:
                return water;
        }
    }

}
