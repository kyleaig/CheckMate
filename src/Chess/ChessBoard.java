package Chess;

import GameState.ChessGame;
import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ChessBoard {

    private static final String BASE_PATH = "/Chess/";
    private static final int TILE_WIDTH = 11;

    private BufferedImage board;
    private BufferedImage highlight;
    private int offsetX;
    private int offsetY;

    private int highlightX;
    private int highlightY;

    public ChessBoard() {

        try {
            board = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "ChessBoard.png"));
            highlight = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "Highlight.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        offsetX = Game.WIDTH / 2 - (board.getWidth() * ChessGame.SCALE) /  2;
        offsetY = Game.HEIGHT / 2 - (board.getHeight() * ChessGame.SCALE) / 2;

        highlightX = -1;
        highlightY = -1;
    }

    public void draw(Graphics2D g) {
        g.drawImage(
                board,
                offsetX,
                offsetY,
                board.getWidth() * ChessGame.SCALE,
                board.getHeight() * ChessGame.SCALE,
                null);

        if (highlightX != -1 && highlightY != -1) {
            g.drawImage(
                    highlight,
                    offsetX + TILE_WIDTH * ChessGame.SCALE * highlightX,
                    offsetY + TILE_WIDTH * ChessGame.SCALE * highlightY,
                    highlight.getWidth() * ChessGame.SCALE,
                    highlight.getHeight() * ChessGame.SCALE,
                    null);
        }
    }

    public void mouseMoved(MouseEvent e) {
        int xPos = e.getX() - offsetX;
        int yPos = e.getY() - offsetY;

        if (xPos > 0 && yPos > 0) {
            highlightX = xPos / (ChessGame.SCALE * TILE_WIDTH);
            highlightY = yPos / (ChessGame.SCALE * TILE_WIDTH);
            if (highlightY > 7 || highlightX > 7) {
                highlightY = highlightX = -1;
            }
        } else {
            highlightY = highlightX = -1;
        }
    }

    public void click() {
        // TODO: Handle clicking on a chess piece
        // Probably gonna need to wait to actually add chess pieces.

        if (highlightX != -1 && highlightY != -1) {
            System.out.println("Clicked tile  (" + (highlightX+1) + ", " + (highlightY+1) + ")");
        } else {
            System.out.println("Out of bounds");
        }
    }
}
