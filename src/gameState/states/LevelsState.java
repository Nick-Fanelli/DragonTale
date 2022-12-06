package gameState.states;

import gameState.GameState;
import gameState.GameStateManager;
import tileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelsState extends GameState {

    private final Background bg;

    private int currentChoice = 0;
    private final String[] options = {
            "Level 1",
            "Level 2",
            "Level 3"
    };

    private final Color titleColor;
    private final Font titleFont;

    private final Font font;

    public LevelsState(GameStateManager gsm) {
        super(gsm);

        bg = new Background("/Backgrounds/menubg.gif", 1);
        bg.setVector(-2, 0);

        titleColor = new Color(0, 100, 128);
        titleFont = new Font("Arial", Font.PLAIN, 28);
        font = new Font("Arial", Font.PLAIN, 12);
    }

    @Override public void init() {}

    @Override
    public void update(float deltaTime) {
        bg.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {

        // Draw the Background
        bg.draw(g);

        // Draw the title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dragon Tale", 134, 70);

        // Draw the menu options
        g.setFont(font);
        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 199, 140 + i * 15);
        }
    }

    private void select() {
        switch (currentChoice) {
            case 0:
                gsm.setState(GameStateManager.LEVEL_1_STATE);
                break;
            case 1:
                gsm.setState(GameStateManager.LEVEL_2_STATE);
                break;
            case 2:
                gsm.setState(GameStateManager.LEVEL_3_STATE);
                break;
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER) {
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {}
}
