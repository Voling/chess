package pieces;
import java.util.*;
import chessboard.*;

public abstract class Piece {
	public abstract boolean checklegal(Square origin, Square target);
	public abstract void checkcontrol(int[] abc);
	String color;
	public Piece(String color) {
		this.color = color; 
	}
	public String getcolor() {
		return color;
	}
	//int[] coordinates = getCoords();
	public void move(int[] startingpoint, int[] destination) {
		System.out.println("move executed");
		if (checklegal(Chessboard.getsquare(startingpoint), Chessboard.getsquare(destination))) {
			System.out.println("AHHHHHH" + Chessboard.getsquare(startingpoint).getPiece());
			Chessboard.setPiece(destination, Chessboard.getsquare(startingpoint).getPiece());
			Chessboard.setPiece(startingpoint, new Empty());
		}
		else {
			System.out.println("Illegal move");
		}
		Chessboard.setboard(Chessboard.getboard());
	}
	public boolean capturelegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		Piece originpiece = origin.getPiece();
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