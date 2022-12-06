package gameState.states.levels;

import entity.HUD;
import entity.Player;
import entity.enemy.Spider;
import entity.enemy.Slugger;
import gameState.GameStateManager;
import tileMap.Background;
import tileMap.TileMap;

import java.awt.*;

public class Level2State extends Level {

    public Level2State(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        super.init();
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/cavetileset.png");
        tileMap.loadMap("/Maps/level2-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new Player(tileMap);
        player.setPosition(50, 200);

        hud = new HUD(player);

        bg = new Background("/Backgrounds/cave-background.png", 0.1);

        populateEnemies();
    }

    @Override
    protected void populatePowerups() {

    }

    @Override
    protected void populateEnemies() {
        Point[] sluggers = new Point[] {
                new Point(1364, 200),
                new Point(1514, 200)
        };

        Point[] arachniks = new Point[] {
                new Point(137, 40),
                new Point(655, 40),
                new Point(675, 40),
                new Point(695, 40),
                new Point(907, 40),
                new Point(927, 40),
                new Point(947, 40),
                new Point(967, 40),
                new Point(987, 40),
                new Point(1007, 40),
                new Point(1027, 40),
                new Point(1047, 40),
                new Point(1067, 40),
        };

        for(int i = 0; i < sluggers.length; i++) {
            Slugger s = new Slugger(tileMap);
            s.setPosition(sluggers[i].x, sluggers[i].y);
            enemies.add(s);
        }

        for(int i = 0; i < arachniks.length; i++) {
            Spider a = new Spider(tileMap, arachniks[i].y);
            a.setPosition(arachniks[i].x, arachniks[i].y);
            enemies.add(a);
        }
    }

    @Override
    protected void reset() {
        backgroundMusic.stop();
        gsm.setState(GameStateManager.LEVEL_2_STATE);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(gsm.getActiveState() == this && !backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        super.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        super.keyReleased(k);
    }
}
