package exception;

import piece.Piece;

public class KingNotFoundException extends PieceNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 189893201955945066L;
	
	public KingNotFoundException(){
		super(Piece.KING);
	}
	
	public KingNotFoundException(String description){
		super(Piece.KING, description);
	}
	
	
}
