package com.game.thebattlecamp.entity;


import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class EnemyCanon extends Sprite {
	
	public EnemyCanon(){
		setImagem(Constants.ENEMY_SPRITE_LOCATION);
		setX((double) Constants.ENEMY_POSITIONS_X[1]);
		setY(GameUtils.randomizeEnemiesPosition().doubleValue());

	}
	
	public void moveFromRightToLeft(){
		
        x -= 0.5 ;
        y = 4*Math.sin(0.2*x) + y ;
        if (x <= 10) {
        	x = Constants.CANVAS_WIDTH + 2.0;
        }
        setLimits();
	}

	public Boolean colision(Shot shot){
		if(rectangle.intersects(shot.rectangle)) {
			shot.setVisible(Boolean.FALSE);
			setDying(true);
			setVisible(false);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
}
