package pieces;
import chessboard.*;

public abstract class piece {
	public abstract boolean checklegal(square origin, square target);
	String color;
	public piece(String color) {
		this.color = color; 
	}
	public String getcolor() {
		return color;
	}
	//int[] coordinates = getCoords();
	public void move(int[] startingpoint, int[] destination) {
		System.out.println("move executed");
		if (checklegal(chessboard.getsquare(startingpoint), chessboard.getsquare(destination))
				&& !chessboard.getsquare(startingpoint).getPiece().getcolor().equals(chessboard.getsquare(destination).getPiece().getcolor())) {
			System.out.println("AHHHHHH" + chessboard.getsquare(startingpoint).getPiece());
			chessboard.setPiece(destination, chessboard.getsquare(startingpoint).getPiece());
			chessboard.setPiece(startingpoint, new empty());
		}
		else {
			System.out.println("Illegal move");
		}
		chessboard.setboard(chessboard.getboard());
	}
	public boolean capturelegal(square origin, square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		piece originpiece = origin.getPiece();
		if ("pieces.pawn".equals(originpiece.toString()) && Math.abs(targety - originy) == 2 || (Math.abs(targety-originy) == 1 && Math.abs(targetx-originx) == 0)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return getClass().getName();
	}
	/*
	public int[] getCoords() {
		return coordinates;
	}
	public void setCoords(int[] abc) {
		coordinates = abc;
	}
	*/
}