package entity.enemy;

import tileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Spider extends Enemy {

    private BufferedImage sprite;

    private double startPosY;

    private double risingSpeed;
    private int waitSeconds;
    private long switchTimer;

    private boolean down;

    public Spider(TileMap tm, int startPosY) {
        super(tm);

        this.startPosY = startPosY;

        moveSpeed = 0.5;
        maxSpeed = 0.5;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        health = maxHealth = 5;
        damage = 2;

        down = false;

        rewardedHealth = 1;
        rewardedFireballPower = 0;
        rewardedXP = 2;

        waitSeconds = 3;

        risingSpeed = 1.25;

        switchTimer = System.nanoTime();

        // Load Sprites
        try {
            sprite = ImageIO.read(Spider.class.getResourceAsStream("/Sprites/Enemies/arachnik.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDown() {
        return down;
    }

    public void update() {
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400) {
                flinching = false;
            }
        }

        long elapsed = (System.nanoTime() - switchTimer) / 1000000;
        if(elapsed >= waitSeconds * 1000) {
            switchPositions();
        }
    }

    private void switchPositions() {
        if(!down) {
            if(falling) {
                dy += fallSpeed;
                if(dy > maxFallSpeed) dy = maxFallSpeed;
            } else {
                dy = 0;
                switchTimer = System.nanoTime();
                down = !down;
            }
        } else {
            if(y > startPosY) {
                dy = -risingSpeed;
            } else {
                dy = 0;
                switchTimer = System.nanoTime();
                down = !down;
            }
        }

    }

    public void draw(Graphics2D g) {
        setMapPosition();

        g.drawImage(
                sprite,
                (int) (x + xmap - width / 2),
                (int) (y + ymap - height / 2),
                null
        );
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public double getStartPosY() {
        return startPosY;
    }

    public double getRisingSpeed() {
        return risingSpeed;
    }

    public int getWaitSeconds() {
        return waitSeconds;
    }

    public long getSwitchTimer() {
        return switchTimer;
    }
}
