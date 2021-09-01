package piece;

import java.awt.Point;
import java.util.ArrayList;


import move.ChessMove;
import move.Move;

public class Knight extends ChessPiece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Knight(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub
		
		this.setType(Piece.KNIGHT);
		this.setReward(4);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		ArrayList<Point> toRet = new ArrayList<Point>();
		
		toRet.add(new Point((int)this.getCurrentLocation().getX() + 1, (int)this.getCurrentLocation().getY() + 2));
		toRet.add(new Point((int)this.getCurrentLocation().getX() + 1, (int)this.getCurrentLocation().getY() - 2));
		toRet.add(new Point((int)this.getCurrentLocation().getX() + 2, (int)this.getCurrentLocation().getY() + 1));
		toRet.add(new Point((int)this.getCurrentLocation().getX() + 2, (int)this.getCurrentLocation().getY() - 1));
		toRet.add(new Point((int)this.getCurrentLocation().getX() - 1, (int)this.getCurrentLocation().getY() + 2));
		toRet.add(new Point((int)this.getCurrentLocation().getX() - 1, (int)this.getCurrentLocation().getY() - 2));
		toRet.add(new Point((int)this.getCurrentLocation().getX() - 2, (int)this.getCurrentLocation().getY() + 1));
		toRet.add(new Point((int)this.getCurrentLocation().getX() - 2, (int)this.getCurrentLocation().getY() - 1));
		
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
		return "N";
	}

}
