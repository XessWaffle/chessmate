package piece;

import java.awt.Point;
import java.io.Serializable;

import move.ChessMove;

public abstract class ChessPiece implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point currentLocation;
	protected Point prevLocation;
	protected boolean isBlack;
	private Piece type;
	private int reward;
	public boolean hasBeenMoved = false;
	
	public ChessPiece(Point currentLocation, boolean isBlack){
		this.currentLocation = currentLocation;
		prevLocation = new Point(-1,-1);
		this.isBlack = isBlack;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}
	
	public Point getPrevLocation(){
		return prevLocation;
	}

	public void move(Point currentLocation) {
		prevLocation = this.currentLocation;
		this.currentLocation = currentLocation;
	}
	
	public void setCurrentLocation(Point currentLocation){
		this.currentLocation = currentLocation;
	}
	
	public boolean isInBounds(ChessMove move){
		return move.getMove().x < 8 && move.getMove().y < 8 && move.getMove().x >= 0 && move.getMove().y >= 0;
	}
	
	public boolean isBlack(){
		return isBlack;
	}

	public Piece getType() {
		return type;
	}

	public void setType(Piece type) {
		this.type = type;
	}
	
	public String toString(){
		return this.getType() + "";
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
	
	public boolean equals(ChessPiece other){
		if(other.getType() == this.getType() && other.getCurrentLocation().equals(this.getCurrentLocation())){
			return true;
		}
		
		return false;
	}
	
	public abstract ChessMove[] getPossibleMoves();

}
