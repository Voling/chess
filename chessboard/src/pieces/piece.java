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
		if (checklegal(chessboard.getsquare(startingpoint), chessboard.getsquare(destination))) {
			System.out.println("true!!!");
			System.out.println("AHHHHHH" + chessboard.getsquare(startingpoint).getPiece());
			chessboard.setPiece(destination, chessboard.getsquare(startingpoint).getPiece());
			chessboard.setPiece(startingpoint, new empty());
		}
		else {
			System.out.println("Illegal move");
		}
		chessboard.setboard(chessboard.getboard());
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