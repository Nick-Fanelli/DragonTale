package gameState.states.levels;

import audio.AudioPlayer;
import entity.HUD;
import entity.Player;
import entity.enemy.Arachnik;
import entity.enemy.Enemy;
import entity.powerups.PowerUp;
import gameState.GameState;
import gameState.GameStateManager;
import gfx.Explosion;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Level extends GameState {

    protected TileMap tileMap;
    protected Background bg;

    protected Player player;
    protected HUD hud;

    protected ArrayList<Enemy> enemies;
    protected ArrayList<PowerUp> powerUps;
    protected ArrayList<Explosion> explosions;

    protected AudioPlayer backgroundMusic;

    public Level(GameStateManager gsm) {
        super(gsm);
    }

    protected abstract void populateEnemies();
    protected abstract void populatePowerups();
    protected abstract void reset();

    public void init() {
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        explosions = new ArrayList<>();

        backgroundMusic = new AudioPlayer("/Music/level1-1.wav", -10);
        backgroundMusic.play();
    }

    @Override
    public void update() {
        if(player != null) {
            player.update();

//            System.out.printf("Player X: %s, Player Y: %s%n", player.getx(), player.gety());

            player.checkAttack(enemies);
            player.checkPowerUp(powerUps);
            if(player.gety() >= tileMap.getHeight() - 15) {
                reset();
            }
            if(player.gety() <= 0) {
                player.setVector(0, 0);
            }
            if(player.getHealth() <= 0) {
                reset();
            }
        }

        if(tileMap != null && player != null) {
            tileMap.setPosition(GamePanel.width / 2 - player.getx(),
                                GamePanel.height / 2 - player.gety());
        }

        if(bg != null) {
            bg.update();
            bg.setPosition(tileMap.getx(), tileMap.gety());
        }

        for(int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).update();
            if(powerUps.get(i).shouldBeRemoved()) {
                powerUps.remove(powerUps.get(i));
                i--;
            }
        }

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()) {
                enemies.remove(i);
                i--;
                explosions.add(new Explosion(e.getx(), e.gety()));
            }
        }

        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()) {
                explosions.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if(bg != null) bg.draw(g);

        if(tileMap != null) tileMap.draw(g);
        if(player != null) player.draw(g);

        for(int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).draw(g);
        }

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition((int) tileMap.getx(), (int) tileMap.gety());
            explosions.get(i).draw(g);
        }

        if(hud != null) hud.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if(player != null) {
            if(k == KeyEvent.VK_LEFT) player.setLeft(true);
            if(k == KeyEvent.VK_RIGHT) player.setRight(true);
            if(k == KeyEvent.VK_UP) player.setUp(true);
            if(k == KeyEvent.VK_DOWN) player.setDown(true);
            if(k == KeyEvent.VK_UP) player.setJumping(true);
            if(k == KeyEvent.VK_SPACE) player.setGliding(true);
            if(k == KeyEvent.VK_R) player.setScratching();
            if(k == KeyEvent.VK_F) player.setFiring();
        }
    }

    @Override
    public void keyReleased(int k) {
        if(player != null) {
            if(k == KeyEvent.VK_LEFT) player.setLeft(false);
            if(k == KeyEvent.VK_RIGHT) player.setRight(false);
            if(k == KeyEvent.VK_UP) player.setUp(false);
            if(k == KeyEvent.VK_DOWN) player.setDown(false);
            if(k == KeyEvent.VK_UP) player.setJumping(false);
            if(k == KeyEvent.VK_SPACE) player.setGliding(false);
        }
    }
}
