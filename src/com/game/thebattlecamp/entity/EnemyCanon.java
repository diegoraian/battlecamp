package com.game.thebattlecamp.entity;


import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class EnemyCanon extends Sprite {
	
	public EnemyCanon(){
		atribuirImagem(Constantes.ENEMY_SPRITE_LOCATION);
		int index = GameUtils.randomizeStartPosition(Constantes.ENEMY_POSITIONS_X.length);
		setX(Constantes.ENEMY_POSITIONS_X[index]);
		setY(GameUtils.randomizeEnemiesPosition());
	}
	
	public void moveFromRightToLeft(){
        x -= 0.5;
        if (x <= 10) {
        	x = -2;
        	setVisible(false);
        }
	}

	public Boolean colision(Shot shot){
		if(GameUtils.compareColision(shot.getX(),shot.getY(),getX(),getY())) {
			setDying(true);
			setVisible(false);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
}
