package chessboard;
import java.util.*;
import pieces.*;

public class game {
	static int movecounter = 1;
	static int ply = 0;
	public static void main(String[] args) {
		chessboard xyz = new chessboard();
		xyz.getPieceOn("a1").move(utilities.coordstointarray("a1"), utilities.coordstointarray("a2"));
		System.out.println(xyz.getPieceOn("a2"));
		int[] abc = {1, 1};
		xyz.detectcontrol();
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