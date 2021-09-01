package move;

public enum Move {
	VALID, INVALID, CASTLE, EN_PASSANT, UNCHECKABLE;
	
	public static boolean equals(Move one, Move two){
		return one == two;
	}
}
