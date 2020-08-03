package pieces;

import chessboard.*;

public class Bishop extends Piece {
	public Bishop(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		/* @param target	square of target
		 * @param origin	origin
		 * @return	if move is legal
		 */

		/* old code
		List<String> legalmoves = new ArrayList<String>();
		for (int i = 0; i < chessboard.length; i++) {
			if (board[getCoords()[0]+i][getCoords()[1]+i].getPiece().equals(new empty())) {
				legalmoves.add(String.valueOf(board[getCoords()[0]+i][getCoords()[1]+i].getCoords()[0]) + ", " +
						String.valueOf(board[getCoords()[0]+i][getCoords()[1]+i].getCoords()[1]));
			}
		}
		for (int i = 0; i < Math.abs(); i++) {
			if (board[getCoords()[0]-i][getCoords()[1]-i].getPiece().equals(new empty())) {
				legalmoves.add(String.valueOf(board[getCoords()[0]-i][getCoords()[1]-i].getCoords()[0]) + ", " +
						String.valueOf(board[getCoords()[0]-i][getCoords()[1]-i].getCoords()[1]));
			}
		}
		 */
		System.out.println("checklegal ran " + Utilities.coordstostring(target.getCoords()));
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		String origincolor = origin.getPiece().getColor();
		String targetcolor = target.getPiece().getColor();
		/* @Deprecated
		 * if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == Math.abs(targetx-originx)) { //finds distance between beginning and end and diagonals have equal delta-x and delta-y
			System.out.println("checktrue");
			int emptyintermediates = 0;
			if (originx > targetx) {
				for (int i = originx; i > targetx; i--) {
					System.out.println(originx + " " + targetx);
					System.out.println(Chessboard.getboard()[i][i].getPiece());
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in negative direction
			}
			else {
				for (int i = originx ; i <= targetx; i++) {
					System.out.println("originx is " + i + " targetx is " + targetx);
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.Empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in positive direction
			}
			System.out.println("EMPTYINTERMEDIATES " + emptyintermediates);
			System.out.println("DIST IS " + (Math.abs(targetx-originx)));
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if (!getcolor().equals(target.getPiece().getcolor())) {
					return true;
				} //detect if destination is enemy piece
				//if piece is white detect black if piece is black detect white (create opposite)_
			}
			if (emptyintermediates == Math.abs(targetx-originx)) {
				return true;
			} //meant for empty pieces. if destination is empty piece, 

		}
		return false;
		 */
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
			} //detects if target is enemy piece
		}
		return false;
	}
	public void checkcontrol(Square origin) {
		//checks all squares to test for control
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		for (int a = 0; a < 8; a++) { //test x positive diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] + a)};
			System.out.println("testing has begun " + coordsoftarget[0] + " " + coordsoftarget[1]);
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				System.out.println("bounds broken. " + coordsoftarget[0] + " " + coordsoftarget[1]);
				break;
			}
			System.out.println("THE COORDS OF BISHOP X POSITIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] - a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X NEGATIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x positive diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] - a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X POSITIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] + a)};
			if (coordsoftarget[0] > 7 || coordsoftarget[0] < 0 || coordsoftarget[1] > 7 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X NEGATIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		/* @param target	square of target
		 * @param origin	square of origin
		 * @return	if piece on origin controls the target which is an ally piece
		 * there are empty intermediates between ally pieces where one controls the other. this method determines if there are empty intermediates between the 
		 */
		System.out.println("controlAllyLegal ran " + Utilities.coordstostring(target.getCoords()));
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
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
