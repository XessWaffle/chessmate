package piece;

import java.awt.Point;

import move.ChessMove;

public class Empty extends ChessPiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Empty(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub
	
		this.setType(Piece.EMPTY);
		this.setReward(0);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsBlack(boolean isBlack){
		super.isBlack = isBlack;
	}
	
	public String toString(){
		return "";
	}

}
