package gameState;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float deltaTime);
    public abstract void draw(Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}
