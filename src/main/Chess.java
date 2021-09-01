package main;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ai.AIThread;
import move.Turn;
import graphics.Images;
import graphics.Window;
import io.ChessAdapter;
import piece.Bishop;
import piece.Knight;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import position.Board;

public class Chess {

	private JFrame frame;
	
	private static ChessAdapter list;
	private static Window view;
	private static Board board;
	private static Images images;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chess window = new Chess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Chess() {
		
		runSetup();
		
		initialize();
		
		//validate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 620, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		view.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(view);
		view.add(view.getMoveTable().getScrollPaneBlack());
		view.add(view.getMoveTable().getScrollPaneWhite());
		
		view.getImages().playSound(1);
		
		addChessListener(view);
		
		initializeMenus();
		initializeButtons();
		
	}
	
	private void initializeButtons() {
		// TODO Auto-generated method stub
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				view.getPosition().reset();
				view.repaint();
				view.getMoveTable().clear();
				((ChessAdapter) view.getMouseListeners()[0]).setIOAllow(true);
			}
		});
		btnReset.setBounds(460, 250, 100, 20);
		view.add(btnReset);
		
		JButton btnAI = new JButton("AIGame");
		btnAI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AIThread nThread = new AIThread(view);
				nThread.start();
			}
		});
		btnAI.setBounds(460,300,100,20);
		view.add(btnAI);
		
		
	}

	private static void addChessListener(Component comp){
		list = new ChessAdapter(view);
		
		comp.addMouseListener(list);
		comp.addMouseMotionListener(list);
	}
	
	private static void runSetup(){
		/*
			Setup setup = new Setup();
			setup.start();
			
			while(setup.getPosition() == null || setup.getImageRetriever() == null);
			
			board = setup.getPosition();
			images = setup.getImageRetriever();
			
			view = new Window(board, images);
			*/
		
		board = new Board();
		images = new Images();
		
		view = new Window(board, images);
		
	}
	
	private void initializeMenus() {
		// TODO Auto-generated method stub
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(view, popupMenu);
		
		JMenuItem mntmQueen = new JMenuItem("Queen");
		mntmQueen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++){
					if(view.getPosition().getTurn() == Turn.WHITE){
						if(board.getSquare(7, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(7, i).setPiece(new Queen(new Point(7, i), true));
						}
					} else {
						if(board.getSquare(0, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(0, i).setPiece(new Queen(new Point(0, i), false));
						}
					}
				}
				list.paintCheck();
			}
		});
		popupMenu.add(mntmQueen);
		
		JMenuItem mntmRook = new JMenuItem("Rook");
		mntmRook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++){
					if(view.getPosition().getTurn() == Turn.WHITE){
						if(board.getSquare(7, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(7, i).setPiece(new Rook(new Point(7, i), true));
						}
					} else {
						if(board.getSquare(0, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(0, i).setPiece(new Rook(new Point(0, i), false));
						}
					}
				}
				list.paintCheck();
			}
		});
		popupMenu.add(mntmRook);
		
		JMenuItem mntmKnight = new JMenuItem("Knight");
		mntmKnight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++){
					if(view.getPosition().getTurn() == Turn.WHITE){
						if(board.getSquare(7, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(7, i).setPiece(new Knight(new Point(7, i), true));
						}
					} else {
						if(board.getSquare(0, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(0, i).setPiece(new Knight(new Point(0, i), false));
						}
					}
				}
				list.paintCheck();
			}
		});
		popupMenu.add(mntmKnight);
		
		JMenuItem mntmBishop = new JMenuItem("Bishop");
		mntmBishop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++){
					if(view.getPosition().getTurn() == Turn.WHITE){
						if(board.getSquare(7, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(7, i).setPiece(new Bishop(new Point(7, i), true));
						}
					} else {
						if(board.getSquare(0, i).getPiece().getType() == Piece.PAWN){
							board.getSquare(0, i).setPiece(new Bishop(new Point(0, i), false));
						}
					}
				}
				list.paintCheck();
			}
		});
		popupMenu.add(mntmBishop);
	}

	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
