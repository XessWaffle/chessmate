package graphics;

import java.awt.Rectangle;

public class WinPotentialBar {
	private Rectangle white;
	private Rectangle black;
		
	public static final int DEFAULT_WIDTH = 10;
	
	public WinPotentialBar(){
		white = new Rectangle(400, 0, 20, 200);
		black = new Rectangle(400, 200, 20, 200);
	}

	public void setPercentage(float percentageWhite) {
		white.setBounds(400, 0, (int)(percentageWhite * 400), 200);
		black.setBounds(400, 0, (int)((1.0 - percentageWhite) * 400), 200);
	}

	public Rectangle getBlackRectangle() {
		return black;
	}
	
	public int getBlackRectangleX() {
		return black.x;
	}
	
	public int getBlackRectangleY() {
		return black.y;
	}
	
	public int getBlackRectangleHeight() {
		return black.height;
	}
	
	public Rectangle getWhiteRectangle() {
		return white;
	}
	
	public int getWhiteRectangleX() {
		return white.x;
	}
	
	public int getWhiteRectangleY() {
		return white.y;
	}
	
	public int getWhiteRectangleHeight() {
		return white.height;
	}
}
