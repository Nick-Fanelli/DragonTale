package entity;

import tileMap.TileMap;

import java.awt.*;

public class WebString extends MapObject {

    public WebString(TileMap tm) {
        super(tm);

        moveSpeed = maxSpeed = 0;
        fallSpeed = maxFallSpeed = 0;

        width = 1;
        height = 0;
        cwidth = 1;
        cheight = 0;
    }

    public void setHeight(int height) {
        this.height = height;
        this.cheight = height;
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        if(height > 0) {
            g.setColor(Color.WHITE);
            g.drawLine((int) (tileMap.getx() + x), (int) (tileMap.gety() + y), (int) (tileMap.getx() + x), (int) (tileMap.gety() + y) + height + 5);
        }
    }
}
