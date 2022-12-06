package gameState.states;

import gameState.GameState;
import gameState.GameStateManager;
import tileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm) {
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
                gsm.setState(GameStateManager.LEVELS_STATE);
                break;
            case 1:
                // Help
                break;
            case 2:
                System.exit(0);
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
