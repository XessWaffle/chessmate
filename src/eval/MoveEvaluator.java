package eval;

import java.awt.Point;

import move.ChessMove;
import piece.ChessPiece;
import position.Legalizer;
import position.Board;

public class MoveEvaluator {
	private Board toCompare;
	private Legalizer valueDet;
	
	
	public MoveEvaluator(Board toCompare){
		this.setPosition(toCompare);
		valueDet = new Legalizer(toCompare);
	}

	public Board getPosition() {
		return toCompare;
	}

	public void setPosition(Board toCompare) {
		this.toCompare = toCompare;
	}
	
	public Legalizer getLegalizer() {
		return valueDet;
	}

	public void setLegalizer(Board toDet) {
		valueDet.setPosition(toDet);
	}
	
	public ChessMove evaluateMove(ChessMove move){
		
		ChessMove replace = new ChessMove(toCompare.getPiece(move.getMoveX(), move.getMoveY()), toCompare.getPiece(move.getMoveX(), move.getMoveY()).getCurrentLocation());
		
		toCompare.setPiece(move);
		toCompare.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);	
		
		BoardEvaluator board = new BoardEvaluator();
		
		int rating = board.evaluatePosition(getPosition(), move.getPiece().isBlack());
		
		for(ChessMove possibleMoves: move.getPiece().getPossibleMoves()){
			
			if(possibleMoves.isValid())
				rating += toCompare.getSquare(possibleMoves.getMoveX(), possibleMoves.getMoveY()).getReward();
		}
		
		System.out.println("Piece: " + move + " " + move.getMove() + "\nRating: " + rating);
		
		toCompare.setPiece(move.getReplacing());
		toCompare.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
		toCompare.setPiece(replace);
		
		move.setRating(rating);
		
		
		return move;
		
	}

	
	
}
