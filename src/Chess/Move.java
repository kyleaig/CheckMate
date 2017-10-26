package Chess;

public class Move {
	int fromLoc[] = new int[2];
	int toLoc[] = new int[2];
	
	public Move(int[] position) {
		fromLoc = position;
	}
	
	// From Location:
	public void setFromLocX(int x) {
		fromLoc[0] = x;
	}
	
	public void setFromLocY(int y) {
		fromLoc[1] = y;
	}
	
	public void setFromLoc(int x, int y) {
		setFromLocX(x);
		setFromLocY(y);
	}
	
	// To Location:
	public void setToLocX(int x) {
		toLoc[0] = x;
	}
	
	public void setToLocY(int y) {
		toLoc[1] = y;
	}
	
	public void setToLoc(int x, int y) {
		setToLocX(x);
		setToLocY(y);
	}
	
	
}