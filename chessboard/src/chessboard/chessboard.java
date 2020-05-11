package chessboard;
import java.util.*;

import pieces.*;
public class chessboard {
	public static square[][] board = new square[8][8];
	public static String[] whitecontrols;
	public static String[] blackcontrols;
	public chessboard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int[] abc = {i, j};
				board[i][j] = new square(abc);
			}
		}
		setboard(createstartingpos());
	}
	public square[][] createstartingpos() {
		//white pieces
		board[0][0].setPiece(new rook("w"));
		board[1][0].setPiece(new knight("w"));
		board[2][0].setPiece(new bishop("w"));
		board[3][0].setPiece(new queen("w"));
		board[4][0].setPiece(new king("w"));
		board[5][0].setPiece(new bishop("w"));
		board[6][0].setPiece(new knight("w"));
		board[7][0].setPiece(new rook("w"));
		//black pieces
		board[0][7].setPiece(new rook("b"));
		board[1][7].setPiece(new knight("b"));
		board[2][7].setPiece(new bishop("b"));
		board[3][7].setPiece(new queen("b"));
		board[4][7].setPiece(new king("b"));
		board[5][7].setPiece(new bishop("b"));
		board[6][7].setPiece(new knight("b"));
		board[7][7].setPiece(new rook("b"));
		//both color pawns
		for (int i = 0; i < board.length; i++) {
			board[i][1].setPiece(new pawn("w"));
			board[i][6].setPiece(new pawn("b"));
		}
		
		return board;
	}
	public piece getPieceOn(String query) {
		List<String> abc = utilities.interpretmove(query);
		return board[Integer.parseInt(abc.get(0))-1][Integer.parseInt(abc.get(1))-1].getPiece();
	} //retrieve piece on coordinate
	public static void setPiece(int[] coordinates, piece piece) {
		board[coordinates[0]][coordinates[1]].setPiece(piece);
	} //set piece on coordinate
	public static square getsquare(int[] coords) {
		return board[coords[0]][coords[1]];
	} //retrieve square on coordinates
	public static square[][] getboard() {
		return board;
	} //return board
	public static void setboard(square[][] abc) {
		board = abc;
	} //set board
}
