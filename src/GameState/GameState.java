package GameState;

import java.awt.event.MouseEvent;

public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected Settings settings;

    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
    public void setSettings(Settings s) {
        settings = s;
    }
}
