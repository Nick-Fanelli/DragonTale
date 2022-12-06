package entity;

import tileMap.TileMap;

import java.awt.*;

public class EndOfLevelFlag extends MapObject {

    public EndOfLevelFlag(TileMap tm) {
        super(tm);

        width = 32;
        height = 32;
        cwidth = 20;
        cheight = 20;
    }

    @Override
    public void draw(Graphics2D g) {
        setMapPosition();
        super.draw(g);

    }
}
