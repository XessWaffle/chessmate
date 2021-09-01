package graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import piece.Piece;

public class Images {
	
	private BufferedImage[] images;
	private Clip[] sounds;
	
	public Images(){
		loadImages();
	}
	
	public void playSound(int index){
		
		loadSounds();
		
		sounds[index].start();
			
	}

	private void loadImages() {
		// TODO Auto-generated method stub
		
		images = new BufferedImage[15];
		sounds = new Clip[2];
		
		loadKing();
		loadQueen();
		loadRook();
		loadKnight();
		loadBishop();
		loadPawn();
		loadOthers();
		loadSounds();
	}
	
	private void loadSounds() {
		// TODO Auto-generated method stub
		try{
			sounds[0] = AudioSystem.getClip();
			sounds[0].open(AudioSystem.getAudioInputStream(new File("img\\snd\\trash.wav")));
			
			sounds[1] = AudioSystem.getClip();
			sounds[1].open(AudioSystem.getAudioInputStream(new File("img\\snd\\startup.wav")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadOthers() {
		// TODO Auto-generated method stub
		try{
			
			images[12] = (ImageIO.read(new File("img\\misc\\identifier.gif")));	
			images[13] = (ImageIO.read(new File("img\\misc\\check.gif")));	
			images[14] = (ImageIO.read(new File("img\\misc\\hover.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadPawn() {
		// TODO Auto-generated method stub
		try{
			
			images[11] = (ImageIO.read(new File("img\\black\\BlackPawn.gif")));		
			images[10] = (ImageIO.read(new File("img\\white\\WhitePawn.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadBishop() {
		// TODO Auto-generated method stub
		try{
			
			images[9] = (ImageIO.read(new File("img\\black\\BlackBishop.gif")));		
			images[8] = (ImageIO.read(new File("img\\white\\WhiteBishop.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadKnight() {
		// TODO Auto-generated method stub
		try{
			
			images[7] = (ImageIO.read(new File("img\\black\\BlackKnight.gif")));		
			images[6] = (ImageIO.read(new File("img\\white\\WhiteKnight.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadRook() {
		// TODO Auto-generated method stub
		try{
			
			images[5] = (ImageIO.read(new File("img\\black\\BlackRook.gif")));		
			images[4] = (ImageIO.read(new File("img\\white\\WhiteRook.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadQueen() {
		// TODO Auto-generated method stub
		try{
			
			images[3] = (ImageIO.read(new File("img\\black\\BlackQueen.gif")));		
			images[2] = (ImageIO.read(new File("img\\white\\WhiteQueen.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadKing() {
		// TODO Auto-generated method stub
		
		try{
			
			images[1] = (ImageIO.read(new File("img\\black\\BlackKing.gif")));		
			images[0] = (ImageIO.read(new File("img\\white\\WhiteKing.gif")));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public BufferedImage getImage(Piece type, boolean isBlack){
		
		int index = Piece.convertToIndexLocation(type);
		
		if(isBlack){
			return images[index + 1];
		} else {
			return images[index];
		}
	}
	
	public BufferedImage getImage(int index){
		return images[index];
	}
}
