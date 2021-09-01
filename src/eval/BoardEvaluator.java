package eval;

import piece.ChessPiece;
import position.Board;

public class BoardEvaluator {
	
	public BoardEvaluator(){}
	
	public BoardEvaluator(Board position) {
		// TODO Auto-generated constructor stub
	}

	public int evaluatePosition(Board toEvaluate, boolean isBlack){
		
		ChessPiece[][] black = toEvaluate.getAll(true);
		ChessPiece[][] white = toEvaluate.getAll(false);
		
		int reward = 0;
		
		if(isBlack){

			for(ChessPiece[] locs: black){
				for(ChessPiece p: locs){
					reward +=p.getReward();
				}
			}
			
			for(ChessPiece[] locs: white){
				for(ChessPiece p: locs){
					reward -= p.getReward();
				}
			}
		} else {

			for(ChessPiece[] locs: black){
				for(ChessPiece p: locs){
					reward -=p.getReward();
				}
			}
			
			for(ChessPiece[] locs: white){
				for(ChessPiece p: locs){
					reward += p.getReward();
				}
			}

		}
		
		return reward;
		
		
		
		
		
		
	}
	
	
}
