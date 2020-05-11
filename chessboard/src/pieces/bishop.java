package pieces;
import java.util.ArrayList;
import java.util.List;

import chessboard.*;

public class bishop extends piece {
	public bishop(String color) {
		super(color);
	}
	public boolean checklegal(square origin, square target) {
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
					if (chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in positive direction
				for (int i = originx; i >= targetx; i--) {
					System.out.println(originx + " " + targetx);
					System.out.println(chessboard.getboard()[i][i].getPiece());
					if (chessboard.getboard()[i][i].getPiece().toString().equals("pieces.empty")) {
						emptyintermediates++;
					}
				} //finds empty intermediates going in negative direction
				if (emptyintermediates == Math.abs(targetx-originx)) {
					return true;
				} //meant for empty pieces. if destination is empty piece, 
				if (emptyintermediates + 1 == Math.abs(targetx-originx)) {
					if (!this.getcolor().equals(chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
						return true;
					} //detect if destination is enemy piece
				}
			}
		}
		return false;
	}
}
