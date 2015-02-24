
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
    
    private BufferedImage icon;
    private BufferedImage logo;

    private BufferedImage floor;
    private BufferedImage wall;
    private BufferedImage chasm;
    private BufferedImage water;
    private BufferedImage grass;
    private BufferedImage tree;
    private BufferedImage path;
    private BufferedImage pillar;
    
    private BufferedImage door_left;
    private BufferedImage lever;
    private BufferedImage wincircle;

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
    private BufferedImage stunicon;
    private BufferedImage healthicon;
    private BufferedImage weaponstunicon;
    private BufferedImage gameover;
    private BufferedImage victory;
    
    private BufferedImage swordicon;
    private BufferedImage spearicon;
    private BufferedImage maceicon;
    private BufferedImage pyrospellicon;
    private BufferedImage icestafficon;
    private BufferedImage wandicon;

    public ImageLoader() {
        try {
            
            logo = ImageIO.read(new File("graphics/logo.png"));
            icon = ImageIO.read(new File("graphics/icon.png"));
            
            floor = ImageIO.read(new File("graphics/tiles/floor.png"));
            wall = ImageIO.read(new File("graphics/tiles/wall.png"));
            chasm = ImageIO.read(new File("graphics/tiles/floor.png"));
            water = ImageIO.read(new File("graphics/tiles/water.png"));
            grass = ImageIO.read(new File("graphics/tiles/grass.png"));
            tree = ImageIO.read(new File("graphics/tiles/tree.png"));
            path = ImageIO.read(new File("graphics/tiles/path.png"));
            pillar = ImageIO.read(new File("graphics/tiles/pillar.png"));
            
            door_left = ImageIO.read(new File("graphics/tiles/door_left.png"));
            lever = ImageIO.read(new File("graphics/tiles/lever.png"));
            wincircle = ImageIO.read(new File("graphics/tiles/wincircle.png"));

            skeleton = ImageIO.read(new File("graphics/actors/skeleton.png"));
            lizardMan = ImageIO.read(new File("graphics/actors/lizardman.png"));
            troll = ImageIO.read(new File("graphics/actors/troll.png"));
            dragon = ImageIO.read(new File("graphics/actors/dragon.png"));

            player = ImageIO.read(new File("graphics/actors/player.png"));

            sword = ImageIO.read(new File("graphics/weapons/sword.png"));
            spear = ImageIO.read(new File("graphics/weapons/spear.png"));
            mace = ImageIO.read(new File("graphics/weapons/mace.png"));
            pyrospell = ImageIO.read(new File("graphics/weapons/pyrospell.png"));
            icestaff = ImageIO.read(new File("graphics/weapons/icestaff.png"));
            wand = ImageIO.read(new File("graphics/weapons/wand.png"));
            
            weaponbackg = ImageIO.read(new File("graphics/icons/weaponbackg.png"));
            stunicon = ImageIO.read(new File("graphics/icons/stunicon.png"));
            healthicon = ImageIO.read(new File("graphics/icons/healthicon.png"));
            weaponstunicon = ImageIO.read(new File("graphics/icons/weaponstunicon.png"));
            gameover = ImageIO.read(new File("graphics/gameover.png"));
            victory = ImageIO.read(new File("graphics/victory.png"));
            
            swordicon = ImageIO.read(new File("graphics/icons/swordicon.png"));
            spearicon = ImageIO.read(new File("graphics/icons/spearicon.png"));
            maceicon = ImageIO.read(new File("graphics/icons/maceicon.png"));
            pyrospellicon = ImageIO.read(new File("graphics/icons/pyrospellicon.png"));
            icestafficon = ImageIO.read(new File("graphics/icons/icestafficon.png"));
            wandicon = ImageIO.read(new File("graphics/icons/wandicon.png"));
            
        } catch (IOException ex) {
            System.out.println("Graphics file not found! Exiting...");
            System.exit(1);
        }
    }

    public BufferedImage GetImage(String name) {
        switch(name) {
            case "Icon":
                return icon;
            case "Logo":
                return logo;
            
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
            
            case "Door Left":
                return door_left;
            case "Lever":
                return lever;
            case "Win Circle":
                return wincircle;
            
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
            case "Stun Icon":
                return stunicon;
            case "Health Icon":
                return healthicon;
            case "Weapon Delay Icon":
                return weaponstunicon;
            case "Game Over":
                return gameover;
            case "Victory":
                return victory;
                
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
