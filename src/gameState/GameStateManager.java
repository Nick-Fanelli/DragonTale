package gameState;

import audio.AudioPlayer;
import gameState.states.levels.Level1State;
import gameState.states.MenuState;
import gameState.states.levels.Level2State;
import gameState.states.levels.Level3State;
import main.GamePanel;

import java.awt.*;

public class GameStateManager {

    private Graphics2D g;

    public static int MENU_STATE = 0;
    public static int LEVEL_1_STATE = 1;
    public static int LEVEL_2_STATE = 2;
    public static int LEVEL_3_STATE = 3;

    private GameState activeState;

    public GameStateManager(Graphics2D g) {
        this.g = g;
        setState(MENU_STATE);
    }

    private void disposeState() {
        activeState = null;
    }

    public void setState(int state) {
        AudioPlayer.StopAllSound();
        if(state == MENU_STATE)
            activeState = new MenuState(this);
        if(state == LEVEL_1_STATE)
            activeState = new Level1State(this);
        if(state == LEVEL_2_STATE)
            activeState = new Level2State(this);
        if(state == LEVEL_3_STATE)
            activeState = new Level3State(this);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
    }

    public GameState getActiveState() {
        return activeState;
    }

    public void update() {if(activeState != null) activeState.update();}
    public void draw(Graphics2D g) {if(activeState != null) activeState.draw(g);}
    public void keyPressed(int key) {if(activeState != null) activeState.keyPressed(key);}
    public void keyReleased(int key) {if(activeState != null) activeState.keyReleased(key);}
}
