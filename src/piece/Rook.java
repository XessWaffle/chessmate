package piece;

import java.awt.Point;
import java.util.ArrayList;

import move.ChessMove;
import move.Move;

public class Rook extends ChessPiece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rook(Point currentLocation, boolean isBlack) {
		super(currentLocation, isBlack);
		// TODO Auto-generated constructor stub

		this.setType(Piece.ROOK);
		this.setReward(6);
	}

	@Override
	public ChessMove[] getPossibleMoves() {
		// TODO Auto-generated method stub
		ArrayList<Point> toRet = new ArrayList<Point>();
		
		
		for(int i = 0; i < 8; i++){
			toRet.add(new Point(i, (int) this.getCurrentLocation().getY()));
			toRet.add(new Point((int) this.getCurrentLocation().getX(), i));
		}
		
			
		ChessMove[] ret = new ChessMove[toRet.size()];
		
		int ind = 0;
		
		for(Point p: toRet){

			ret[ind] = new ChessMove(this, p);
			ret[ind].setValidity(this.isInBounds(ret[ind]));
			ret[ind++].setMoveType(Move.VALID);
		}
		
		return ret;
	}
	
	public String toString(){
		return "R";
	}

}
