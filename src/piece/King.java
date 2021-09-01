package piece;

import java.awt.Point;
import java.util.ArrayList;

import move.ChessMove;
import move.Move;

public class King extends ChessPiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isMated;
	private boolean isCastled;
	
	public King(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub
	
		this.setType(Piece.KING);
		this.setReward(200);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		
		ArrayList<Point> toRet = new ArrayList<Point>();
		
		if(isBlack()){
			toRet.add(new Point(0,2));
			toRet.add(new Point(0,6));
		} else {
			toRet.add(new Point(7,2));
			toRet.add(new Point(7,6));
		}	
		
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <=1; j++)
				if(!(i == 0 && j == 0))
					toRet.add(new Point((int)this.currentLocation.getX() + i, (int)this.currentLocation.getY() + j));
			
		
		ChessMove[] ret = new ChessMove[toRet.size()];
		
		int ind = 0;
		
		for(Point p: toRet){

			ret[ind] = new ChessMove(this, p);
			ret[ind].setValidity(this.isInBounds(ret[ind]));
			ret[ind++].setMoveType(Move.VALID);
			//System.out.println(p);
		}
		
		ret[0].setMoveType(Move.CASTLE);
		ret[1].setMoveType(Move.CASTLE);
		
		return ret;
		
	}

	public boolean isMated() {
		return isMated;
	}

	public void setMate(boolean isMated) {
		this.isMated = isMated;
	}

	public boolean isCastled() {
		return isCastled;
	}

	public void setCastled(boolean isCastled) {
		this.isCastled = isCastled;
	}
	
	public String toString(){
		return "K";
	}

}
