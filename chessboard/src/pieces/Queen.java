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
		//diagonal movement
		if (Math.abs(targetx - originx) > 0) { //finds distance between beginning and end (diagonals have equal delta-x and delta-y)
			if (Math.abs(targety-originy) == Math.abs(targetx-originx)) { //detect if destination is from a diagonal of bishop
				System.out.println("checktrue");
				int emptyintermediates = 0;

				for (int i = originx; i <= targetx; i++) {
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					} //finds empty intermediates going in positive direction
				} 
				for (int i = originx; i >= targetx; i--) {
					System.out.println(originx + " " + targetx);
					System.out.println(Chessboard.getboard()[i][i].getPiece());
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in negative direction
				if (emptyintermediates == Math.abs(targetx-originx)) {
					return true;
				} //meant for empty pieces. if destination is empty piece, 
				if (emptyintermediates == Math.abs(targetx-originx)) {
					if (!this.getcolor().equals(Chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
						return true;
					} //detect if destination is enemy piece
				}
			}
		}
		//horizontal, vertical movement
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds moves in x directions

			System.out.println("checktrue");
			int emptyintermediates = 0;

			for (int i = originx; i <= targetx; i++) {
				if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			for (int i = originx; i >= targetx; i--) {
				System.out.println(originx + " " + targetx);
				System.out.println(Chessboard.getboard()[originx][i].getPiece());
				if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			} //find empty intermediates between origin and destination in negative direction
			if (emptyintermediates == Math.abs(targetx-originx)) {
				return true;
			} 
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if (!this.getcolor().equals(Chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				} //detect if destination is enemy piece
			}
		} 
		if (Math.abs(targety-originy) > 0 && Math.abs(targetx-originx) == 0) { //finds moves in y directions
			System.out.println("checktrue");
			int emptyintermediates = 0;

			for (int i = originy; i <= targety; i++) {
				if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			for (int i = originy; i >= targety; i--) {
				System.out.println(originy + " " + targety);
				System.out.println(Chessboard.getboard()[i][originy].getPiece());
				if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if ("pieces.empty".equals(Chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				}
				return true;
			}
			if (emptyintermediates == Math.abs(targety-originy)) {
				if (!this.getcolor().equals(Chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				} //detect if destination is enemy piece
			}
		}
		return false;
	}
	public void checkcontrol(int[] coords) {
		Piece subject = Chessboard.getPieceOn(Utilities.coordstostring(coords));
		int coordsofsubject[] = {Integer.parseInt(Utilities.literalcoordstoString(coords).substring(0, 1)), Integer.parseInt(Utilities.literalcoordstoString(coords).substring(1))};
		//test horizontals and verticals
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
		//test diagonals
		for (int a = 0; a < 8; a++) { //test x positive diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] + a)};
			
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X POSITIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] - a)};
			
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X NEGATIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x positive diagonal downward direction
			int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] - a)};
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X POSITIVE DIAGONAL DOWNWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
		for (int a = 0; a < 8; a++) { //test x negative diagonal upward direction
			int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] + a)};
			if (coordsoftarget[0] > 8 || coordsoftarget[0] < 0 || coordsoftarget[1] > 8 || coordsoftarget[1] < 0) {
				break;
			}
			System.out.println("THE COORDS OF BISHOP X NEGATIVE DIAGONAL UPWARD IS " + Utilities.coordstostring(coordsoftarget));
			Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
		}
	}
}

