package pieces;
import java.util.ArrayList;
import java.util.List;

import chessboard.*;

public class Bishop extends Piece {
	public Bishop(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		/* @param target	square of target
		 * @param board		board
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
		System.out.println("checklegal ran");
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(targetx - originx) > 0) { //finds distance between beginning and end (diagonals have equal delta-x and delta-y)
			if (Math.abs(targety-originy) == Math.abs(targetx-originx)) { //detect if destination is from a diagonal of bishop
				System.out.println("checktrue");
				int emptyintermediates = 0;
				for (int i = originx; i <= targetx; i++) {
					i++;
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in positive direction
				for (int i = originx; i > targetx; i--) {
					System.out.println(originx + " " + targetx);
					System.out.println(Chessboard.getboard()[i][i].getPiece());
					if (Chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in negative direction
				System.out.println("EMPTYINTERMEDIATES " + emptyintermediates);
				System.out.println("DIST IS " + (Math.abs(targetx-originx)));
				if (emptyintermediates == Math.abs(targetx-originx)) {
					return true;
				} //meant for empty pieces. if destination is empty piece, 
				if (emptyintermediates == Math.abs(targetx-originx) - 1) {
					if (!getcolor().equals(Chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
						return true;
					} //detect if destination is enemy piece
				}
			}
		}
		return false;
	}
	public void checkcontrol(int[] coords) {
		Piece subject = Chessboard.getPieceOn(Utilities.coordstostring(coords));
		int coordsofsubject[] = {Integer.parseInt(Utilities.literalcoordstoString(coords).substring(0, 1)), Integer.parseInt(Utilities.literalcoordstoString(coords).substring(1))};
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
