package pieces;
import chessboard.*;

public class Pawn extends Piece {
	public Pawn(String color) {
		super(color);
	}
	public boolean checklegal(Square origin, Square target) {
		//need to implement en passant, promotion
		int targetx = target.getCoords()[0];
		int targety = target.getCoords()[1];
		int originx = origin.getCoords()[0];
		int originy = origin.getCoords()[1];
		String origincolor = origin.getPiece().getColor();
		String targetcolor = target.getPiece().getColor();
		System.out.println("Coords are:\nTarget: " + targetx + " " + targety + " Origin: " + originx + " " + originy);
		if (Math.abs(originx-targetx) == 0) {
			switch (Math.abs(targety-originy)) {
			case 2: //if doublestep
				switch (origincolor) {
				case "w": //if pawn is white
					if (originy == 1 && "w".equals(origincolor)) {
						System.out.println("checktrue");
						int emptyintermediates = 0;
						for (int i = originy+1; i <= targety; i++) {
							if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.Empty")) {
								emptyintermediates++;
							}
						}
						System.out.println("Pawndist " + emptyintermediates + " " + Math.abs(targety-originy));
						if (emptyintermediates == Math.abs(targety-originy)) {
							return true;
						}
					}
					break;
				case "b": //if pawn is black
					if (originy == 6 && "b".equals(origincolor)) {
						System.out.println("checktrue");
						int emptyintermediates = 0;
						for (int i = originy-1; i >= targety; i--) {
							if (Chessboard.getboard()[originx][i].getPiece().toString().equals("pieces.Empty")) {
								emptyintermediates++;
							}
						}
						if (emptyintermediates == Math.abs(targety-originy)) {
							return true;
						}
					}
					break;
				}
			case 1:
				switch (getColor()) {
				case "w":
					System.out.println("checktrue");
					if ("pieces.Empty".equals(target.getPiece().toString()) && target.getCoords()[1] == origin.getCoords()[1] + 1) {
						return true;
					}
					break;
				case "b":
					System.out.println("checktrue");
					if ("pieces.Empty".equals(target.getPiece().toString()) && target.getCoords()[1] == origin.getCoords()[1] - 1) {
						return true;
					}
					break;
				}
			}
		}
		if ((Math.abs(targetx-originx) == 1) && !origincolor.equals(targetcolor)) { //allows pawn to capture
			return true;
		}
		return false;
	}
	public void checkcontrol(Square origin) {
		Piece subject = origin.getPiece();
		int coordsofsubject[] = origin.getCoords();
		switch (getColor()) {
		case "w":
			int coordsoftarget[] = {coordsofsubject[0]+1, coordsofsubject[1]+1};
			if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0) {
				Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
			}
			coordsoftarget[0] = coordsofsubject[0]-1;
			if (coordsoftarget[0] < 8 && coordsoftarget[0] >= 0 && coordsoftarget[1] < 8 && coordsoftarget[1] >= 0) {
				Chessboard.addcontrol(subject, coordsofsubject, coordsoftarget);
			}
			break;
		case "b":
			int coordsoftargetb[] = {coordsofsubject[0]-1, coordsofsubject[1]-1};
			if (coordsoftargetb[0] < 8 && coordsoftargetb[0] >= 0 && coordsoftargetb[1] < 8 && coordsoftargetb[1] >= 0) {
				Chessboard.addcontrol(subject, coordsofsubject, coordsoftargetb);
			}
			coordsoftargetb[0] = coordsofsubject[0]+1;
			if (coordsoftargetb[0] < 8 && coordsoftargetb[0] >= 0 && coordsoftargetb[1] < 8 && coordsoftargetb[1] >= 0) {
				Chessboard.addcontrol(subject, coordsofsubject, coordsoftargetb);
			}
			break;
		}
	}
	public boolean controlAllyLegal(Square origin, Square target) {
		Piece subject = origin.getPiece();
		if (target.getPiece().getColor().equals(subject.getColor())) {
			return true;
		}
		return false;
	}
}
