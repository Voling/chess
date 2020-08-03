package chessboard;
import java.util.*;
import java.util.stream.*;

import pieces.*;
public class Chessboard {
	public static Square[][] board = new Square[8][8];
	public static List<String> whitecontrols = new ArrayList<String>(64);
	public static List<String> blackcontrols = new ArrayList<String>(64);
	public Chessboard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int[] abc = {i, j};
				board[i][j] = new Square(abc);
			}
		}
		setboard(createstartingpos());
	}
	public Square[][] createstartingpos() {
		//white pieces
		board[0][0].setPiece(new Rook("w"));
		//board[1][0].setPiece(new Knight("w"));
		//board[2][0].setPiece(new Bishop("w"));
		//board[3][0].setPiece(new Queen("w"));
		//board[4][0].setPiece(new King("w"));
		//board[5][0].setPiece(new Bishop("w"));
		//board[6][0].setPiece(new Knight("w"));
		//board[7][0].setPiece(new Rook("w"));
		//black pieces
		//board[0][7].setPiece(new Rook("b"));
		//board[1][7].setPiece(new Knight("b"));
		//board[2][7].setPiece(new Bishop("b"));
		//board[3][7].setPiece(new Queen("b"));
		//board[4][7].setPiece(new King("b"));
		//board[5][7].setPiece(new Bishop("b"));
		//board[6][7].setPiece(new Knight("b"));
		//board[7][7].setPiece(new Rook("b"));
		
		//both color pawns
		for (int i = 0; i < board.length; i++) {
			//board[i][1].setPiece(new Pawn("w"));
			//board[i][6].setPiece(new Pawn("b"));
		}
		 
		return board;
	}
	public void detectcontrol() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				int coords[] = {i, j};
				getPieceOn(Utilities.coordstostring(coords)).checkcontrol(Chessboard.getSquare(coords));
			}
		}
		cleancontrols();
	}
	public static void addcontrol(Piece subject, int coordsofsubject[], int coordsoftarget[]) {
		System.out.println("The following coordinate is being tested: " + Utilities.coordstostring(coordsoftarget) + " by " + subject.getColor() + " piece " + subject.toString());
		
		switch (subject.getColor()) {
		case "w":
			if (!whitecontrols.contains(Utilities.coordstostring(coordsoftarget))) {
				System.out.println("CASE WHITE ACHIEVED: " + Utilities.coordstostring(coordsoftarget));
				if (subject.checklegal(getSquare(coordsofsubject), getSquare(coordsoftarget))) {
					System.out.println("Control added: " + Utilities.coordstostring(coordsoftarget));
					whitecontrols.add(Utilities.coordstostring(coordsoftarget));
				} // assuming no duplicate, add legal squares to control list
				else {
					if (subject.controlAllyLegal(getSquare(coordsofsubject), getSquare(coordsoftarget))) {
						whitecontrols.add(Utilities.coordstostring(coordsoftarget));
					}
				} // if checklegal fails, test for ally instead
			} 
			else {
				System.out.println("Duplicate control coordinate detected");
			}
			break;
		case "b":
			System.out.println("SWITCH B HAS BEEN ACTIVATED " + Utilities.coordstostring(coordsoftarget));
			if (!blackcontrols.contains(Utilities.coordstostring(coordsoftarget))) {
				if (subject.checklegal(getSquare(coordsofsubject), getSquare(coordsoftarget))) {
					System.out.println("CASE BLACK ACHIEVED: " + Utilities.coordstostring(coordsoftarget));
					blackcontrols.add(Utilities.coordstostring(coordsoftarget));
				} // assuming no duplicate, add legal squares to control list
				else {
					if (subject.controlAllyLegal(getSquare(coordsofsubject), getSquare(coordsoftarget))) {
						blackcontrols.add(Utilities.coordstostring(coordsoftarget));
					} // if checklegal fails, test for ally instead
				}
			}
			else {
				System.out.println("Duplicate control coordinate detected");
			}
			break;
		}
		/*if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0 && "w".equals(subject.getcolor())) {
			if (subject.checklegal(getsquare(coordsofsubject), getsquare(coordsoftarget))) {
				whitecontrols.add(Utilities.coordstostring(coordsoftarget));
			} // add legal squares to control list

		}
		if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0 && "b".equals(subject.getcolor())) {
			if (subject.checklegal(getsquare(coordsofsubject), getsquare(coordsoftarget))) {
				blackcontrols.add(Utilities.coordstostring(coordsoftarget));
			} // simply add legal squares to control list
		}
		 */
	}
	public void cleancontrols() {
		whitecontrols = whitecontrols.stream().distinct().collect(Collectors.toList());
		blackcontrols = blackcontrols.stream().distinct().collect(Collectors.toList());
		/*for (int i = 0; i < whitecontrols.size(); i++) {
			char firstchar = whitecontrols.get(i).toCharArray()[0];
			int secondchar = Integer.parseInt(String.valueOf(whitecontrols.get(i).toCharArray()[1]));
			if ( firstchar < 97 || firstchar > 104 || secondchar < 1 || secondchar > 8) {
				whitecontrols.remove(i);
			}
		}
		for (int i = 0; i < blackcontrols.size(); i++) {
			char firstchar = blackcontrols.get(i).toCharArray()[0];
			int secondchar = Integer.parseInt(String.valueOf(blackcontrols.get(i).toCharArray()[1]));
			if ( firstchar < 97 || firstchar > 104 || secondchar < 1 || secondchar > 8) {
				blackcontrols.remove(i);
			}
		}
		 */
		Collections.sort(whitecontrols);
		Collections.sort(blackcontrols);
	}
	public List<String> getwhitecontrols() {
		return whitecontrols;
	}
	public List<String> getblackcontrols() {
		return blackcontrols;
	}
	public static Piece getPieceOn(String query) {
		List<String> abc = Utilities.interpretmove(query);
		return board[Integer.parseInt(abc.get(0))-1][Integer.parseInt(abc.get(1))-1].getPiece();
	} //retrieve piece on coordinate
	public static void setPiece(int[] coordinates, Piece piece) {
		board[coordinates[0]][coordinates[1]].setPiece(piece);
	} //set piece on coordinate
	public static Square getSquare(int[] coords) {
		return board[coords[0]][coords[1]];
	} //retrieve square on coordinates
	public static Square getSquare(int targetx, int targety) {
		return board[targetx][targety];
	} //retrieve square on coordinates
	public static Square[][] getboard() {
		return board;
	} //return board
	public static void setboard(Square[][] abc) {
		board = abc;
	} //set board
}
