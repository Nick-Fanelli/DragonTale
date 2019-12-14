package gameState.states.levels;

import entity.HUD;
import entity.Player;
import entity.enemy.Bat;
import entity.powerups.JumpCrystal;
import gameState.GameStateManager;
import tileMap.Background;
import tileMap.TileMap;

import java.awt.*;

public class Level3State extends Level {

    public Level3State(GameStateManager gsm) {
        super(gsm);
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new Player(tileMap);
        player.setPosition(100, 200);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        populateEnemies();
        populatePowerups();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void populateEnemies() {
        Bat bat = new Bat(tileMap, 100, 100);
        bat.setPosition(100, 200);
        enemies.add(bat);
    }

    @Override
    protected void populatePowerups() {
//        JumpCrystal jumpCrystal = new JumpCrystal(tileMap);
//        jumpCrystal.setPosition(200, 200);
//
//        powerUps.add(jumpCrystal);
    }

    @Override
    protected void reset() {
        gsm.setState(GameStateManager.LEVEL_3_STATE);
    }

    @Override
    public void update() {
        super.update();
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
