
package fi.nano.tangential.ui;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Piirtää tason, hahmot ja esineet ruudulle.
 *
 * @author Nanofus
 */
public class LevelRenderer extends JPanel {

    private final int tileSize;

    private final Level level;

    private final ImageLoader imageLoader;

    private int originX;
    private int originY;

    private int centerX;
    private int centerY;

    LevelRenderer(Level level, int windowWidth, int windowHeight) {
        imageLoader = new ImageLoader();

        this.level = level;

        this.originX = windowWidth / 2;
        this.originY = windowHeight / 2;

        tileSize = imageLoader.GetImage("Floor").getWidth();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < level.GetWidth(); i++) {
            for (int j = 0; j < level.GetHeight(); j++) {
                BufferedImage drawnImage;
                switch (level.GetTile(i, j).GetType()) {
                    case WALL:
                        drawnImage = imageLoader.GetImage("Wall");
                        break;
                    case FLOOR:
                        drawnImage = imageLoader.GetImage("Floor");
                        break;
                    case WATER:
                        drawnImage = imageLoader.GetImage("Water");
                        break;
                    default:
                        drawnImage = imageLoader.GetImage("Chasm");
                        break;
                }
                g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);

                for (Item item : level.GetItems()) {
                    if (item.GetPosition().x == i && item.GetPosition().y == j) {
                        if (!item.IsEquipped()) {
                            drawnImage = imageLoader.GetImage(item.GetName());
                            g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);
                        }
                    }
                }

                for (Actor actor : level.GetActors()) {
                    if (actor.GetPosition().x == i && actor.GetPosition().y == j) {
                        drawnImage = imageLoader.GetImage(actor.GetName());
                        g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);
                    }
                }

                if (level.GetPlayer().GetPosition().x == i && level.GetPlayer().GetPosition().y == j) {
                    drawnImage = imageLoader.GetImage("Player");
                    g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);
                }
            }
        }
    }
}
