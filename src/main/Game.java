package main;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game {

    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");
        GamePanel panel = new GamePanel(window);

        window.setContentPane(panel);

        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                panel.onResize(window.getSize());
            }
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
