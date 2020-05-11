package pieces;
import chessboard.*;

public class king extends piece {
	boolean incheck = false;
	public king(String color) {
		super(color);
	}
	public boolean checklegal(square origin, square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(originx-targetx) == 1 || Math.abs(originy-targety) == 1) {
			return true;
		}
		return false;
	}
	public void setcheck(boolean check) {
		incheck = check;
	}
}
