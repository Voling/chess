package pieces;
import chessboard.*;

public class Pawn extends Piece {
	public Pawn(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		System.out.println("Coords are:\nTarget: " + targetx + " " + targety + " Origin: " + originx + " " + originy);
		if (Math.abs(targety - originy) == 2) { //finds distance between beginning and end. for doublestep pawn
			if (originy == 1 && getcolor().equals("w")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i <= targety; i++) {
					if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				}
				System.out.println("Pawndist " + emptyintermediates + " " + Math.abs(targety-originy));
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
			if (originy == 6 && getcolor().equals("b")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i >= targety; i--) {
					i--;
					if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				}
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
		}


		if (Math.abs(targety - originy) == 1) { //finds distance between beginning and end. for singlestep pawn
			if (getcolor().equals("w")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i <= targety; i++) {
					if (("pieces.empty".equals(Chessboard.getboard()[originx][i].getPiece().toString()))) {
						emptyintermediates++;
					}
				}
				System.out.println("Pawndist " + emptyintermediates + " " + Math.abs(targety-originy));
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
			if (getcolor().equals("b")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i >= targety; i--) {
					i--;
					if ("pieces.empty".equals(Chessboard.getboard()[originx][i].getPiece().toString())) {
						emptyintermediates++;
					}
				}
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
		}
		if ((Math.abs(targetx-originx) == 1) && !origin.getPiece().getcolor().equals(target.getPiece().getcolor())) {
			return true;
		}
		return false;
	}
	public void checkcontrol(int[] coords) {
		Piece subject = Chessboard.getPieceOn(Utilities.coordstostring(coords));
		int coordsofsubject[] = {Integer.parseInt(Utilities.literalcoordstoString(coords).substring(0, 1)), Integer.parseInt(Utilities.literalcoordstoString(coords).substring(1))};
		int coordsoftarget[] = {coordsofsubject[0]+1, coordsofsubject[1]+1};
		Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		coordsoftarget[0] = coordsofsubject[0]-2;
		Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
	}
}
