package move;

public enum Turn {
	WHITE, BLACK;
	
	public static boolean equals(Turn toCheck, Turn other){
		return other == toCheck;
	}
	
	public static Turn switchTurn(Turn toSwitch){
		if(toSwitch == BLACK){
			return WHITE;
		} else {
			return BLACK;
		}
	}
}
