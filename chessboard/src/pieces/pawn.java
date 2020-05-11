package pieces;
import chessboard.*;

public class pawn extends piece {
	public pawn(String color) {
		super(color);
	}
	public boolean checklegal(square origin, square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		System.out.println("Coords are:\nTarget: " + targetx + " " + targety + " Origin: " + originx + " " + originy);
		System.out.println(originy + " " + super.getcolor().equals("w"));
		if (Math.abs(targety - originy) == 2) { //finds distance between beginning and end. for doublestep pawn
			if (originy == 1 && super.getcolor().equals("w")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i <= targety; i++) {
					if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				}
				System.out.println("Pawndist " + emptyintermediates + " " + Math.abs(targety-originy));
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
			if (originy == 6 && super.getcolor().equals("b")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i >= targety; i--) {
					i--;
					if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				}
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
		}
		

		if (Math.abs(targety - originy) == 1) { //finds distance between beginning and end. for singlestep pawn
			if (super.getcolor().equals("w")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i <= targety; i++) {
					if (("pieces.empty".equals(chessboard.getboard()[originx][i].getPiece().toString()))) {
						emptyintermediates++;
					}
				}
				System.out.println("Pawndist " + emptyintermediates + " " + Math.abs(targety-originy));
				if (emptyintermediates == Math.abs(targety-originy)) {
					return true;
				}
			}
			if (super.getcolor().equals("b")) {
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originy; i >= targety; i--) {
					i--;
					if ("pieces.empty".equals(chessboard.getboard()[originx][i].getPiece().toString())) {
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
}
