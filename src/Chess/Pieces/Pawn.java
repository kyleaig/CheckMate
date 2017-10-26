package Chess.Pieces;

import Chess.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(int x, int y) {
        super ("Pawn.png", x, y);
    }
    
    // public void promote(ChessPiece p); or maybe this should go in ChessGame.java
}
