package main;

import graphics.Images;
import position.Board;

public class Setup extends Thread {

	
	private Images prepared = null;
	private Board toPlay = null;
	
	
	@Override
	public void run(){
		prepared = new Images();
		toPlay = new Board();
	}

	public Images getImageRetriever() {
		return prepared;
	}

	public Board getPosition() {
		return toPlay;
	}

}