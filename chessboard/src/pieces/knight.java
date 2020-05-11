package pieces;
import chessboard.*;

public class knight extends piece {
	public knight(String color) {
		super(color);
	}
	public boolean checklegal(square origin, square target) {
		System.out.println("checklegal ran");
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if ((Math.abs(targetx-originx) == 2) && Math.abs(targety-originy) == 1) {
			if (!origin.getPiece().getcolor().equals(target.getPiece().getcolor())) {
				return true;
			}
		}
		if ((Math.abs(targetx-originx) == 1) && (Math.abs(targety-originy) == 2)) {
			if (!origin.getPiece().getcolor().equals(target.getPiece().getcolor())) {
				return true;
			}
		}
		return false;
	}
}
