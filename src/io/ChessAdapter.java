package io;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import graphics.Window;
import move.ChessMove;
import move.Turn;
import piece.ChessPiece;
import piece.King;
import piece.Piece;

public class ChessAdapter extends MouseAdapter{
	
	private ChessPiece piece;
	
	private ChessMove generate;
	private ChessMove[] toDraw;
	
	
	private Window view;
	
	private boolean disabled = false;
	
	public ChessAdapter(Window position){
		this.view = position;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(!disabled){
			Point at = arg0.getPoint();
			
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if((i * Window.DEFAULT_SIZE < at.x && (i + 1) * Window.DEFAULT_SIZE > at.x) 
							&& (j * Window.DEFAULT_SIZE < at.y && (j + 1) * Window.DEFAULT_SIZE > at.y)){
						piece = view.getPosition().getPiece(j, i);
					}
				}
			}
		}
		
		if(piece != null && piece.isBlack() && view.getPosition().getTurn() == Turn.WHITE){
			piece = null;
		} else if(piece != null && !piece.isBlack() && view.getPosition().getTurn() == Turn.BLACK){
			piece = null;
		} else {
			toDraw = view.getPosition().getLegalizer().determineLegalMoves(piece);
		}
	}

	
	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(!disabled && piece != null){
			Point to = this.findPoint(arg0.getPoint());
			
			generate = new ChessMove(piece,to);
			
			boolean piecePresent = view.getPosition().hasPiece(generate.getMoveX(), generate.getMoveY());
			
					
			if(piece.getType() != Piece.EMPTY && !piece.getCurrentLocation().equals(to)){
				
				view.getPosition().move(generate);
				
				view.getMoveTable().writeMove(view.getPosition().getTurn(), view.generateMoveString(generate, piecePresent));
			}
			
			paintCheck();
			
			try{
				if(((King)view.getPosition().findPiece(Piece.KING, true)[0]).isMated() 
						|| ((King)view.getPosition().findPiece(Piece.KING, false)[0]).isMated()){
					setIOAllow(false);
				}
			} catch (Exception e){
				System.out.println("King not found");
			} 
		}
	}
	
	public void paintCheck(){
		
		view.update(view.getGraphics());
		
		if(view.getPosition().checkForCheck(true)){
			view.paintCheck(view.getGraphics(), true);
		} else if(view.getPosition().checkForCheck(false)){
			view.paintCheck(view.getGraphics(), false);
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(piece != null && piece.getType() != Piece.EMPTY && !disabled && toDraw != null){
			
			view.drawPossibleMoves(view.getGraphics(), toDraw, this.findPoint(e.getPoint()));
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void setIOAllow(boolean allow){
		disabled = !allow;
	}
	
	public Point findPoint(Point toCheck){
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if((i * Window.DEFAULT_SIZE < toCheck.x && (i + 1) * Window.DEFAULT_SIZE > toCheck.x) 
						&& (j * Window.DEFAULT_SIZE < toCheck.y && (j + 1) * Window.DEFAULT_SIZE > toCheck.y)){
					toCheck = new Point(j,i);
				}
			}
		}
		
		return toCheck;
	}

	
	
}
