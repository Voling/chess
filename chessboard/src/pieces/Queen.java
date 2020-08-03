package pieces;

import chessboard.*;

public class Queen extends Piece {
	public Queen(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		String origincolor = origin.getPiece().getColor();
		String targetcolor = origin.getPiece().getColor();
		//diagonal movement
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == Math.abs(targetx-originx)) { //finds distance between beginning and end and diagonals have equal delta-x and delta-y
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				if (originy > targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx-i, originy-i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is above target. y must decrease
				if (originy < targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx-i, originy+i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is below target. y must increase
			} //origin is ahead of target x-wise. x must decrease
			if (originx < targetx) {
				System.out.println("targetx greater than originx");
				if (originy > targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx+i, originy-i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is above target. y must decrease
				if (originy < targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx+i, originy+i};
						System.out.println("Checking " + Utilities.coordstostring(abc));
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is below target. y must increase
			} //origin is behind target x-wise. x must increase
			System.out.println("EMPTYINTERMEDIATES " + emptyintermediates);
			System.out.println("DIST IS " + (Math.abs(targetx-originx)));
			if (emptyintermediates == (Math.abs(originx-targetx))) {
				return true;
			} //detects if amount of empty intermediates between origin and destination is equal to distance.
			if (emptyintermediates + 1 == (Math.abs(originx-targetx)) && Utilities.coloropposite(origincolor).equals(targetcolor)) {
				return true;
			} //detects if target is enemy piece and emptyintermediates is equal to distance -1.
		}
		//horizontal, vertical movement
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds moves in x directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					System.out.println(i + " " + targetx);
					System.out.println(Chessboard.getboard()[originx-i][originy].getPiece());
					if (Chessboard.getboard()[originx-i][originy].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //find empty intermediates between origin and destination in negative direction
				System.out.println("checks ended" + emptyintermediates + " " + Math.abs(targetx-originx));
			}
			else {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					if (Chessboard.getboard()[originx+i][originy].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if (!this.getColor().equals(Chessboard.getboard()[targetx][targety].getPiece().getColor())) {
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
					System.out.println(Chessboard.getboard()[originx][originy-i].getPiece());
					if (Chessboard.getboard()[originx][originy-i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			else {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					if (Chessboard.getboard()[originx][originy+i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates == Math.abs(targety-originy)) {
				if (!this.getColor().equals(Chessboard.getboard()[targetx][targety].getPiece().getColor())) {
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
		//test horizontals and verticals
		for (int x = 0; x < 8; x++) { //test x directions
			int coordsoftarget[] = {x, coordsofsubject[1]};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int y = 0; y < 8; y++) { //test y directions
			int coordsoftarget[] = {coordsofsubject[0], y};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		//test diagonals
		for (int a = 0; a < 8; a++) { //test x positive diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] + a)};

			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF QUEEN X POSITIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] - a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF QUEEN X NEGATIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x positive diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] - a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF QUEEN X POSITIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] + a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF QUEEN X NEGATIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		System.out.println("controlAllyLegal ran " + Utilities.coordstostring(target.getCoords()));
		//horizontal, vertical movement controlAllyLegal
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds intermediates in x directions
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					System.out.println(i + " " + targetx);
					System.out.println(Chessboard.getboard()[originx-i][originy].getPiece());
					if (Chessboard.getboard()[originx-i][originy].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //find empty intermediates between origin and destination in negative direction
				System.out.println("checks ended" + emptyintermediates + " " + Math.abs(targetx-originx));
			}
			else {
				for (int i = 0; i <= Math.abs(originx-targetx); i++) {
					if (Chessboard.getboard()[originx+i][originy].getPiece().toString().equals("pieces.Empty")) {
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
					System.out.println(Chessboard.getboard()[originx][originy-i].getPiece());
					if (Chessboard.getboard()[originx][originy-i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			else {
				for (int i = 0; i <= Math.abs(originy-targety); i++) {
					if (Chessboard.getboard()[originx][originy+i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				}
			}
			System.out.println("empty intermediates at " + emptyintermediates + " distance is " + Math.abs(targetx-originx));
			if (emptyintermediates + 1 == Math.abs(targetx-originx) && origin.getPiece().getColor().equals(target.getPiece().getColor())) {
				return true;
			} //detects if amount of empty intermediates between x colored rook and x colored piece is equal to distance - 1.
		}
		//diagonal movement controlAllyLegal
		String origincolor = origin.getPiece().getColor();
		String targetcolor = target.getPiece().getColor();
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == Math.abs(targetx-originx) && origincolor.equals(targetcolor)) { //finds distance between beginning and end and diagonals have equal delta-x and delta-y
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				if (originy > targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx-i, originy-i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is above target. y must decrease
				if (originy < targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx-i, originy+i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is below target. y must increase
			} //origin is ahead of target x-wise. x must decrease
			if (originx < targetx) {
				if (originy > targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx+i, originy-i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is above target. y must decrease
				if (originy < targety) {
					for (int i = 0; i <= Math.abs(originx-targetx); i++) {
						int abc[] = {originx+i, originy+i};
						if (Chessboard.getSquare(abc).getPiece().toString().equals("pieces.Empty")) {
							emptyintermediates++;
						}
					}
				} //origin is below target. y must increase
			} //origin is behind target x-wise. x must increase
			System.out.println("EMPTYINTERMEDIATES " + emptyintermediates);
			System.out.println("DIST IS " + (Math.abs(targetx-originx)));
			if (emptyintermediates + 1 == (Math.abs(originx-targetx))) {
				return true;
			} //detects if amount of empty intermediates between x colored bishop and x colored piece is equal to distance - 1.
		}
		return false;
	}
}

