package GameState;

public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected Settings settings;

    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public void setSettings(Settings s) {
        settings = s;
    }
}
