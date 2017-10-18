package GameState;

import Chess.ChessBoard;
import Main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ChessGame extends GameState {

    public static final int SCALE = 4;

    private GameStateManager gameStateManager;
    private ChessBoard chessBoard;

    public ChessGame(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        chessBoard = new ChessBoard();

    }

    @Override
    public void init() {

       System.out.println("Inside Chess Game!");

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
        chessBoard.draw(g);




    }


    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        chessBoard.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Nothing yet
    }


}
