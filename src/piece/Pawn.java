package piece;

import java.awt.Point;
import java.util.ArrayList;

import move.ChessMove;
import move.Move;

public class Pawn extends ChessPiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pawn(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub
	
		this.setType(Piece.PAWN);
		this.setReward(1);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		ArrayList<Point> toRet = new ArrayList<Point>();
		
		//System.out.println(this.getCurrentLocation());
		
		if(isBlack()){
			
		
			toRet.add(new Point((int)this.getCurrentLocation().getX() + 2, (int)this.getCurrentLocation().getY()));
			
			toRet.add(new Point((int)this.getCurrentLocation().getX() + 1, (int)this.getCurrentLocation().getY()));
			toRet.add(new Point((int)this.getCurrentLocation().getX() + 1, (int)this.getCurrentLocation().getY() - 1));
			toRet.add(new Point((int)this.getCurrentLocation().getX() + 1, (int)this.getCurrentLocation().getY() + 1));
		} else {
			
			
			toRet.add(new Point((int)this.getCurrentLocation().getX() - 2, (int)this.getCurrentLocation().getY()));
			
			toRet.add(new Point((int)this.getCurrentLocation().getX() - 1, (int)this.getCurrentLocation().getY()));
			toRet.add(new Point((int)this.getCurrentLocation().getX() - 1, (int)this.getCurrentLocation().getY() - 1));
			toRet.add(new Point((int)this.getCurrentLocation().getX() - 1, (int)this.getCurrentLocation().getY() + 1));
		}
		
		
		ChessMove[] ret = new ChessMove[toRet.size()];
		
		int ind = 0;
		
		for(Point p: toRet){

			ret[ind] = new ChessMove(this, p);
			
			ret[ind].setValidity(this.isInBounds(ret[ind]));
			ret[ind++].setMoveType(Move.VALID);
			//System.out.println(p);
		}
		
		ret[0].setMoveType(Move.UNCHECKABLE);
		ret[1].setMoveType(Move.UNCHECKABLE);
		
		return ret;
	}
	
	public String toString(){
		return "P";
	}

}
