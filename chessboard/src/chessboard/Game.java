package chessboard;
import java.util.*;
import pieces.*;

public class Game {
	static int movecounter = 1;
	static int ply = 0;
	static String check;
	public static void main(String[] args) {
		Chessboard xyz = new Chessboard();
		int[] abc = {1, 1};
		
		System.out.println(xyz.getPieceOn("b2"));
		xyz.getPieceOn("f1").move(Utilities.coordstointarray("f1"), Utilities.coordstointarray("e2"));
		
		xyz.detectcontrol();
		System.out.println("f1 HAS " + xyz.getPieceOn("f1"));
		System.out.println("e2 HAS " + xyz.getPieceOn("e2"));
		System.out.print("White controls: ");
		for (String i: xyz.getwhitecontrols()) {
			System.out.print(i + ", ");
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
