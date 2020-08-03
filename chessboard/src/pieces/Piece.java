package pieces;
import java.util.*;
import chessboard.*;

public abstract class Piece {
	public abstract boolean checklegal(Square origin, Square target);
	public abstract void checkcontrol(Square origin);
	public abstract boolean controlAllyLegal(Square origin, Square target);
	//public abstract boolean capturelegal(Square origin, Square target);
	private String color = null;
	private boolean hasMoved = false;
	public Piece(String color) {
		this.color = color; 
	}
	public String getColor() {
		return color;
	}
	//int[] coordinates = getCoords();
	public void move(Square origin, Square target) {
		System.out.println("move executed");
		if (checklegal(origin, target)) {
			origin.getPiece().setHasMoved(true);
			Chessboard.setPiece(target.getCoords(), origin.getPiece());
			target.getPiece().setHasMoved(true);
			Chessboard.setPiece(origin.getCoords(), new Empty());
		}
		else {
			System.out.println("Illegal move");
		}
	}
	public String toString() {
		return getClass().getName();
	}
	public void setHasMoved(boolean hasmoved) {
		hasMoved = hasmoved;
	}
	public boolean getHasMoved() {
		return hasMoved;
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