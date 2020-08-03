package pieces;
import chessboard.*;

public class Rook extends Piece {
	public Rook(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds moves in x directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					System.out.println(i + " " + targetx);
					System.out.println(Chessboard.getSquare(originx-i, originy).getPiece());
					if (Chessboard.getSquare(originx-i, originy).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //find empty intermediates between origin and destination in negative direction
				System.out.println("checks ended" + emptyintermediates + " " + Math.abs(targetx-originx));
			}
			else {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					if (Chessboard.getSquare(originx+i, originy).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if (!this.getColor().equals(Chessboard.getSquare(targetx, targety).getPiece().getColor())) {
					return true;
				} //detect if destination is enemy piece
			}
			if (emptyintermediates == Math.abs(targetx-originx)) {
				return true;
			}
		} 
		if (Math.abs(targety-originy) > 0 && Math.abs(targetx-originx) == 0) { //finds intermediates in y directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originy > targety) {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					System.out.println(originy + " " + targety);
					System.out.println(Chessboard.getSquare(originx, originy-i).getPiece());
					if (Chessboard.getSquare(originx, originy-i).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			else {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					if (Chessboard.getSquare(originx, originy+i).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates == Math.abs(targety-originy)) {
				if (!this.getColor().equals(Chessboard.getSquare(targetx, targety).getPiece().getColor())) {
					return true;
				} //detect if destination is enemy piece
			}
			if (emptyintermediates == Math.abs(targetx-originx)) {
				return true;
			}
		}
		return false;
	}
	public void checkcontrol(Square origin) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		for (int x = 0; x < 8; x++) { //test x directions
			int coordsoftarget[] = {x, coordsofsubject[1]};
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int y = 0; y < 8; y++) { //test y directions
			int coordsoftarget[] = {coordsofsubject[0], y};
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		//
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds intermediates in x directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					System.out.println(i + " " + targetx);
					if (Chessboard.getSquare(originx-i, originy).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //find empty intermediates between origin and destination in negative direction
				System.out.println("checks ended" + emptyintermediates + " " + Math.abs(targetx-originx));
			}
			else {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					if (Chessboard.getSquare(originx+i, originy).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates + 1 == Math.abs(targetx-originx) && origin.getPiece().getColor().equals(target.getPiece().getColor())) {
				return true;
				} //detects if amount of empty intermediates between x colored rook and x colored piece is equal to distance - 1.
			}
		if (Math.abs(targety-originy) > 0 && Math.abs(targetx-originx) == 0) { //finds intermediates in y directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originy > targety) {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					System.out.println(originy + " " + targety);
					if (Chessboard.getSquare(originx, originy-i).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			else {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					if (Chessboard.getSquare(originx, originy+i).getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates + 1 == Math.abs(targetx-originx) && origin.getPiece().getColor().equals(target.getPiece().getColor())) {
				return true;
				} //detects if amount of empty intermediates between x colored rook and x colored piece is equal to distance - 1.
			}
		return false;
	}
}
