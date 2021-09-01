package graphics;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import move.Turn;

public class MoveTable{
	private JTextArea blackMoves;
	private JTextArea whiteMoves;
	private JScrollPane scrollPaneBlack;
	private JScrollPane scrollPaneWhite;
	
	
	public static final int DEFAULT_X = 450;
	public static final int DEFAULT_Y = 10;
	public static final int DEFAULT_HEIGHT = 200;
	public static final int DEFAULT_WIDTH = 60;
	
	public MoveTable(){
		
		setBlackMoves(new JTextArea());
		setWhiteMoves(new JTextArea());
		
		scrollPaneBlack = new JScrollPane();
		scrollPaneBlack.setBounds(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		scrollPaneWhite = new JScrollPane();
		scrollPaneWhite.setBounds(DEFAULT_X + 60, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		blackMoves.setName("Black");
		whiteMoves.setName("White");
		
		scrollPaneBlack.setViewportView(blackMoves);
		scrollPaneWhite.setViewportView(whiteMoves);
	
	}
	
	public void writeMove(Turn turn, String generatedMove){
		if(turn == Turn.BLACK){
			blackMoves.append(generatedMove + "\n");
		} else {
			whiteMoves.append(generatedMove + "\n");
		}
	}
	
	public void clear(){
		blackMoves.setText("");
		whiteMoves.setText("");
	}
	
	public JScrollPane getScrollPaneBlack() {
		return scrollPaneBlack;
	}

	public void setScrollPaneBlack(JScrollPane scrollPaneBlack) {
		this.scrollPaneBlack = scrollPaneBlack;
	}

	public JScrollPane getScrollPaneWhite() {
		return scrollPaneWhite;
	}

	public void setScrollPaneWhite(JScrollPane scrollPaneWhite) {
		this.scrollPaneWhite = scrollPaneWhite;
	}

	public JTextArea getBlackMoves() {
		return blackMoves;
	}

	public void setBlackMoves(JTextArea blackMoves) {
		this.blackMoves = blackMoves;
	}

	public JTextArea getWhiteMoves() {
		return whiteMoves;
	}

	public void setWhiteMoves(JTextArea whiteMoves) {
		this.whiteMoves = whiteMoves;
	}
}
