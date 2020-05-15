package chessboard;
import java.awt.Color;
import java.util.*;
import java.util.stream.*;

import pieces.*;
public class chessboard {
	public static square[][] board = new square[8][8];
	public static List<String> whitecontrols = new ArrayList<String>(64);
	public static List<String> blackcontrols = new ArrayList<String>(64);
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
	public void detectcontrol() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				int abc[] = {i, j};
				System.out.println(utilities.coordstostring(abc));
				piece subject = getPieceOn(utilities.coordstostring(abc));
				int coordsofsubject[] = {Integer.parseInt(utilities.literalcoordstoString(abc).substring(0, 1)), Integer.parseInt(utilities.literalcoordstoString(abc).substring(1))}; 
				if (subject.toString().equals("pieces.queen")) { //test controls of queen
					//test horizontals and verticals
					for (int x = i; x < 8; x++) { //test positive x direction
						int coordsoftarget[] = {x, coordsofsubject[1]};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int x = i; x >= 0; x--) { //test negative x direction
						int coordsoftarget[] = {x, coordsofsubject[1]};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int y = j; y >= 0; y--) { //test positive y direction
						int coordsoftarget[] = {coordsofsubject[0], y};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int y = j; y < 8; y++) { //test negative y direction
						int coordsoftarget[] = {coordsofsubject[0], y};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					//test diagonals
					for (int a = i; a < 8; a++) { //test x positive diagonal upward direction
						int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] + a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a >= 0; a--) { //test x negative diagonal downward direction
						int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] - a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a < 8; a++) { //test x positive diagonal downward direction
						int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] - a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a >= 0; a--) { //test x negative diagonal upward direction
						int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] + a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
				}
				if (subject.toString().equals("pieces.bishop")) { //test controls of bishop
					for (int a = i; a < 8; a++) { //test x positive diagonal upward direction
						int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] + a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a >= 0; a--) { //test x negative diagonal downward direction
						int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] - a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a < 8; a++) { //test x positive diagonal downward direction
						int coordsoftarget[] = {(coordsofsubject[0] + a), (coordsofsubject[1] - a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int a = i; a >= 0; a--) { //test x negative diagonal upward direction
						int coordsoftarget[] = {(coordsofsubject[0] - a), (coordsofsubject[1] + a)};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
				}
				if (subject.toString().equals("pieces.rook")) { //test controls of rook
					for (int x = i; x < 8; x++) { //test positive x direction
						int coordsoftarget[] = {x, coordsofsubject[1]};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int x = i; x >= 0; x--) { //test negative x direction
						int coordsoftarget[] = {x, coordsofsubject[1]};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int y = i; y >= 0; y--) { //test positive y direction
						int coordsoftarget[] = {coordsofsubject[0], y};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
					for (int y = i; y < 8; y++) { //test negative y direction
						int coordsoftarget[] = {coordsofsubject[0], y};
						addcontrol(subject, coordsofsubject, coordsoftarget);
					}
				}
				if (subject.toString().equals("pieces.pawn")) { //test controls of pawn
					int coordsoftarget[] = {coordsofsubject[0]+1, coordsofsubject[1]};
					addcontrol(subject, coordsofsubject, coordsoftarget);
					coordsoftarget[0] = coordsofsubject[0]-1;
					addcontrol(subject, coordsofsubject, coordsoftarget);
				}
				if (subject.toString().equals("pieces.knight")) { //test controls of knight
					int knightmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1] + 2}, {coordsofsubject[0] + 2, coordsofsubject[1] + 1}, 
							{coordsofsubject[0] + 2, coordsofsubject[1] -1}, {coordsofsubject[0] + 1, coordsofsubject[1] - 2}, {coordsofsubject[0] - 1, coordsofsubject[1] - 2}, 
							{coordsofsubject[0] - 2, coordsofsubject[1] - 1},
							{coordsofsubject[0] - 2, coordsofsubject[1] + 1}, {coordsofsubject[0] - 1, coordsofsubject[1] + 2}};
					for (int[] knightmove: knightmoves) {
						addcontrol(subject, coordsofsubject, knightmove);
					}
				}
				if (subject.toString().equals("pieces.king")) { //test controls of king
					int kingmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1]},
							{coordsofsubject[0] + 1, coordsofsubject[1] - 1},
							{coordsofsubject[0], coordsofsubject[1] - 1},
							{coordsofsubject[0] -1, coordsofsubject[1] -1 },
							{coordsofsubject[0] - 1, coordsofsubject[1] - 1},
							{coordsofsubject[0] - 1, coordsofsubject[1] + 1},
							{coordsofsubject[0], coordsofsubject[1] + 1},
							{coordsofsubject[0] + 1, coordsofsubject[1] + 1}};
					for (int[] kingmove: kingmoves) {
						addcontrol(subject, coordsofsubject, kingmove);
					}
				}
			}
		}
		cleancontrols();
	}
	public void addcontrol(piece subject, int coordsofsubject[], int coordsoftarget[]) {
		System.out.println(subject + " " + coordsofsubject[0] + " " + coordsofsubject[1] + " " + coordsoftarget[0] + " " + coordsoftarget[1]);
		if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0 && "w".equals(subject.getcolor())) {
			if (subject.checklegal(getsquare(coordsofsubject), getsquare(coordsoftarget))) {
				System.out.println(utilities.coordstostring(coordsoftarget));
				whitecontrols.add(utilities.coordstostring(coordsoftarget));
			}
		}
		if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0 && "b".equals(subject.getcolor())) {
			if (subject.checklegal(getsquare(coordsofsubject), getsquare(coordsoftarget))) {
				blackcontrols.add(utilities.coordstostring(coordsoftarget));
			}
		}
	}
	public void cleancontrols() {
		whitecontrols = whitecontrols.stream().distinct().collect(Collectors.toList());
		blackcontrols = blackcontrols.stream().distinct().collect(Collectors.toList());
		for (int i = 0; i < whitecontrols.size(); i++) {
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
		Collections.sort(whitecontrols);
		Collections.sort(blackcontrols);
	}
	public List<String> getwhitecontrols() {
		return whitecontrols;
	}
	public List<String> getblackcontrols() {
		return blackcontrols;
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
