package pieces;
import chessboard.*;

public class Knight extends Piece {
	public Knight(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
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
	public void checkcontrol(int[] coords) {
		Piece subject = Chessboard.getPieceOn(Utilities.coordstostring(coords));
		int coordsofsubject[] = {Integer.parseInt(Utilities.literalcoordstoString(coords).substring(0, 1)), Integer.parseInt(Utilities.literalcoordstoString(coords).substring(1))};
		int knightmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1] + 2}, {coordsofsubject[0] + 2, coordsofsubject[1] + 1}, 
				{coordsofsubject[0] + 2, coordsofsubject[1] -1}, {coordsofsubject[0] + 1, coordsofsubject[1] - 2}, {coordsofsubject[0] - 1, coordsofsubject[1] - 2}, 
				{coordsofsubject[0] - 2, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 2, coordsofsubject[1] + 1}, {coordsofsubject[0] - 1, coordsofsubject[1] + 2}};
		for (int[] knightmove: knightmoves) {
			Chessboard.addcontrol(subject, coordsofsubject, knightmove);
		}
	}
}
