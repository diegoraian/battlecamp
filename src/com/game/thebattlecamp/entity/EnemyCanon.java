package com.game.thebattlecamp.entity;


import java.awt.Rectangle;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class EnemyCanon extends Sprite {
	
	public EnemyCanon(){
		atribuirImagem(Constantes.ENEMY_SPRITE_LOCATION);
		setX((double) Constantes.ENEMY_POSITIONS_X[1]);
		setY(GameUtils.randomizeEnemiesPosition().doubleValue());
	}
	
	public void moveFromRightToLeft(){
		rectangle = new Rectangle(x.intValue(), y.intValue(), width, height);
        x -= 0.5 ;
        y = 4*Math.sin(0.2*x) + y ;
        if (x <= 10) {
        	x = -2.0;
        	setVisible(false);
        }
	}

	public Boolean colision(Shot shot){
		if(rectangle.intersects(shot.rectangle)) {
			setDying(true);
			setVisible(false);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
}
