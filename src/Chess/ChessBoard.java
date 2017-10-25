package Chess;

import Chess.Pieces.*;
import GameState.ChessGame;
import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ChessBoard {

    private static final String BASE_PATH = "/Chess/";
    private static final int TILE_WIDTH = 11;

    // Chess Board Images
    private BufferedImage boardImage;
    private BufferedImage pieceHighlight;
    private BufferedImage moveHighlight;
    private BufferedImage invalidHighlight;
    private int offsetX;
    private int offsetY;


    private int highlightX;
    private int highlightY;
    private boolean tileSelected;

    // Chess Piece Objects
    private ArrayList<ChessPiece> whitePieces;
    private ArrayList<ChessPiece> blackPieces;
    private ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {

        try {
            boardImage = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "ChessBoard.png"));
            pieceHighlight = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "PieceHighlight.png"));
            moveHighlight = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "SuccessHighlight.png"));
            invalidHighlight = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + "ErrorHighlight.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        offsetX = Game.WIDTH / 2 - (boardImage.getWidth() * ChessGame.SCALE) /  2;
        offsetY = Game.HEIGHT / 2 - (boardImage.getHeight() * ChessGame.SCALE) / 2;

        highlightX = -1;
        highlightY = -1;

        initBoard();
    }

    private void initBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        initPlayer(blackPieces, true);
        initPlayer(whitePieces, false);

    }

    private void initPlayer(ArrayList<ChessPiece> player, boolean top) {
        int pawnRow, kingRow = 0;
        if (top) {
            pawnRow = 1;
            kingRow = 0;
        } else {
            pawnRow = 6;
            kingRow = 7;
        }

        for (int i = 0; i < 8; i++) {
            insertPiece(player, new Pawn(i, pawnRow), i, pawnRow);
        }

        insertPiece(player, new Rook(0, kingRow), 0, kingRow);
        insertPiece(player, new Knight(1, kingRow), 1, kingRow);
        insertPiece(player, new Bishop(2, kingRow), 2, kingRow);
        insertPiece(player, new Queen(3, kingRow), 3, kingRow);
        insertPiece(player, new King(4, kingRow), 4, kingRow);
        insertPiece(player, new Bishop(5, kingRow), 5, kingRow);
        insertPiece(player, new Knight(6, kingRow), 6, kingRow);
        insertPiece(player, new Rook(7, kingRow), 7, kingRow);
    }

    private void insertPiece(ArrayList<ChessPiece> player, ChessPiece piece, int row, int col) {
        board[col][row] = piece;
        player.add(piece);
    }


    public void draw(Graphics2D g) {
        g.drawImage(
                boardImage,
                offsetX,
                offsetY,
                boardImage.getWidth() * ChessGame.SCALE,
                boardImage.getHeight() * ChessGame.SCALE,
                null);

        if (highlightX != -1 && highlightY != -1) {
            g.drawImage(
                    pieceHighlight,
                    offsetX + TILE_WIDTH * ChessGame.SCALE * highlightX,
                    offsetY + TILE_WIDTH * ChessGame.SCALE * highlightY,
                    pieceHighlight.getWidth() * ChessGame.SCALE,
                    pieceHighlight.getHeight() * ChessGame.SCALE,
                    null);
        }

        // Draw all of white's pieces
        for (ChessPiece piece : whitePieces) {
            BufferedImage img = piece.getIcon();
            g.drawImage(
                    img,
                    offsetX + piece.getX() * TILE_WIDTH  * ChessGame.SCALE,
                    offsetY + piece.getY() * TILE_WIDTH  * ChessGame.SCALE,
                    img.getWidth() * ChessGame.SCALE,
                    img.getHeight() * ChessGame.SCALE,
                    null);
        }

        // Draw all of black's pieces;
        for (ChessPiece piece : blackPieces) {
            BufferedImage img = piece.getIcon();
            g.drawImage(
                    img,
                    offsetX + piece.getX() * TILE_WIDTH  * ChessGame.SCALE,
                    offsetY + piece.getY() * TILE_WIDTH  * ChessGame.SCALE,
                    img.getWidth() * ChessGame.SCALE,
                    img.getHeight() * ChessGame.SCALE,
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
