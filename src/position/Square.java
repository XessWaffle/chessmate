package position;


import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import piece.ChessPiece;


public class Square implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_SIZE = 75;
	
	private ChessPiece piece;
	private Color color;
	private Point location;
	
	private int reward;
	
	public boolean showDot = false; 
	
	public Square(ChessPiece piece){
		this.piece = piece;
		this.location = piece.getCurrentLocation();		
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
		piece.move(location);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getReward() {
		return this.getPiece().getReward();
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public boolean equals(Square other){
		if(other.location.equals(this.location) && other.getPiece().equals(this.getPiece())){
			return true;
		}
		
		return false;
	}


}