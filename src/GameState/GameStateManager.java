package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENU_STATE = 0;
    public static final int CHESS_STATE = 1;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        gameStates.add(new MainMenu(this));
        gameStates.add(new ChessGame(this));
        setState(0);
    }

    public void setState(int newState) {
        currentState = newState;
        gameStates.get(currentState).init();
    }

    public GameState getState(int state) {
        return gameStates.get(state);
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k ){
        gameStates.get(currentState).keyReleased(k);
    }

    public void mouseClicked(MouseEvent e) {
        // TODO: Pass mouse event parameters to game state
    }
}
