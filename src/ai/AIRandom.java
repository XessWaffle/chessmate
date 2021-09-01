package ai;

import java.util.Random;

import move.ChessMove;
import piece.ChessPiece;
import position.Board;

public class AIRandom extends AI {

	public AIRandom(Board toPlay, boolean toPlayAs) {
		super(toPlay, toPlayAs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ChessMove playNext() {
		// TODO Auto-generated method stub

		Random ran = new Random();
		
		ChessMove[] play;
			
		do{
			play = toPlay.getLegalizer().determineLegalMoves(this.findRandomPiece());
			System.out.println("!P2O");
		} while(play.length == 0);
				
		
		int det = ran.nextInt(play.length);
		
		return play[det];
	}
	
	public ChessPiece findRandomPiece(){
		
		ChessPiece toMove = null;
		
		ChessPiece[][] all = toPlay.getAll(isBlack());
		
		Random ran = new Random();
		
		do{
			int x = ran.nextInt(all.length), y;
			
			if(all[x].length > 1)
				y = ran.nextInt(all[x].length);
			else if(all[x].length == 1){
				y = 0;
			} else {
				y = -1;
			}
			
			if(y != -1)
				toMove = all[x][y];
			
		} while(toMove == null);
		
		
		return toMove;
	}

}
