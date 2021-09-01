package ai;

import move.ChessMove;
import position.Board;

public abstract class AI {
	
	protected Board toPlay;
	private boolean isBlack;
	
	public AI(Board toPlay, boolean toPlayAs){
		this.setPosition(toPlay);
		this.isBlack = toPlayAs;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public Board getPosition() {
		return toPlay;
	}

	public void setPosition(Board toPlay) {
		this.toPlay = toPlay;
	}
	
	public abstract ChessMove playNext();

	
}
