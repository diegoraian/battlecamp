package com.game.thebattlecamp.entity;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class Meteor extends Sprite{
	private Integer life = 3;
	
	private int i = 0;
	
	public Meteor() {
		setVisible(Boolean.TRUE);
		spriteSheetArray = GameUtils.extractImagesFromAnySpriteSheet(Constants.METEOR_SPRITE, 128, 128, 2, 2);
		setImage(spriteSheetArray[0]);
		setImagem(Constants.METEOR_SPRITE);
		width = 128;
		height = 128;
		setX((double) Constants.ENEMY_POSITIONS_X[1]);
		setY(GameUtils.randomizeEnemiesPosition().doubleValue());
	}
	public void move(){
        x -= 2 ;
       new Thread(new Runnable(){

		@Override
			public void run() {
			if(x >= Constants.CANVAS_WIDTH) life = 3;
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
        	life = 3;
        	setVisible(Boolean.TRUE);
        }
        
        setLimits();
	}


	@Override
	public Boolean colision(Sprite sprite){
		if(rectangle.intersects(sprite.rectangle)) {
			sprite.setVisible(Boolean.FALSE);
			life--;
			if(life == 0){
				setVisible(false);
			}
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Integer getLife() {
		return life;
	}
	
	public void setLife(Integer life) {
		this.life = life;
	}
}
