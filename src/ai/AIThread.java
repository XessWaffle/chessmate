package ai;

import java.awt.Point;

import exception.KingNotFoundException;
import graphics.Window;
import move.ChessMove;
import move.Turn;
import piece.Piece;
import piece.Queen;

public class AIThread extends Thread{

	private AI black;
	private AI white;
	
	private Window toPlay;
	public AIThread(Window toPlay){
		black = new AISmart(toPlay.getPosition(), true);
		white = new AISmart(toPlay.getPosition(), false);	
	
		this.toPlay = toPlay;
	
	}
	
	@Override
	public void run(){
		try {
			while(!toPlay.getPosition().checkForMate(true) || !toPlay.getPosition().checkForMate(false) || toPlay.getPosition().count(true) + toPlay.getPosition().count(false) == 2){
				
				ChessMove move;
				
				move = white.playNext();
				
				boolean hasPiece = toPlay.getPosition().hasPiece(move.getMoveX(), move.getMoveY());
				
				toPlay.getMoveTable().writeMove(Turn.WHITE, toPlay.generateMoveString(move, hasPiece));
				
				toPlay.getPosition().move(move);
				
				paintCheck();
				
				try{
					Thread.sleep(2000);
				} catch(Exception e){
					e.printStackTrace();
				}
				
				promote();
				
				if(!(!toPlay.getPosition().checkForMate(true) || !toPlay.getPosition().checkForMate(false) || toPlay.getPosition().count(true) + toPlay.getPosition().count(false) == 2)){
					break;
				}
			}
		} catch (KingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("END");
		
	}
	
	private void promote() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 8; i++){
			if(toPlay.getPosition().getTurn() == Turn.WHITE){
				if(toPlay.getPosition().getSquare(7, i).getPiece().getType() == Piece.PAWN){
					toPlay.getPosition().getSquare(7, i).setPiece(new Queen(new Point(7, i), true));
				}
			} else {
				if(toPlay.getPosition().getSquare(0, i).getPiece().getType() == Piece.PAWN){
					toPlay.getPosition().getSquare(0, i).setPiece(new Queen(new Point(0, i), false));
				}
			}
		}
	}

	private void paintCheck(){
		
		toPlay.update(toPlay.getGraphics());
		
		if(toPlay.getPosition().checkForCheck(true)){
			toPlay.paintCheck(toPlay.getGraphics(), true);
		} else if(toPlay.getPosition().checkForCheck(false)){
			toPlay.paintCheck(toPlay.getGraphics(), false);
		}
		
	}
	
	
}
