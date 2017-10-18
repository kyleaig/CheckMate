package Main;

import GameState.GameStateManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

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
            addMouseListener(this);
            addMouseMotionListener(this);

            // This actually starts the run method on the thread.
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * This method holds the primary game loop.
     * It runs as a thread which is constructed from this runnable class
     */
    public void run() {
        init();

        long begin;

        // **** MAIN GAME LOOP **** //
        while (running) {

            // Timestamp the beginning of the game loop
            begin = System.currentTimeMillis();


            update();
            draw(graphics);
            drawJPanel();

            // Sleep for a variable amount of time to hold a constant framerate.
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
        // Get time elapsed since the game loop started.
        long elapsed = System.currentTimeMillis() - begin;
        // Get the amount of time left to complete a constant draw interval
        long difference = drawIntervalInMilli - elapsed;
        if(difference > 0) {
            try {
                // Wait for that amount of time.
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
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
    }

    private void update(){
        gameStateManager.update();
    }

    private void draw(Graphics2D graphics) {

        // THIS REFRESHES THE SCREEN
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        gameStateManager.draw(graphics);
    }

    private void drawJPanel() {
        Graphics jPanelGraphics = getGraphics();
        jPanelGraphics.drawImage(screen, 0, 0, WIDTH, HEIGHT, null);
        jPanelGraphics.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        gameStateManager.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameStateManager.keyReleased(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameStateManager.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameStateManager.mouseMoved(e);
    }
}