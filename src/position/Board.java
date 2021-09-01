package position;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import exception.KingNotFoundException;
import move.ChessMove;
import move.Move;
import move.Turn;
import piece.Bishop;
import piece.Empty;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Queen;
import piece.Rook;
import piece.ChessPiece;
import piece.Piece;

public class Board {
	
	private Square[][] board;
	private Turn turn = Turn.WHITE;
	private Legalizer preMove;
	
	private static final boolean isWhite = true;
	private static final float grayFactor = (float) 0.35;
	
	private int value;
	
	public Board(){
		board = new Square[8][8];
		
		preMove = new Legalizer(this);
		
		this.reset();
	}
	
	public Board(Board toCopy){
		this.board = toCopy.board.clone();
		this.turn = toCopy.turn;
	}
	
	public void reset() {
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				if(i == 1){
					board[i][j] = new Square(new Pawn(new Point(i, j), isWhite));
				} else if(i == 6){
					board[i][j] = new Square(new Pawn(new Point(i, j), !isWhite));
				} else if(i == 0){
					if(j == 0 || j == 7){
						board[i][j] = new Square(new Rook(new Point(i, j), isWhite));
					} else if(j == 1 || j == 6){
						board[i][j] = new Square(new Knight(new Point(i, j), isWhite));
					} else if(j == 2 || j == 5){
						board[i][j] = new Square(new Bishop(new Point(i, j), isWhite));
					} else if(j == 3){
						board[i][j] = new Square(new Queen(new Point(i, j), isWhite));
					} else {
						board[i][j] = new Square(new King(new Point(i, j), isWhite));
					}
				} else if(i == 7){
					if(j == 0 || j == 7){
						board[i][j] = new Square(new Rook(new Point(i, j), !isWhite));
					} else if(j == 1 || j == 6){
						board[i][j] = new Square(new Knight(new Point(i, j), !isWhite));
					} else if(j == 2 || j == 5){
						board[i][j] = new Square(new Bishop(new Point(i, j), !isWhite));
					} else if(j == 3){
						board[i][j] = new Square(new Queen(new Point(i, j), !isWhite));
					} else {
						board[i][j] = new Square(new King(new Point(i, j), !isWhite));
					}
				} else {
					board[i][j] = new Square(new Empty(new Point(i, j), isWhite));
				}
				
				
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)){
					board[i][j].setColor(new Color(1 - grayFactor, 1 - grayFactor, 1 - grayFactor));
				} else {
					board[i][j].setColor(new Color(grayFactor, grayFactor, grayFactor));
				}
				
				if(i == 4 || i == 5 && j == 4 || j == 5){
					board[i][j].setReward(50);
				} else {
					board[i][j].setReward(2);
				}
			}
			
			
		}
		
		turn = Turn.WHITE;
	}


	public Turn getTurn() {
		return turn;
	}
	
	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public boolean isInBounds(ChessMove move){
		return move.getMove().x < 8 && move.getMove().y < 8 && move.getMove().x >= 0 && move.getMove().y >= 0;
	}

	public boolean isInBounds(int i, int j){
		return i >= 0 && i < 8 && j >= 0 && j < 8;
	}
	
	public Square getSquare(int i, int j){
		if(isInBounds(i,j)){
			return board[i][j];
		}
		return null;
	}
	
	public boolean hasPiece(int i, int j){
		if(this.getPiece(i, j) == null){
			return false;
		}
		
		return !(this.getPiece(i, j).getType() == Piece.EMPTY);
	}
	
	public ChessPiece getPiece(int i, int j){
		if(isInBounds(i,j)){
			return board[i][j].getPiece();
		}
		return null;
	}
	
	public void setPiece(ChessMove move){
		if(isInBounds(move)){
			board[move.getMoveX()][move.getMoveY()].setPiece(move.getPiece());
			
		}
	}
	
	public void removePiece(int i, int j){
		if(isInBounds(i, j)){
			board[i][j].setPiece(new Empty(new Point(i, j), isWhite));
		} 
	}
	
	public Piece getType(int i, int j){
		if(isInBounds(i,j)){
			return board[i][j].getPiece().getType();
		}
		return null;
	}
	
	public Point[] getPoints(Point at, Point to) {
		// TODO Gets all points between at and to.
		ArrayList<Point> pts = new ArrayList<Point>();
		
		int xDiff = Math.abs(at.x - to.x),yDiff = Math.abs(at.y - to.y);
		
		//System.out.println(at + "\n" + to);
		
		if(at.y > to.y && at.x > to.x){
			//System.out.println(">>");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(i + to.x,i + to.y));
			}
			
		} else if (at.y > to.y && at.x < to.x){
			//System.out.println("><");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(to.x - i,i + to.y));
			}
		} else if((at.y < to.y && at.x < to.x)){
			//System.out.println("<<");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(to.x - i,to.y - i));
			}
		} else if(at.y < to.y && at.x > to.x){
			//System.out.println("<>");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(i + to.x, to.y - i));
			}
		} else if((at.y == to.y && at.x > to.x)){
			//System.out.println("==>");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(i + to.x, to.y));
			}
		} else if((at.y == to.y && at.x < to.x)){
			//System.out.println("==<");
			for(int i = 1; i < xDiff; i++){
				pts.add(new Point(to.x - i,to.y));
			}
		} else if((at.y > to.y && at.x == to.x)){
			//System.out.println(">==");
			for(int i = 1; i < yDiff; i++){
				pts.add(new Point(to.x,to.y + i));
			}
		} else if((at.y < to.y && at.x == to.x)){
			//System.out.println("<==");
			for(int i = 1; i < yDiff; i++){
				pts.add(new Point(to.x,to.y - i));
			}
		} 
		
		Point[] ret = new Point[pts.size()];
		int len = 0;
		
		for(Point add: pts){
			ret[len++] = add;
		}
		
		return ret;
	}
	
	public boolean isBlack(int i, int j){
		if(isInBounds(i,j)){
			return board[i][j].getPiece().isBlack();
		}
		return false;
	}
	
	public ChessPiece[] findPiece(Piece piece, boolean isBlack){
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.isBlack(i, j) == isBlack && this.getType(i, j) == piece){
					pieces.add(this.getPiece(i, j));
				}
			}
		}
		
		ChessPiece[] ret = new ChessPiece[pieces.size()];
		int len = 0;
		
		for(ChessPiece add: pieces){
			ret[len++] = add;
		}
		
		
		return ret;
	}
	
	public ChessPiece[][] getAll(boolean isBlack){
		ChessPiece[][] ret = {
				this.findPiece(Piece.PAWN, isBlack),
				this.findPiece(Piece.BISHOP, isBlack),
				this.findPiece(Piece.KNIGHT, isBlack),
				this.findPiece(Piece.ROOK, isBlack),
				this.findPiece(Piece.QUEEN, isBlack),
				this.findPiece(Piece.KING, isBlack) 
			};
		
		return ret;
	}
	
	public ChessPiece[] findCheckingPieces(boolean isBlack) throws KingNotFoundException{
		ArrayList<ChessPiece> checking = new ArrayList<ChessPiece>();
		
		ChessPiece king = this.findPiece(Piece.KING, isBlack)[0];
		
		ChessPiece[][] allLoc = this.getAll(!isBlack);
		
		for(ChessPiece[] loc: allLoc){
			for(ChessPiece piece: loc){
				ChessMove[] validMoves = preMove.determineLegalMoves(piece);
				
				for(ChessMove move: validMoves){
					if(king.getCurrentLocation().equals(move.getMove()) && !obstruction(move)){
						checking.add(piece);
					}
				}
				
			}
		}
		

		ChessPiece[] ret = new ChessPiece[checking.size()];
		int len = 0;
		
		for(ChessPiece move: checking){
			ret[len++] = move;
		}
		
		return ret;
		
	}
	
	public boolean isLegal(ChessMove move){
		
		if((!this.hasPiece(move.getMoveX(), move.getMoveY()) || (this.hasPiece(move.getMoveX(), move.getMoveY()) 
				&& this.isBlack(move.getMoveX(), move.getMoveY()) != move.getPiece().isBlack())) && this.isInBounds(move)){
			return true;
		}
		
		return false;
		
	}
	
	public boolean obstruction(ChessMove move) {
		// TODO Auto-generated method stub
		Point[] toCheck = this.getPoints(move.getCurrentPieceLocation(), move.getMove());
		
		for(Point piecePres: toCheck){
			if(this.hasPiece(piecePres.x, piecePres.y)){
				return true;
			}
		}
		
		return false;
	}
	
	public int count(boolean isBlack) {
		// TODO Auto-generated method stub
		int sum = 0;
		
		ChessPiece[][] allLoc = this.getAll(isBlack);
		
		for(ChessPiece[] loc: allLoc){
			for(@SuppressWarnings("unused") ChessPiece piece: loc){
				sum++;
			}
		}	
		
		return sum;
	}
	
	public ChessPiece[] attackingPieces(Point toCheck, boolean isBlack){
		
		ArrayList<ChessPiece> attacking = new ArrayList<ChessPiece>();
		
		for(ChessPiece[] locs: this.getAll(isBlack)){
			for(ChessPiece piece: locs){
				ChessMove[] moves = piece.getPossibleMoves();
				
				boolean added = false;
				
				for(int i = 0; i < moves.length; i++){
					if(moves[i].getMove().equals(toCheck) && !added && moves[i].getMoveType() != Move.UNCHECKABLE){
							
						if(piece.getType()  == Piece.KNIGHT){
							attacking.add(piece);
							added = true;
						} else if(!obstruction(moves[i])){
							attacking.add(piece);
							added = true;
						}
					}
				}
			}
		}
		
		ChessPiece[] ret = new ChessPiece[attacking.size()];
		
		int ind = 0;
		
		for(ChessPiece toAdd: attacking){
			ret[ind++] = toAdd;
		}
		
		return ret;

	}
	
	public boolean checkForCheck(boolean isBlack){
		return this.attackingPieces(this.findPiece(Piece.KING, isBlack)[0].getCurrentLocation(), !isBlack).length > 0;
	}
	
	public boolean checkAfterMove(ChessMove move){
		
		boolean yes = false;
		
		ChessMove repl = new ChessMove(this.getPiece(move.getMoveX(), move.getMoveY()), move.getMove());
		
		this.setPiece(move);
		this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
		
		if(this.checkForCheck(move.getPiece().isBlack()))
			yes = true;
		
		System.out.println("CHECKED: " + yes);
		
		this.setPiece(move.getReplacing());
		this.setPiece(repl);

		return yes;
	}

	
	public boolean checkForMate(boolean isBlack) throws KingNotFoundException{
		ChessPiece king = this.findPiece(Piece.KING, isBlack)[0];
		
		if(this.checkForCheck(isBlack)){
			boolean move = canMove(isBlack);
			boolean block = canBlock(this.findCheckingPieces(isBlack), king);
			boolean take = canTake(this.findCheckingPieces(isBlack));
			
			//System.out.println("Move: " + move + "\nBlock: " + block + "\nTake: " + take + "\nAll: " + (!(move || block || take)));
			
			return !(move || block || take);
		} else {
			return false;
		}
		
		
	}

	public boolean checkForStaleMate(boolean isBlack) throws KingNotFoundException{
		
		if(!this.checkForCheck(isBlack) && this.count(isBlack) == 1){
			
			return canMove(isBlack);
			
		} else {
			return false;
		}
		
	}
	
	private boolean canTake(ChessPiece[] findCheckingPieces) {
		// TODO Auto-generated method stub
		
		if(findCheckingPieces.length > 1 || findCheckingPieces.length == 0){
			return false;
		} else {
			
			ChessPiece toTake = findCheckingPieces[0];
			
			ChessPiece[][] allLoc = this.getAll(!toTake.isBlack());
			
			for(ChessPiece[] locs : allLoc){
				for(ChessPiece toCheck: locs){
					ChessMove[] legalMoves = preMove.determineLegalMoves(toCheck);
					
					for(ChessMove move: legalMoves){
						if(move.getMove().equals(toTake.getCurrentLocation()) && move.getMoveType() != Move.CASTLE){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	private boolean canBlock(ChessPiece[] findCheckingPieces, ChessPiece king) {
		// TODO Auto-generated method stub
		if(findCheckingPieces.length > 1 || findCheckingPieces.length == 0){
			return false;
		} else {
			ChessPiece toBlock = findCheckingPieces[0];
			
			if(toBlock.getType() == Piece.KNIGHT){
				return false;
			}
			
			ChessPiece[][] allLoc = this.getAll(!toBlock.isBlack());
			
			Point[] points  = this.getPoints(toBlock.getCurrentLocation(), king.getCurrentLocation());
			
			System.out.println(points.length);
			
			for(ChessPiece[] locs : allLoc){
				for(ChessPiece toCheck: locs){
					ChessMove[] legalMoves = preMove.determineLegalMoves(toCheck);
					
					for(ChessMove move: legalMoves){
						for(Point toPlace: points){
							if(move.getMove().equals(toPlace)){
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
		
	}

	private boolean canMove(boolean isBlack) throws KingNotFoundException{
		// TODO Auto-generated method stub
		ChessPiece king = this.findPiece(Piece.KING, isBlack)[0];
		
		ChessMove[] moves = preMove.determineLegalMoves(king);
		
		for(ChessMove toCheck: moves){
			
			if(toCheck.getMoveType() != Move.CASTLE && toCheck.isValid() && !obstruction(toCheck)){
				return true;
			}
		}
		
		return false;
	}

	public void move(ChessMove toMove){
		
		//System.out.println("toMove:" + toMove + "\ntype:" + toMove.getMoveType());
		
		ChessMove move = this.checkMoveValidity(toMove);
		

		if(move != null && move.isValid()){
			//System.out.println("move:" + move + "\ntype:" + move.getMoveType());
			
			if(move.getMoveType() == Move.CASTLE && !((King) move.getPiece()).isCastled()){
				ChessPiece rook;
				ChessMove rookMove = new ChessMove(null, null);
				
				if(move.getMove().equals(new Point(0,2))){
					rook = this.getPiece(0, 0);
					if(rook.getType() != Piece.ROOK){
						return;
					}
					
					rookMove.setMove(new Point(0,3));
					rookMove.setPiece(rook);
					
					this.setPiece(move);
					this.setPiece(rookMove);
					
					this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
					this.removePiece(rookMove.getPreviousPieceLocation().x, rookMove.getPreviousPieceLocation().y);
					
					((King) move.getPiece()).setCastled(true);
									
				} else if(move.getMove().equals(new Point(0,6))){
					rook = this.getPiece(0, 7);
					if(rook.getType() != Piece.ROOK){
						return;
					}
					
					rookMove.setMove(new Point(0,5));
					rookMove.setPiece(rook);
					
					this.setPiece(move);
					this.setPiece(rookMove);
					
					this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
					this.removePiece(rookMove.getPreviousPieceLocation().x, rookMove.getPreviousPieceLocation().y);
					
					((King) move.getPiece()).setCastled(true);
					
				} else if(move.getMove().equals(new Point(7,2))){
					rook = this.getPiece(7, 0);
					if(rook.getType() != Piece.ROOK){
						return;
					}
				
					rookMove.setMove(new Point(7,3));
					rookMove.setPiece(rook);
					
					this.setPiece(move);
					this.setPiece(rookMove);
					
					this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
					this.removePiece(rookMove.getPreviousPieceLocation().x, rookMove.getPreviousPieceLocation().y);
					
					((King) move.getPiece()).setCastled(true);
					
				} else if(move.getMove().equals(new Point(7,6))){
					rook = this.getPiece(7, 7);
					if(rook.getType() != Piece.ROOK){
						return;
					}
					
					rookMove.setMove(new Point(7,5));
					rookMove.setPiece(rook);
					
					this.setPiece(move);
					this.setPiece(rookMove);
					
					this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
					this.removePiece(rookMove.getPreviousPieceLocation().x, rookMove.getPreviousPieceLocation().y);
					
					((King) move.getPiece()).setCastled(true);
					
				} 
				
				turn = Turn.switchTurn(turn);
			} else {
				this.setPiece(move);
				turn = Turn.switchTurn(turn);
				
				this.removePiece(move.getPreviousPieceLocation().x, move.getPreviousPieceLocation().y);
			}
			
			move.getPiece().hasBeenMoved = true;
	
		}
		
		try {
			((King) this.findPiece(Piece.KING, true)[0]).setMate(this.checkForMate(true));
			((King) this.findPiece(Piece.KING, false)[0]).setMate(this.checkForMate(false));
			
		} catch (KingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ChessMove checkMoveValidity(ChessMove toMove){
		
		
		if(toMove.getPiece().isBlack() && turn == Turn.WHITE 
				|| !toMove.getPiece().isBlack() && turn == Turn.BLACK){
			
			return null;
		}
		
		ChessMove[] legalMoves = preMove.determineLegalMoves(toMove.getPiece());
		
		//System.out.println("!" + legalMoves.length);
		
		for(ChessMove move: legalMoves){
			
			if(toMove.equals(move) && !checkAfterMove(move)){
				return move;
			}
		}
		
		return null;
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Legalizer getLegalizer() {
		// TODO Auto-generated method stub
		return preMove;
	}
	
	
	
	
	
}
