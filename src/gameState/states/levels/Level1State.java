package gameState.states.levels;

import entity.HUD;
import entity.Player;
import entity.enemy.Slugger;
import gameState.GameStateManager;
import tileMap.Background;
import tileMap.TileMap;

import java.awt.*;

public class Level1State extends Level {

    public Level1State(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        super.init();
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new Player(tileMap);
        player.setPosition(100, 200);

        hud = new HUD(player);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        populateEnemies();
    }

    @Override
    protected void populatePowerups() {

    }

    @Override
    protected void populateEnemies() {
        Slugger s;
        Point[] sluggers = new Point[] {
                new Point( 860, 200),
                new Point(1525, 200),
                new Point(1680, 200),
                new Point(1800, 200)
        };
        for(int i = 0; i < sluggers.length; i++) {
            s = new Slugger(tileMap);
            s.setPosition(sluggers[i].x, sluggers[i].y);
            enemies.add(s);
        }
    }

    @Override
    protected void reset() {
        backgroundMusic.stop();
        gsm.setState(GameStateManager.LEVEL_1_STATE);
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
