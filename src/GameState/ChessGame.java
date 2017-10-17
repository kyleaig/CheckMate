package GameState;

import java.awt.*;

public class ChessGame extends GameState {

    private GameStateManager gameStateManager;

    public ChessGame(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void init() {
        System.out.println("Inside Chess Game!");
    }

    @Override
    public void update() {
        System.out.println("Updating Chess Game! \t " + settings.getNumPlayers());
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
