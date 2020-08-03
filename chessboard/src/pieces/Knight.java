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
			if (!origin.getPiece().getColor().equals(target.getPiece().getColor())) {
				return true;
			}
		}
		if ((Math.abs(targetx-originx) == 1) && (Math.abs(targety-originy) == 2)) {
			if (!origin.getPiece().getColor().equals(target.getPiece().getColor())) {
				return true;
			}
		}
		return false;
	}
	public void checkcontrol(Square origin) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		int knightmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1] + 2}, {coordsofsubject[0] + 2, coordsofsubject[1] + 1}, 
				{coordsofsubject[0] + 2, coordsofsubject[1] -1}, {coordsofsubject[0] + 1, coordsofsubject[1] - 2}, {coordsofsubject[0] - 1, coordsofsubject[1] - 2}, 
				{coordsofsubject[0] - 2, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 2, coordsofsubject[1] + 1}, {coordsofsubject[0] - 1, coordsofsubject[1] + 2}};
		for (int[] knightmove: knightmoves) {
			if (knightmove[0] < 8 && knightmove[0] > 0 && knightmove[1] < 8 && knightmove[1] > 0) {
				Chessboard.addcontrol(subject, coordsofsubject, knightmove);
			}
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		int knightmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1] + 2}, {coordsofsubject[0] + 2, coordsofsubject[1] + 1}, 
				{coordsofsubject[0] + 2, coordsofsubject[1] -1}, {coordsofsubject[0] + 1, coordsofsubject[1] - 2}, {coordsofsubject[0] - 1, coordsofsubject[1] - 2}, 
				{coordsofsubject[0] - 2, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 2, coordsofsubject[1] + 1}, {coordsofsubject[0] - 1, coordsofsubject[1] + 2}};
		for (int[] knightmove: knightmoves) {
			if (knightmove[0] < 8 && knightmove[0] >= 0 && knightmove[1] < 8 && knightmove[1] >= 0 && target.getCoords().equals(knightmove) && target.getPiece().getColor().equals(subject.getColor())) {
				return true;
			}
		}
		return false;
	}
}
