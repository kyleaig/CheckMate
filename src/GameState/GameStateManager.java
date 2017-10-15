package GameState;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENU_STATE = 0;
    public static final int CHESS_STATE = 1;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = 0;
    }

    public void setState(int state) {
    }
}
