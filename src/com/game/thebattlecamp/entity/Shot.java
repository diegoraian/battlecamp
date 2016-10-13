package com.game.thebattlecamp.entity;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class Shot extends Sprite{
	
	public Shot(Integer x, Integer y) {
		atribuirImagem(Constantes.SHOT_SPRITE_LOCATION);
		setX(x);
		setY(y);
	}
	public void moveShot(){
		setX(getX() + 10);
	}
}
