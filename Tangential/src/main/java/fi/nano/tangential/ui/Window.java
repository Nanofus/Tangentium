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

    private Game game;

    private JFrame frame;

    private InputListener inputListener;

    private LevelRenderer level;

    public Window(Game game) {
        this.game = game;

        inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setPreferredSize(new Dimension(800, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(inputListener);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("graphics/floor.png"));
        } catch (IOException ex) {
            System.out.println("Graphics file not found! Exiting...");
            System.exit(1);
        }
        LevelRenderer level = new LevelRenderer(image);

        frame.add(level);
    }

    private void createComponents(Container container) {

    }

    public JFrame getFrame() {
        return frame;
    }

}
