package chessboard;
import java.util.*;
import pieces.*;

public class Game {
	static int movecounter = 1;
	static int ply = 0;
	static String check;
	public static void main(String[] args) {
		Chessboard xyz = new Chessboard();
		int[] abc = {0, 0};
		int[] bcd = {1, 0};
		
		//System.out.println(Chessboard.getPieceOn("b2"));
		Chessboard.getPieceOn("c1").move(Chessboard.getSquare(Utilities.coordstointarray("c1")), Chessboard.getSquare(Utilities.coordstointarray("a3")));
		
		xyz.detectcontrol();
		System.out.println("a1 HAS " + Chessboard.getPieceOn("a1"));
		System.out.println("h6 HAS " + Chessboard.getPieceOn("h6"));
		System.out.println("Rook on a1 controls Knight on b1? " + Chessboard.getPieceOn("a1").controlAllyLegal(Chessboard.getSquare(abc), Chessboard.getSquare(bcd)));
		System.out.print("White controls: ");
		for (String i: xyz.getwhitecontrols()) {
			System.out.print(i + ", ");
		}
		System.out.print("\nBlack controls: ");
		for (String j: xyz.getblackcontrols()) {
			System.out.print(j + ", ");
		}
		//for (square[] i: xyz.getboard()) {
			//for (square j: i) {
				//System.out.print(j.getPiece());
			//}
		//}
		
		
		//System.out.print(xyz.getPieceOn("b2"));
		//System.out.print(xyz.getPieceOn("c1"));
		//xyz.getPieceOn("c1").move(utilities.coordstointarray("c1"), utilities.coordstointarray("b2"));
		//xyz.getPieceOn("e2").move(utilities.coordstointarray("e2"), utilities.coordstointarray("e3"));
		//System.out.println(xyz.getPieceOn("e3"));
		//System.out.println("After Bb2 the piece on c1 is " + xyz.getPieceOn("c1") + ". the piece on b2 is ");
		//System.out.print(xyz.getPieceOn("b2"));
		
	}
	public static int getmovecounter() {
		return movecounter;
	}
}
