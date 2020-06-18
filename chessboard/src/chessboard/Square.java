package chessboard;
import pieces.*;

public class Square {
	Piece occupying;
	int[] coordinates;
	public Square() {
		occupying = new Empty();
	}
	public Square(int[] coordinates) {
		occupying = new Empty();
		this.coordinates = coordinates;
	}
	public Square(Piece occupying) {
		this.occupying = occupying;
	}
	
	public void setPiece(Piece piece) {
		this.occupying = piece;
	}
	public Piece getPiece() {
		return occupying;
	}
	public int[] getCoords() {
		return coordinates;
	}
	public void setCoords(int[] abc) {
		coordinates = abc;
	}
}
