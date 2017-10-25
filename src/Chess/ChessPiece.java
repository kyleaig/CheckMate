package Chess;

public abstract class ChessPiece {
	/*	1. Piece location (row 1-8, col a-h[1-8])
		2. List of available legal moves
			-- Maybe have a static function in a MoveCalc class
				that can place the moves in an ArrayList<Move>
				for the ChessPiece
		3. Black or white?
			-- isWhite = true/false ?
			-- int color = 0/1 ?
		4. Active or captured
			-- active = true/false
			-- captured = false/true
		5. capturePiece() (only if enemy piece)
		6. move(Move m) (m is from list of available legal moves)
	*/
	
	int location[] = new int[2];
	
	public void move(Move m) {
		
	}
	
	public void capture(ChessPiece p) {
		/* if (p.getColor() != getColor()) {
		 * 	
		 * }
		 */
        // This is Ash's code and it's gonna cause a conflict
	}
}
