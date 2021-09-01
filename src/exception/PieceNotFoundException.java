package exception;

import piece.Piece;

public class PieceNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5363456158691392961L;
	
	private String description;
	private Piece type;
	
	
	public PieceNotFoundException(){
		setErrorDescription("Piece not found");
	}
	
	public PieceNotFoundException(Piece type){
		setErrorDescription(type + " not found");
		this.type = type;
	}
	
	public PieceNotFoundException(Piece type, String description){
		this.type = type;
		this.setErrorDescription(description);
	}

	public Piece getPieceNotFoundType() {
		return type;
	}

	public String getErrorDescription() {
		return description;
	}

	public void setErrorDescription(String description) {
		this.description = description;
	}
}
