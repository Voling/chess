package pieces;
import chessboard.*;

public class rook extends piece {
	public rook(String color) {
		super(color);
	}
	public boolean checklegal(square origin, square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(targetx - originx) > 0 && Math.abs(targety-originy) == 0) { //finds moves in x directions

			System.out.println("checktrue");
			int emptyintermediates = 0;
			for (int i = originx; i <= targetx; i++) {
				if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			for (int i = originx; i >= targetx; i--) {
				System.out.println(originx + " " + targetx);
				System.out.println(chessboard.getboard()[originx][i].getPiece());
				if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			} //find empty intermediates between origin and destination in negative direction
			if (emptyintermediates == Math.abs(targetx-originx)) {
				return true;
			} 
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if (!this.getcolor().equals(chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				} //detect if destination is enemy piece
			}
		} 
		if (Math.abs(targety-originy) > 0 && Math.abs(targetx-originx) == 0) { //finds moves in y directions
			System.out.println("checktrue");
			int emptyintermediates = 0;

			for (int i = originy; i <= targety; i++) {
				if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			for (int i = originy; i >= targety; i--) {
				System.out.println(originy + " " + targety);
				System.out.println(chessboard.getboard()[i][originy].getPiece());
				if (chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.empty")) {
					emptyintermediates++;
				}
			}
			if (emptyintermediates == Math.abs(targetx-originx)) {
				if ("pieces.empty".equals(chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				}
				return true;
			}
			if (emptyintermediates == Math.abs(targety-originy)) {
				if (!this.getcolor().equals(chessboard.getboard()[targetx][targety].getPiece().getcolor())) {
					return true;
				} //detect if destination is enemy piece
			}
		}
		return false;
	}
}
