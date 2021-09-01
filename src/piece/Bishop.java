package piece;

import java.awt.Point;
import java.util.ArrayList;

import move.ChessMove;
import move.Move;

public class Bishop extends ChessPiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bishop(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub

		this.setType(Piece.BISHOP);
		this.setReward(3);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		ArrayList<Point> toRet = new ArrayList<Point>();
		
		
		
		int i = 0;
		
		while(i < 8){
			toRet.add(new Point(i,(int) (currentLocation.getY() - currentLocation.getX() + i)));
			toRet.add(new Point(i,(int) (currentLocation.getY() + currentLocation.getX() - i)));
			i++;
		}
		
		
		ChessMove[] ret = new ChessMove[toRet.size()];
		
		int ind = 0;
		
		for(Point p: toRet){
			
			ret[ind] = new ChessMove(this, p);
			ret[ind].setValidity(this.isInBounds(ret[ind]));
			ret[ind++].setMoveType(Move.VALID);
			//System.out.println(p);
		}
		
		return ret;
	}
	
	public String toString(){
		return "B";
	}

}
