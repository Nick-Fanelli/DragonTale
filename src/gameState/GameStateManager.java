package gameState;

import audio.AudioPlayer;
import gameState.states.LevelsState;
import gameState.states.levels.Level1State;
import gameState.states.MenuState;
import gameState.states.levels.Level2State;
import gameState.states.levels.Level3State;
import main.GamePanel;

import java.awt.*;

public class GameStateManager {

    private final Graphics2D g;

    public static int MENU_STATE = -1;
    public static int LEVELS_STATE = 0;
    public static int LEVEL_1_STATE = 1;
    public static int LEVEL_2_STATE = 2;
    public static int LEVEL_3_STATE = 3;

    private GameState activeState;

    public GameStateManager(Graphics2D g) {
        this.g = g;
        setState(LEVELS_STATE);
    }

    public void setState(int state) {
        AudioPlayer.StopAllSound();

        if(state == MENU_STATE)
            activeState = new MenuState(this);
        if(state == LEVELS_STATE)
            activeState = new LevelsState(this);
        if(state == LEVEL_1_STATE)
            activeState = new Level1State(this);
        if(state == LEVEL_2_STATE)
            activeState = new Level2State(this);
        if(state == LEVEL_3_STATE)
            activeState = new Level3State(this);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
    }

    public GameState getActiveState() {
        return activeState;
    }

    public void update(float deltaTime) { if(activeState != null) activeState.update(deltaTime); }
    public void draw(Graphics2D g) { if(activeState != null) activeState.draw(g); }
    public void keyPressed(int key) { if(activeState != null) activeState.keyPressed(key); }
    public void keyReleased(int key) { if(activeState != null) activeState.keyReleased(key); }
}
