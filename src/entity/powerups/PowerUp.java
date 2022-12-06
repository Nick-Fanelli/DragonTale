package entity.powerups;

import entity.Animation;
import entity.MapObject;
import entity.Player;
import tileMap.TileMap;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp extends MapObject {

    public static final String pathBlueCrystal = "/Tilesets/Crystals/crystal-qubodup-ccby3-32-green.png";
    public static final BufferedImage blueCrystals = ImageUtils.loadImage(pathBlueCrystal);

    protected boolean shouldBeRemoved = false;

    public enum Color {
        GREEN
    }

    public PowerUp(TileMap tm, Color color) {
        super(tm);

        width = 32;
        height = 32;
        cwidth = 20;
        cheight = 20;

        BufferedImage[] sprites = new BufferedImage[8];

        if (color == Color.GREEN) {
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = blueCrystals.getSubimage(i * width, 0, width, height);
            }
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(250);
    }

    public void setShouldBeRemoved(boolean shouldBeRemoved) {
        this.shouldBeRemoved = shouldBeRemoved;
    }

    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }

    @Override
    public void draw(Graphics2D g) {
        setMapPosition();
        super.draw(g);
    }

    public void update() {
        animation.update();
    }

}
