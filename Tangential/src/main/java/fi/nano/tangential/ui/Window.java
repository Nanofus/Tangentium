package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Peli-ikkuna.
 *
 * @author Nanofus
 */
public class Window implements Runnable {

    private final int windowWidth = 800;
    private final int windowHeight = 600;
    
    private final Game game;

    private JFrame frame;

    private final InputListener inputListener;
    private LevelRenderer levelRenderer;
    
    private BufferedImage img_floor;
    private BufferedImage img_chasm;
    private BufferedImage img_water;
    private BufferedImage img_wall;
    
    private BufferedImage img_skeleton;
    private BufferedImage img_lizardMan;
    private BufferedImage img_troll;
    private BufferedImage img_dragon;
    
    private BufferedImage img_player;

    public Window(Game game) {
        this.game = game;

        inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(inputListener);

        LoadGraphics();
        levelRenderer = new LevelRenderer(game.GetLevel(),windowWidth,windowHeight,img_player,img_floor,img_wall,img_chasm,img_water,img_skeleton,img_lizardMan,img_troll,img_dragon);

        frame.add(levelRenderer);
    }

    private void createComponents(Container container) {

    }
    
    private void LoadGraphics() {
        try {
            img_floor = ImageIO.read(new File("graphics/floor.png"));
            img_wall = ImageIO.read(new File("graphics/wall.png"));
            img_chasm = ImageIO.read(new File("graphics/chasm.png"));
            img_water = ImageIO.read(new File("graphics/water.png"));
            
            img_skeleton = ImageIO.read(new File("graphics/skeleton.png"));
            img_lizardMan = ImageIO.read(new File("graphics/lizardman.png"));
            img_troll = ImageIO.read(new File("graphics/troll.png"));
            img_dragon = ImageIO.read(new File("graphics/dragon.png"));
            
            img_player = ImageIO.read(new File("graphics/player.png"));
        } catch (IOException ex) {
            System.out.println("Graphics file not found! Exiting...");
            System.exit(1);
        }
    }

    public JFrame getFrame() {
        return frame;
    }

}
