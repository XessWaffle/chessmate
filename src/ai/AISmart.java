package ai;

import java.util.ArrayList;

import eval.MoveEvaluator;
import move.ChessMove;
import piece.ChessPiece;
import position.Board;

public class AISmart extends AI{

	public AISmart(Board toPlay, boolean toPlayAs) {
		super(toPlay, toPlayAs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ChessMove playNext() {
		// TODO Auto-generated method stub
		ChessMove[] allMoves = this.sortByValue(this.getAllMoves());
		
		return allMoves[(int)(Math.random() * 3)];
	}

	private ChessMove[] sortByValue(ChessMove[] allMoves) {
		// TODO Auto-generated method stub
		this.quickSort(0, allMoves.length - 1, allMoves);
		
		return allMoves;
	}

	private ChessMove[] getAllMoves() {
		// TODO Auto-generated method stub
		
		MoveEvaluator me = new MoveEvaluator(this.getPosition());
		
		ArrayList<ChessMove> allPossibleMoves = new ArrayList<ChessMove>();
		
		for(ChessPiece[] locs: this.getPosition().getAll(isBlack())){
			for(ChessPiece toDet: locs){
				for(ChessMove legalMove: this.getPosition().getLegalizer().determineLegalMoves(toDet)){
					allPossibleMoves.add(me.evaluateMove(legalMove));
				}
			}
		}
		
		ChessMove[] ret = new ChessMove[allPossibleMoves.size()];
		
		int ind = 0;
		
		for(ChessMove toAdd: allPossibleMoves){
			ret[ind++] = toAdd;
		}
		
		return ret;
	}
	
	private void quickSort(int low, int high, ChessMove[] moves){
		
		//System.out.println(Arrays.toString(toSort));
		
		if(high - low <= 0){
			return;
		} else {
			int pivot = moves[high].getRating();
			int partition = partition(low, high, pivot, moves);
			quickSort(low, partition - 1, moves);
			quickSort(partition + 1, high, moves);
			
		}
		

		
	}
	
	private int partition(int left, int right, int pivot, ChessMove[] moves){
		
		int nLeft = left;
		int nRight = right - 1;
		
		while(true){
			
			while(moves[nLeft].getRating() < pivot){
				nLeft++;
			}
			while(nRight >= 0 && moves[nRight].getRating() > pivot){
				nRight--;
			}
			
			if(nLeft >= nRight){ 
				break;
			} else {
				swap(nLeft,nRight, moves);

				nLeft++;
				nRight--;
			}
			
		}
		
			swap(nLeft, right, moves);
		
		return nLeft;
	}

	private void swap(int nLeft, int nRight, ChessMove[] moves) {
		// TODO Auto-generated method stub
		
		ChessMove temp = moves[nLeft];
		
		moves[nLeft] = moves[nRight];
		
		moves[nRight] = temp;
		
	}
}
