/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangential.ui;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.entities.Actor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Nanofus
 */
public class LevelRenderer extends JPanel {

    private BufferedImage img_floor;
    private BufferedImage img_wall;
    private BufferedImage img_chasm;
    private BufferedImage img_water;

    private BufferedImage img_skeleton;
    private BufferedImage img_lizardMan;
    private BufferedImage img_troll;
    private BufferedImage img_dragon;

    private BufferedImage img_player;

    private int tileSize;

    private Level level;

    private int originX;
    private int originY;

    private int centerX;
    private int centerY;

    LevelRenderer(Level level, int windowWidth, int windowHeight, BufferedImage img_player, BufferedImage img_floor, BufferedImage img_wall, BufferedImage img_chasm, BufferedImage img_water, BufferedImage img_skeleton, BufferedImage img_lizardMan, BufferedImage img_troll, BufferedImage img_dragon) {
        this.level = level;

        this.originX = windowWidth / 2;
        this.originY = windowHeight / 2;

        tileSize = img_floor.getWidth();

        this.img_player = img_player;

        this.img_floor = img_floor;
        this.img_wall = img_wall;
        this.img_chasm = img_chasm;
        this.img_water = img_water;

        this.img_skeleton = img_skeleton;
        this.img_lizardMan = img_lizardMan;
        this.img_troll = img_troll;
        this.img_dragon = img_dragon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < level.GetWidth(); i++) {
            for (int j = 0; j < level.GetHeight(); j++) {
                BufferedImage drawnImage;
                switch (level.GetTile(i, j).GetType()) {
                    case WALL:
                        drawnImage = img_wall;
                        break;
                    case FLOOR:
                        drawnImage = img_floor;
                        break;
                    case WATER:
                        drawnImage = img_water;
                        break;
                    default:
                        drawnImage = img_chasm;
                        break;
                }
                g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);

                for (Actor actor : level.GetActors()) {
                    if (actor.GetPosition().x == i && actor.GetPosition().y == j) {
                        switch (actor.GetName()) {
                            case "Skeleton":
                                drawnImage = img_skeleton;
                                break;
                            case "Lizard Man":
                                drawnImage = img_lizardMan;
                                break;
                            case "Troll":
                                drawnImage = img_troll;
                                break;
                            case "Dragon":
                                drawnImage = img_dragon;
                                break;
                        }
                        g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);
                    }
                }

                if (level.GetPlayer().GetPosition().x == i && level.GetPlayer().GetPosition().y == j) {
                    drawnImage = img_player;
                    g.drawImage(drawnImage, (tileSize * i), (tileSize * j), null);
                }
            }
        }
    }
}
