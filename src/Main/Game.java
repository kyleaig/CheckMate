package Main;

import GameState.GameStateManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private boolean running;
    private int FPS = 60;
    private long drawIntervalInMilli = 1000 / FPS;

    private Thread thread;
    private GameStateManager gameStateManager;
    private BufferedImage screen;
    private Graphics2D graphics;

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    /**
     * Called automatically by the toolkit.
     * Makes this Component displayable by connecting it to a native screen resource.
     */
    public void addNotify() {
        super.addNotify();

        // Here we want to set up out interfaces
        if(thread == null) {

            // This sets up the key listener
            addKeyListener(this);

            // This actually starts the run method on the thread.
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * This method holds the primary game loop.
     */
    public void run() {
        init();

        long begin;

        // **** MAIN GAME LOOP **** //
        while (running) {

            begin = System.currentTimeMillis();

            update();
            draw(graphics);
            drawJPanel();

            sleep(begin);

        }
    }

    /**
     * Calculates how long the game loop took to execute (long elapsed) and then finds how much time is left to
     * to maintain a constant interval between loops (long difference). The function then sleeps for that many
     * milliseconds.
     *
     * @param begin The time the current game loop iteration started, in milliseconds
     */
    private void sleep(long begin){
        long elapsed = System.currentTimeMillis() - begin;
        long difference = drawIntervalInMilli - elapsed;
        if(difference > 0) {
            try {
                Thread.sleep(difference);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        running = true;
        gameStateManager = new GameStateManager();
        screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) screen.getGraphics();
    }

    private void update(){
        gameStateManager.update();
    }

    private void draw(Graphics2D graphics) {
        gameStateManager.draw(graphics);
    }

    private void drawJPanel() {
        Graphics jPanelGraphics = getGraphics();
        jPanelGraphics.drawImage(screen, 0, 0, WIDTH, HEIGHT, null);
        jPanelGraphics.dispose();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        gameStateManager.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        gameStateManager.keyReleased(e.getKeyCode());
    }
}