package com.game.thebattlecamp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameUtils {

	private  GameUtils() {
	}

	public static int randomizeStartPosition(int playerPositions) {
		return new Random().nextInt(playerPositions);
	}
	
	
	public static Integer randomizeEnemiesPosition() {
		Integer valor = new Random().nextInt(Constants.CANVAS_HEIGHT - 80 );
		//while (valor < 30 || valor > Constants.CANVAS_HEIGHT - 20 ){
//			valor = new Random().nextInt(Constants.CANVAS_HEIGHT);
		//}
		return valor;
	}
	
	
	public static BufferedImage[] extractImagesFromSpriteSheet() {
		try {
			final String sheet = Constants.BOSS_SPRITE;
			final int width = 173;
			final int height = 158;
			final int rows = 3 ;
			final int cols = 4;
			BufferedImage bigImg = null;	
			bigImg = ImageIO.read(new File(extractURLFromString(sheet).getPath()));
	
			BufferedImage[] sprites = new BufferedImage[rows * cols];
	
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}
			return sprites;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage[] extractImagesFromPlayerSpriteSheet(String sheet){
		try {
			final int width = 10;
			final int height = 130;
			final int rows = 3;
			final int cols = 7;
			
			BufferedImage bigImg = null;	
			bigImg = ImageIO.read(new File(extractURLFromString(sheet).getPath()));
	
			BufferedImage[] sprites = new BufferedImage[rows * cols];
	
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}
			return sprites;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage[] extractImagesFromEnemySpriteSheet(String sheet){
		try {
			final int width = 95;
			final int height = 130;
			final int rows = 3;
			final int cols = 7;
			
			BufferedImage bigImg = null;	
			bigImg = ImageIO.read(new File(extractURLFromString(sheet).getPath()));
	
			BufferedImage[] sprites = new BufferedImage[rows * cols];
	
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);
			    }
			}
			return sprites;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public Boolean isNotNullOrEmpty(Object obj){
		if(null !=  obj ){
			if("" != obj ){
				return  Boolean.TRUE;
			};	
		}
		return Boolean.FALSE;
	}
	
	public static URL extractURLFromString(String caminhoImagem){
	 	ClassLoader classLoader = GameUtils.class.getClassLoader();
		return classLoader.getResource(caminhoImagem); 
	}
	
	public static Boolean compareColision(Double x1,Double y1,Double x2, Double y2,int width, int height){
		if(x1 == x2 && y1 < (y2+height) && y1 > (y2-height) ) return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		       Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(extractURLFromString(url));
		        clip.open(inputStream);
		        clip.start();
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
    }
		  }).start();
	}

	public static Integer randomizeEnemiesAmount() {
		return new Random().nextInt(Constants.AMOUNT_EASY);		
	}

}
