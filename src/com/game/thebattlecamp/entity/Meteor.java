package com.game.thebattlecamp.entity;

import java.awt.image.BufferedImage;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class Meteor extends Sprite{
	
	public Meteor() {
		setVisible(Boolean.TRUE);
		spriteSheetArray = GameUtils.extractImagesFromAnySpriteSheet(Constants.METEOR_SPRITE, 128, 128, 2, 2);
		setImage(spriteSheetArray[1]);
		setImagem(Constants.METEOR_SPRITE);
		setX((double) Constants.ENEMY_POSITIONS_X[1]);
		setY(GameUtils.randomizeEnemiesPosition().doubleValue());
	}
	public void move(){
        x -= 2 ;
        	
       new Thread(new Runnable(){
        	private int i = 0;
			@Override
			public void run() {
		        if(i < 4){
		        	setImage(spriteSheetArray[i++]);
		        }
		       try {
				Thread.sleep(800l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 	
		}}).start();;
        
        if (x <= 10) {
        	x = Constants.CANVAS_WIDTH + 2.0;
        	y = GameUtils.randomizeEnemiesPosition().doubleValue();
        }
        setLimits();
	}
	

	
}
