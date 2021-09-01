package piece;

public enum Piece {
	KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN, EMPTY;
	
	public static int convertToIndexLocation(Piece toConvert){
		if(toConvert == KING){
			return 0;
		} else if(toConvert == QUEEN){
			return 2;
		} else if(toConvert == ROOK){
			return 4;
		} else if(toConvert == KNIGHT){
			return 6;
		} else if(toConvert == BISHOP){
			return 8;
		} else if(toConvert == PAWN){
			return 10;
		}
		
		return -1;
	}
}
