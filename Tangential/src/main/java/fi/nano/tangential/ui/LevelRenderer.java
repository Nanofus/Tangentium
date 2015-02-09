package fi.nano.tangential.ui;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.Position;
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

    private final int tileSizeX;
    private final int tileSizeY;

    private final int actorSizeX;
    private final int actorSizeY;

    private final Level level;

    private final ImageLoader imageLoader;

    private final int windowWidth;
    private final int windowHeight;
    private int offsetX;
    private int offsetY;

    LevelRenderer(Level level, int windowWidth, int windowHeight) {
        imageLoader = new ImageLoader();

        this.level = level;

        this.windowWidth = windowWidth / 2;
        this.windowHeight = windowHeight / 2;

        tileSizeX = imageLoader.GetImage("Floor").getWidth();
        tileSizeY = imageLoader.GetImage("Floor").getHeight();

        actorSizeX = imageLoader.GetImage("Player").getWidth();
        actorSizeY = imageLoader.GetImage("Player").getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Position camPosition = new Position(level.GetPlayer().GetPosition().x,level.GetPlayer().GetPosition().y);
        Position isoCamPosition = TwoDToIso(camPosition);
        offsetX = windowWidth - isoCamPosition.x;
        offsetY = windowHeight - isoCamPosition.y;

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
                    case PILLAR:
                        drawnImage = imageLoader.GetImage("Pillar");
                        break;
                    case CHASM:
                        drawnImage = imageLoader.GetImage("Chasm");
                        break;
                    case PATH:
                        drawnImage = imageLoader.GetImage("Path");
                        break;
                    case GRASS:
                        drawnImage = imageLoader.GetImage("Grass");
                        break;
                    case TREE:
                        drawnImage = imageLoader.GetImage("Tree");
                        break;
                    default:
                        drawnImage = imageLoader.GetImage("Chasm");
                        break;
                }
                Position isoPos = TwoDToIso(new Position(i, j));
                int tileOffset = 0;

                if (drawnImage.getHeight() > tileSizeY) {
                    tileOffset = 32;
                }

                g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - tileOffset + offsetY, null);

                Item itemInTile = level.GetItemInTile(i, j);
                if (itemInTile != null) {
                    drawnImage = imageLoader.GetImage(itemInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - 16 + offsetY, null);
                }

                Actor actorInTile = level.GetActorInTile(i, j);
                if (actorInTile != null) {
                    drawnImage = imageLoader.GetImage(actorInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - 16 + offsetY, null);
                }

            }
        }
    }

    //Rikki
    public Position IsoToTwoD(Position position) {
        Position pos = new Position(0, 0);
        pos.x = (2 * (position.y * tileSizeY) + (position.x * tileSizeX)) / 2;
        pos.y = (2 * (position.y * tileSizeY) - (position.x * tileSizeX)) / 2;
        return pos;
    }

    public Position TwoDToIso(Position position) {
        Position pos = new Position(0, 0);
        pos.x = (position.x - position.y) * (tileSizeX / 2);
        pos.y = (position.x + position.y) * (tileSizeY / 2);
        return pos;
    }

}
