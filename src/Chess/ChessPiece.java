package Chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ChessPiece {

	/*

		1. Piece location (row 1-8, col a-h[1-8])
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

	private static final String BASE_PATH = "/Chess/Pieces/";

	private BufferedImage icon;
	protected int position[];

	public ChessPiece(String imageName, int x, int y) {

		try {
			icon = ImageIO.read(getClass().getResourceAsStream(BASE_PATH + imageName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		position = new int[2];
		position[0] = x;
		position[1] = y;
	}

	public void move(Move m) {

		// TODO: update position
	}
	
	public void capture(ChessPiece p) {
//		 if (p.getColor() != getColor()) {
//
//		}
	}

	public BufferedImage getIcon() {
		return icon;
	}

	public int getX() {
		return position[0];
	}

	public int getY() {
		return position[1];
	}
}
