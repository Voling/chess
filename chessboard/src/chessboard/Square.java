package chessboard;
import pieces.*;

public class square {
	piece occupying;
	int[] coordinates;
	public square() {
		occupying = new empty();
	}
	public square(int[] coordinates) {
		occupying = new empty();
		this.coordinates = coordinates;
	}
	public square(piece occupying) {
		this.occupying = occupying;
	}
	
	public void setPiece(piece piece) {
		this.occupying = piece;
	}
	public piece getPiece() {
		return occupying;
	}
	public int[] getCoords() {
		return coordinates;
	}
	public void setCoords(int[] abc) {
		coordinates = abc;
	}
}
