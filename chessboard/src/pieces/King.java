package pieces;
import chessboard.*;

public class King extends Piece {
	boolean incheck;
	public King(String color) {
		super(color);
	}
	public boolean detectcheck(Square origin) {
		switch (this.getColor()) {
		case "w":
			if (Chessboard.blackcontrols.contains(Utilities.coordstostring(origin.getCoords()))) {
				incheck = true;
			}
			else {
				incheck = false;
			}
			break;
		case "b":
			if (Chessboard.whitecontrols.contains(Utilities.coordstostring(origin.getCoords()))) {
				incheck = true;
			}
			else {
				incheck = false;
			}
			break;
		}
		return incheck;	
	}
	public void castle(String direction) {
		if (!getHasMoved()) {
			switch (getColor()) {
			case "w":
				if ("O-O-O".equalsIgnoreCase(direction) && !Chessboard.getPieceOn("a1").getHasMoved() 
						&& Chessboard.getPieceOn("c1").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("b1").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("d1").toString().equals("pieces.Empty")) {
					Chessboard.setPiece(Utilities.coordstointarray("e1"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("a1"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("c1"), new King("w"));
					Chessboard.getPieceOn("c1").setHasMoved(true);
					Chessboard.setPiece(Utilities.coordstointarray("d1"), new Rook("w"));
					Chessboard.getPieceOn("d1").setHasMoved(true);
				}
				if ("O-O".equalsIgnoreCase(direction) && !Chessboard.getPieceOn("h1").getHasMoved()
						&& Chessboard.getPieceOn("f1").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("g1").toString().equals("pieces.Empty")) {
					Chessboard.setPiece(Utilities.coordstointarray("e1"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("h1"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("g1"), new King("w"));
					Chessboard.getPieceOn("g1").setHasMoved(true);
					Chessboard.setPiece(Utilities.coordstointarray("f1"), new Rook("w"));
					Chessboard.getPieceOn("f1").setHasMoved(true);
				}
				break;
			case "b":
				if ("O-O-O".equalsIgnoreCase(direction) && !Chessboard.getPieceOn("a8").getHasMoved() 
						&& Chessboard.getPieceOn("c8").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("b8").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("d8").toString().equals("pieces.Empty")) {
					Chessboard.setPiece(Utilities.coordstointarray("e8"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("a8"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("c8"), new King("b"));
					Chessboard.getPieceOn("c8").setHasMoved(true);
					Chessboard.setPiece(Utilities.coordstointarray("d8"), new Rook("b"));
					Chessboard.getPieceOn("d8").setHasMoved(true);
				}
				if ("O-O".equalsIgnoreCase(direction) && !Chessboard.getPieceOn("h8").getHasMoved()
						&& Chessboard.getPieceOn("f8").toString().equals("pieces.Empty") 
						&& Chessboard.getPieceOn("g8").toString().equals("pieces.Empty")) {
					Chessboard.setPiece(Utilities.coordstointarray("e8"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("h8"), new Empty());
					Chessboard.setPiece(Utilities.coordstointarray("g8"), new King("b"));
					Chessboard.getPieceOn("g8").setHasMoved(true);
					Chessboard.setPiece(Utilities.coordstointarray("f8"), new Rook("b"));
					Chessboard.getPieceOn("f8").setHasMoved(true);
				}
				break;
			}
		}
		else {
			System.out.println("Illegal move");
		}
	}
	public boolean checklegal(Square origin, Square target) {
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		if (Math.abs(originx-targetx) == 1 || Math.abs(originy-targety) == 1) {
			return true;
		}
		return false;
	}
	public void checkcontrol(Square origin) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		int kingmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1]},
				{coordsofsubject[0] + 1, coordsofsubject[1] - 1},
				{coordsofsubject[0], coordsofsubject[1] - 1},
				{coordsofsubject[0] -1, coordsofsubject[1]},
				{coordsofsubject[0] - 1, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 1, coordsofsubject[1] + 1},
				{coordsofsubject[0], coordsofsubject[1] + 1},
				{coordsofsubject[0] + 1, coordsofsubject[1] + 1}};
		for (int[] kingmove: kingmoves) {
			if (kingmove[0] < 8 && kingmove[0] >= 0 && kingmove[1] < 8 && kingmove[1] >= 0) {
				Chessboard.addcontrol(subject, coordsofsubject, kingmove);	
			}
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		int kingmoves[][] = {{coordsofsubject[0] + 1, coordsofsubject[1]},
				{coordsofsubject[0] + 1, coordsofsubject[1] - 1},
				{coordsofsubject[0], coordsofsubject[1] - 1},
				{coordsofsubject[0] -1, coordsofsubject[1]},
				{coordsofsubject[0] - 1, coordsofsubject[1] - 1},
				{coordsofsubject[0] - 1, coordsofsubject[1] + 1},
				{coordsofsubject[0], coordsofsubject[1] + 1},
				{coordsofsubject[0] + 1, coordsofsubject[1] + 1}};
		for (int[] kingmove: kingmoves) {
			if (kingmove[0] < 8 && kingmove[0] >= 0 && kingmove[1] < 8 && kingmove[1] >= 0 && target.getCoords().equals(kingmove) && target.getPiece().getColor().equals(subject.getColor())) {
				return true;
			}
		}
		return false;
	}
}