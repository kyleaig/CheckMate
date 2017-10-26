package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

public class Pawn extends ChessPiece {

    public Pawn(int x, int y) {
        super ("Pawn.png", x, y);
    }
    
    public void initMoveSet() { // Should be called every player's turn
    	// TODO: We need to agree on row and column direction
    	
    	int[] dest = {getX(), getY()};
    	if (isWhite()) { // White advance 1
    		dest[1] = getY() + 1;
    		legal_moves.add(new Move(getPos(), dest));
    		if (getY() == 1) { // White advance 2
    			dest[1] = getY() + 2;
    			legal_moves.add(new Move(getPos(), dest));
    		}
    	} else { // Black advance 1
    		dest[1] = getY() - 1;
    		legal_moves.add(new Move(getPos(), dest));
    		if (getY() == 6) { // Black advance 2
    			dest[1] = getY() - 2;
    			legal_moves.add(new Move(getPos(), dest));
    		}
    	}
    }
    
    // public void promote(ChessPiece p); or maybe this should go in ChessGame.java
}
