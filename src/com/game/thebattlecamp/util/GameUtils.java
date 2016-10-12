package com.game.thebattlecamp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class GameUtils {

	private  GameUtils() {
	}

	public static int randomizeStartPosition(int playerPositions) {
		return new Random().nextInt(playerPositions);
	}
	
	public static BufferedImage[] extractImagesFromSpriteSheet(String sheet){
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
}
