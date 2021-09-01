package move;

import java.awt.Point;

import piece.ChessPiece;

public class ChessMove {
	
	private ChessPiece toMove;
	private Point toPut;
	private Move type;
	
	private boolean isValid, hasBeenUpdated = false;
	private int rating = 0;
	
	public ChessMove(ChessPiece toMove, Point toPut){
		this.setPiece(toMove);
		this.setMove(toPut);
	}
	
	private void checkUpdate(){
		if(type != null && rating != 0){
			setHasBeenUpdated(true);
		}
	}

	public ChessPiece getPiece() {
		return toMove;
	}

	public void setPiece(ChessPiece toMove) {
		this.toMove = toMove;
	}
	
	public Point getCurrentPieceLocation(){
		return toMove.getCurrentLocation();
	}
	
	public Point getPreviousPieceLocation(){
		return toMove.getPrevLocation();
	}

	public Point getMove() {
		return toPut;
	}

	public void setMove(Point toPut) {
		this.toPut = toPut;
	}
	
	public int getMoveX(){
		return toPut.x;
	}
	
	public int getMoveY(){
		return toPut.y;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
		this.checkUpdate();
	}

	public Move getMoveType() {
		return type;
	}

	public void setMoveType(Move type) {
		this.type = type;
		this.checkUpdate();
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValidity(boolean isValid) {
		this.isValid = isValid;
	}
	
	public boolean hasBeenUpdated() {
		return hasBeenUpdated;
	}

	public void setHasBeenUpdated(boolean hasBeenUpdated) {
		this.hasBeenUpdated = hasBeenUpdated;
	}
	
	public ChessMove getReplacing(){
		return new ChessMove(toMove, toMove.getPrevLocation());
	}
	
	public boolean equals(ChessMove other){
		
		return (other.toMove.equals(toMove) && other.getMove().equals(this.getMove()));
	}
	
	public String toString(){
		return toMove.toString();
	}

	
	
	
	
}
