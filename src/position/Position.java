package position;

import java.io.Serializable;

import exception.KingNotFoundException;
import move.ChessMove;

public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3541770241586526174L;

	private Board position;
	
	private boolean isStaleMate, isCheckMate;
	
	private ChessMove[] legalMoves;
	
	
	public Position(Board position) throws KingNotFoundException {
		this.setPosition(position);
		
		this.setStaleMate(position.checkForStaleMate(true) || position.checkForStaleMate(false));
		this.setCheckMate(position.checkForMate(true) || position.checkForMate(false));
		
		
		setLegalMoves(position.getLegalizer().determineAllLegalMoves(position.getTurn()));
		
	}

	/**
	 * @return the position
	 */
	public Board getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(Board position) {
		this.position = position;
	}


	/**
	 * @return the isStaleMate
	 */
	public boolean isStaleMate() {
		return isStaleMate;
	}


	/**
	 * @param isStaleMate the isStaleMate to set
	 */	
	public void setStaleMate(boolean isStaleMate) {
		this.isStaleMate = isStaleMate;
	}


	/**
	 * @return the isCheckMate
	 */
	public boolean isCheckMate() {
		return isCheckMate;
	}


	/**
	 * @param isCheckMate the isCheckMate to set
	 */
	public void setCheckMate(boolean isCheckMate) {
		this.isCheckMate = isCheckMate;
	}


	/**
	 * @return the legalMoves
	 */
	public ChessMove[] getLegalMoves() {
		return legalMoves;
	}

	/**
	 * @param legalMoves the legalMoves to set
	 */
	public void setLegalMoves(ChessMove[] legalMoves) {
		this.legalMoves = legalMoves;
	}
	
	public Position getLegalPosition(ChessMove move) throws KingNotFoundException {
		
		boolean ret = false;
		
		for(ChessMove lMove: legalMoves) {
			if(move.equals(lMove)) {
				ret = true;
			}
		}
		
		if(!ret) {
			return null;
		} else {
			position.move(move);
			
			return new Position(position);
		}
	}
	
	public int[] obtainBoardValues() {
		
		int[] boardValues = new int[64];
		
		int cnt = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				boardValues[cnt++] = position.getSquare(i, j).getReward();
			}
		}
		
		return boardValues;
	}
	
	
}
