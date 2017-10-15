package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private boolean running;

    private int FPS = 30;
    private long drawIntervalInMilli = 1000 / FPS;

    private GameStateManager gameStateManager;

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void run() {
        init();


        // **** MAIN GAME LOOP **** //

        while (running) {

            update();
            draw();

            // We will need some math to sleep for a give amount of time to maintain 30 FPS
        }

    }

    public void init() {
        running = true;
        gameStateManager = new GameStateManager();
    }


    private void update(){

    }

    private void draw() {

    }

    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {

    }


    public void keyReleased(KeyEvent e) {

    }
}