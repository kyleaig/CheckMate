package Chess;

import GameState.ChessGame;
import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ChessBoard {

    private static final String chessBoardImagePath = "/Chess/ChessBoard.png";

    private BufferedImage board;
    private int offsetX;
    private int offsetY;

    private int highlightX;
    private int highlightY;



    public ChessBoard() {


        try {

            board = ImageIO.read(getClass().getResourceAsStream(chessBoardImagePath));

        } catch (Exception e) {

            e.printStackTrace();

        }

        offsetX = Game.WIDTH / 2;
        offsetY = Game.HEIGHT / 5;

        highlightX = -1;
        highlightY = -1;

    }

    public void draw(Graphics2D g) {

        g.drawImage(board, offsetX, offsetY, board.getWidth() * ChessGame.SCALE, board.getHeight() * ChessGame.SCALE,null);


    }

    public void mouseMoved(MouseEvent e) {
        int xPos = e.getX();
        int yPos = e.getY();

        //System.out.println("X: " + xPos + "\t Y: " + yPos);

        xPos -= offsetX;
        yPos -= offsetY;

        if (xPos > 0 && yPos > 0) {
            highlightX = xPos / 44;
            highlightY = yPos / 44;
        } else {
            highlightY = highlightX = -1;
        }

        if (highlightY > 7 || highlightX > 7) {
            highlightY = highlightX = -1;
        }

        System.out.println("X: " + highlightX + "\t Y: " + highlightY);

    }
}
