package chessboard;

import java.util.*;

public class utilities {
	public static List<String> interpretmove(String move) {
		List<String> coordsinterpretation = new ArrayList<String>();
		int minascii = 96;
		char[] chars = move.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(((int)(chars[i]) <=122 & (int)(chars[i]) >= 97) && !Character.isDigit(chars[i])) {
				coordsinterpretation.add(String.valueOf((int)(chars[i]) - minascii));
			}
			else {
				coordsinterpretation.add(String.valueOf((chars[i])));
			}
		}
		return coordsinterpretation;
	} //convert move into readable
	public static List<String> intarraytolist(int[] abc) {
		List<String> list = new ArrayList<String>();
		for (int i: abc) {
			list.add(String.valueOf(i));
		}
		return list;
	} //convert simple int array to list
	public static int[] coordstointarray(String abc) {
		int[] array = new int[2];
		if (abc.length() == 2) {
			array[0] = Integer.valueOf(utilities.interpretmove(abc.substring(0, 1)).get(0)) - 1;
			array[1] = Integer.valueOf(abc.substring(1)) - 1;
		}
		return array;
	}
	public static String coordstostring(int[] coords) {
		String abc = coords[0] + "" + coords[1];
		return abc;
	}
}
