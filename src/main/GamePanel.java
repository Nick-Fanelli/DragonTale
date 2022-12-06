package main;

import gameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int width = 428;
    public static int height = 240;
    public static int SCALE = 2;

    public static final int FPS = 60;
    public static final double UPDATE_CAP = 1.0 / FPS;

    private Thread thread;
    private boolean isRunning;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    private final JFrame frame;

    public GamePanel(JFrame frame) {
        this.frame = frame;

        setPreferredSize(new Dimension(width * SCALE, height * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void onResize(Dimension size) {
        setSize(size);
    }

    public void addNotify() {
        super.addNotify();

        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        gsm = new GameStateManager(g);

        isRunning = true;
    }

    public void run() {

        init();

        boolean shouldDraw;

        double currentUpdateTime;
        double lastUpdateTime = System.nanoTime() / 1000000000.0;
        double deltaTime = 0;

        double firstTime, passedTime;
        double lastTime = System.nanoTime() / 1000000000.0;
        double updateTime = 0;

        while(isRunning) {

            shouldDraw = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            updateTime += passedTime;

            while(updateTime >= UPDATE_CAP) {
                updateTime -= UPDATE_CAP;
                shouldDraw = true;

                // Calculate Delta Time
                currentUpdateTime = System.nanoTime() / 1000000000.0;
                deltaTime = currentUpdateTime - lastUpdateTime;
                lastUpdateTime = currentUpdateTime;

                update((float) deltaTime);
            }

            if(shouldDraw) {
                draw();
                drawToScreen();
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private void update(float deltaTime) { gsm.update(deltaTime); }
    private void draw() { gsm.draw(g); }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {gsm.keyPressed(key.getKeyCode());}
    public void keyReleased(KeyEvent key) {gsm.keyReleased(key.getKeyCode());}

}
















