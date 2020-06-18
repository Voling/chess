package pieces;
import chessboard.*;

public class King extends Piece {
	boolean incheck = false;
	public King(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(originx-targetx) == 1 || Math.abs(originy-targety) == 1) {
			return true;
		}
		return false;
	}
	public void checkcontrol(int[] coords) {
		Piece subject = Chessboard.getPieceOn(Utilities.coordstostring(coords));
		int coordsofsubject[] = {Integer.parseInt(Utilities.literalcoordstoString(coords).substring(0, 1)), Integer.parseInt(Utilities.literalcoordstoString(coords).substring(1))};
		int kingmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1]},
				{coordsofsubject[0] + 1, coordsofsubject[1] - 1},
				{coordsofsubject[0], coordsofsubject[1] - 1},
				{coordsofsubject[0] -1, coordsofsubject[1]},
				{coordsofsubject[0] - 1, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 1, coordsofsubject[1] + 1},
				{coordsofsubject[0], coordsofsubject[1] + 1},
				{coordsofsubject[0] + 1, coordsofsubject[1] + 1}};
		for (int[] kingmove: kingmoves) {
			Chessboard.addcontrol(subject, coordsofsubject, kingmove);
		}
	}
}
