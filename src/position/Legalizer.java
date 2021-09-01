package position;

import java.util.ArrayList;

import move.ChessMove;
import move.Move;
import move.Turn;
import piece.ChessPiece;
import piece.Piece;

public class Legalizer {
	
	private Board toCompare;
	
	public Legalizer(Board toCompare){
		this.setPosition(toCompare);
	}
	
	public ChessMove[] determineAllLegalMoves(Turn turn) {
		
		ArrayList<ChessMove> poss = new ArrayList<ChessMove>();
		
		for(ChessPiece[] pieces: toCompare.getAll(turn == Turn.BLACK)) {
			for(ChessPiece piece: pieces) {
				if(piece.getType() == Piece.EMPTY){
					return null;
				}
				
				ChessMove[] moves = piece.getPossibleMoves();
				
				if(piece.getType() == Piece.PAWN){
					return this.determinePawn(moves, piece);
				} else if(piece.getType() == Piece.KING){
					return this.determineKing(moves, piece);
				} else if(piece.getType() == Piece.KNIGHT){
					
					for(ChessMove toCheck: moves){
						if(toCompare.isLegal(toCheck) && !toCompare.checkAfterMove(toCheck)){
							poss.add(toCheck);
						}
					}
					
				} else {
					
					for(ChessMove toCheck: moves){
						if(toCompare.isLegal(toCheck) && !toCompare.obstruction(toCheck) && !toCompare.checkAfterMove(toCheck)){
							poss.add(toCheck);
						}
					}
					
				}
				
				
			}
		}
		
		ChessMove[] ret = new ChessMove[poss.size()];
		
		int ind = 0;
		
		for(ChessMove toAdd: poss){
			ret[ind++] = toAdd;
		}
		
		return ret;
			
			
	}

	public ChessMove[] determineLegalMoves(ChessPiece piece) {
		// TODO Auto-generated method stub
		
		if(piece.getType() == Piece.EMPTY){
			return null;
		}
		
		ArrayList<ChessMove> poss = new ArrayList<ChessMove>();
		
		ChessMove[] moves = piece.getPossibleMoves();
		
		if(piece.getType() == Piece.PAWN){
			return this.determinePawn(moves, piece);
		} else if(piece.getType() == Piece.KING){
			return this.determineKing(moves, piece);
		} else if(piece.getType() == Piece.KNIGHT){
			
			for(ChessMove toCheck: moves){
				if(toCompare.isLegal(toCheck) && !toCompare.checkAfterMove(toCheck)){
					poss.add(toCheck);
				}
			}
			
		} else {
			
			for(ChessMove toCheck: moves){
				if(toCompare.isLegal(toCheck) && !toCompare.obstruction(toCheck) && !toCompare.checkAfterMove(toCheck)){
					poss.add(toCheck);
				}
			}
			
		}
		
		ChessMove[] ret = new ChessMove[poss.size()];
		
		int ind = 0;
		
		for(ChessMove toAdd: poss){
			ret[ind++] = toAdd;
		}
		
		return ret;
		
	}

	private ChessMove[] determineKing(ChessMove[] moves, ChessPiece piece) {
		// TODO Auto-generated method stub
		ArrayList<ChessMove> poss = new ArrayList<ChessMove>();
		
		
		
		if(toCompare.checkForCheck(piece.isBlack())){
			for(ChessMove toCheck: moves){
				if(toCompare.isLegal(toCheck) && !toCompare.obstruction(toCheck) && !toCompare.checkAfterMove(toCheck) && toCheck.getMoveType() != Move.CASTLE){
					poss.add(toCheck);
				}
			}
		} else if(!piece.hasBeenMoved){
			for(ChessMove toCheck: moves){
				if(toCompare.isLegal(toCheck) && !toCompare.obstruction(toCheck) && !toCompare.checkAfterMove(toCheck)){
					poss.add(toCheck);
				}
			}
		} else {
			for(ChessMove toCheck: moves){
				if(toCompare.isLegal(toCheck) && !toCompare.obstruction(toCheck) && !toCompare.checkAfterMove(toCheck) && toCheck.getMoveType() != Move.CASTLE){
					poss.add(toCheck);
				}
			}
		}
			
	
		ChessMove[] ret = new ChessMove[poss.size()];
		
		int ind = 0;
		
		for(ChessMove toAdd: poss){
			ret[ind++] = toAdd;
		}
		
		return ret;
		
	}

	private ChessMove[] determinePawn(ChessMove[] moves, ChessPiece piece) {
		// TODO Auto-generated method stub
		ArrayList<ChessMove> poss = new ArrayList<ChessMove>();
		
		if(!piece.hasBeenMoved){
			if(!toCompare.obstruction(moves[0]) && !toCompare.hasPiece(moves[0].getMoveX(), moves[0].getMoveY()) && !toCompare.checkAfterMove(moves[0])){
				poss.add(moves[0]);
			}
		}
		
		if(!toCompare.hasPiece(moves[1].getMoveX(), moves[1].getMoveY()) && !toCompare.checkAfterMove(moves[1])){
			poss.add(moves[1]);
		}
		
		if(toCompare.hasPiece(moves[2].getMoveX(), moves[2].getMoveY()) 
				&& toCompare.isBlack(moves[2].getMoveX(), moves[2].getMoveY()) != piece.isBlack() 
				&& !toCompare.checkAfterMove(moves[2])){
			poss.add(moves[2]);
		}
		
		if(toCompare.hasPiece(moves[3].getMoveX(), moves[3].getMoveY()) 
				&& toCompare.isBlack(moves[3].getMoveX(), moves[3].getMoveY()) != piece.isBlack() 
				&& !toCompare.checkAfterMove(moves[3])){
			poss.add(moves[3]);
		}
		
		ChessMove[] ret = new ChessMove[poss.size()];
		
		int ind = 0;
		
		for(ChessMove toAdd: poss){
			ret[ind++] = toAdd;
		}
		
		return ret;
		
	}

	public Board getPosition() {
		return toCompare;
	}

	public void setPosition(Board toCompare) {
		this.toCompare = toCompare;
	}
	
	
}
