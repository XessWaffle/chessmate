package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import move.ChessMove;
import piece.ChessPiece;
import piece.King;
import piece.Piece;
import position.Board;

public class Window extends JComponent{
	
	/**
	 * Can be written and read according to number.
	 */
	private static final long serialVersionUID = 4175571884426348359L;
	
	private WinPotentialBar winBar;
	private MoveTable mvTable;
	
	private Board toPlay;
	private Images images;
	
	public static final int DEFAULT_SIZE = 50;
	
	public Window(Board toPlay, Images images){
		
		setPosition(toPlay);
		setImages(images);
		setWinBar(new WinPotentialBar());
		setMoveTable(new MoveTable());
		
	}
	
	public void paintComponent(Graphics g){
		paintBoard(g);
		drawWinBar(g);
	}
	
	public void paintBoard(Graphics g){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				this.paintSquare(g, j, i, i * DEFAULT_SIZE, j * DEFAULT_SIZE);
				
				if(toPlay.hasPiece(j, i)){
					this.paintPiece(g, images.getImage(toPlay.getType(j, i), toPlay.isBlack(j, i)), i * DEFAULT_SIZE, j * DEFAULT_SIZE);
				}
			}
		}
	}
	
	public void drawWinBar(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(winBar.getBlackRectangleX(), winBar.getBlackRectangleY(), WinPotentialBar.DEFAULT_WIDTH, winBar.getBlackRectangleHeight());

		g.setColor(Color.WHITE);
		g.fillRect(winBar.getWhiteRectangleX(), winBar.getWhiteRectangleY(), WinPotentialBar.DEFAULT_WIDTH, winBar.getWhiteRectangleHeight());
		
	}
	
	public void paintPiece(Graphics g, BufferedImage piece, int xLoc, int yLoc){
		g.drawImage(piece, xLoc, yLoc, null);
	}
	
	public void drawPossibleMoves(Graphics g, ChessMove[] toDraw, Point hovering){
		System.out.println("WIN" + toDraw.length);
		
		for(ChessMove move: toDraw){
			
			System.out.println(move);
			System.out.println(move.getMove());
			//System.out.println(move.getPiece());
			if(move.getMove().equals(hovering)){
				g.drawImage(images.getImage(14), DEFAULT_SIZE * move.getMoveY(), DEFAULT_SIZE * move.getMoveX(), null);
			} else {
				g.drawImage(images.getImage(12), DEFAULT_SIZE * move.getMoveY(), DEFAULT_SIZE * move.getMoveX(), null);
			}
		}
	}
	
	public void paintCheck(Graphics g, boolean isBlack){
		ChessPiece king = toPlay.findPiece(Piece.KING, isBlack)[0];
		
		System.out.println("King: " + king.getCurrentLocation());
		
		g.drawImage(images.getImage(13), DEFAULT_SIZE * king.getCurrentLocation().y, DEFAULT_SIZE * king.getCurrentLocation().x, null);
		this.paintPiece(g, images.getImage(Piece.KING, isBlack), DEFAULT_SIZE * king.getCurrentLocation().y, DEFAULT_SIZE * king.getCurrentLocation().x);
		
	}
	
	public void paintSquare(Graphics g, int i, int j, int xLoc, int yLoc){
		g.setColor(toPlay.getSquare(i, j).getColor());
		g.fillRect(xLoc, yLoc, DEFAULT_SIZE, DEFAULT_SIZE);
	}
	
	public String moveToString(Point move) {
		// TODO Auto-generated method stub		
		return ((char)(move.y + 97)) + "" + (8 - move.x);
	}
	
	public String generateMoveString(ChessMove dev, boolean hasPiece) {
		// TODO Auto-generated method stub
		
		if(hasPiece){
			images.playSound(0);
		}
		
		String move = dev.toString();
	
		if(hasPiece){
			move += "x";
			
		}
		
		move += this.moveToString(dev.getMove());
		
		if(((King)getPosition().findPiece(Piece.KING, true)[0]).isMated() 
				|| ((King)getPosition().findPiece(Piece.KING, false)[0]).isMated()){
			
			images.playSound(0);
			
			move += "#";
		} else if(toPlay.checkForCheck(true) || toPlay.checkForCheck(false)){
			
			images.playSound(0);
			
			move += "+";
			//view.repaint();
		} 
		
		if(!dev.getPiece().hasBeenMoved)
			if(move.equals("Kg1") || move.equals("Kg8")){
				move = "O - O";
			} else if(move.equals("Kc1") || move.equals("Kc8")){
				move = "O - O - O";
			}
			
	
		
		return move;
	}


	public Board getPosition() {
		return toPlay;
	}

	public void setPosition(Board toPlay) {
		this.toPlay = toPlay;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public MoveTable getMoveTable() {
		return mvTable;
	}

	public void setMoveTable(MoveTable mvTable) {
		this.mvTable = mvTable;
	}

	public WinPotentialBar getWinBar() {
		return winBar;
	}

	public void setWinBar(WinPotentialBar winBar) {
		this.winBar = winBar;
	}
	
	public void setWinRating(int rating){
		winBar.setPercentage(convertToPercentage(rating));
	}

	private float convertToPercentage(int rating) {
		return (float) ((double)(rating + 150) / 300.0);
	}
	

}
