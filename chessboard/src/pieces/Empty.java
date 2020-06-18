package pieces;
import chessboard.*;

public class empty extends piece {
	public empty() {
		super(null);
	}
	public boolean checklegal(square origin, square target) {
		return false;
	}
	public String getcolor() {
		return color;
	}
}
