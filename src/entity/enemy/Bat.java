package entity.enemy;

import entity.Animation;
import tileMap.TileMap;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bat extends Enemy {

    private int startingPosX;
    private int travelDistance;

    private BufferedImage[] sprites;

    public Bat(TileMap tm, int startingPosX, int travelDistance) {
        super(tm);

        this.startingPosX = startingPosX;
        this.travelDistance = travelDistance;

        maxSpeed = moveSpeed = 0.7;
        maxFallSpeed = fallSpeed = 0;

        width = 32;
        height = 32;
        cwidth = 20;
        cheight = 20;

        rewardedHealth = 1;
        rewardedFireballPower = 1;

        maxHealth = health = 2;
        damage = 2;

        BufferedImage spritesheet = ImageUtils.loadImage("/Sprites/Enemies/32x32-bat-sprite.png");

        sprites = new BufferedImage[4];

        for(int i = 0; i < sprites.length; i++) {
            sprites[i] = spritesheet.getSubimage(i * width, 1, width, height);
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right = true;
        facingRight = true;
    }

    private void getNextPosition() {
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed) dx = -maxSpeed;
        } else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed) dx = maxSpeed;
        }
    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400) {
                flinching = false;
            }
        }

        if(right && x >= startingPosX + travelDistance) {
            right = false;
            left = true;
            facingRight = false;
        } else if(left && x <= startingPosX) {
            left = false;
            right = true;
            facingRight = true;
        }

        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition();

        super.draw(g);
    }


}
