package pieces;
import chessboard.*;

public class Empty extends Piece {
	public Empty() {
		super(null);
	}
	public boolean checklegal(Square origin, Square target) {
		return false;
	}
	public void checkcontrol(Square origin) {
		//System.out.println("no present piece");
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		return false;
	}
}
