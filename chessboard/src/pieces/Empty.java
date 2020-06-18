package pieces;
import chessboard.*;

public class Empty extends Piece {
	public Empty() {
		super(null);
	}
	public boolean checklegal(Square origin, Square target) {
		return false;
	}
	public String getcolor() {
		return color;
	}
	public void checkcontrol(int[] coords) {
		//System.out.println("no present piece");
	}
}
