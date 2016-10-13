package com.game.thebattlecamp.entity;

import java.awt.Rectangle;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class Shot extends Sprite{
	
	public Shot(Double x, Double y) {
		atribuirImagem(Constantes.SHOT_SPRITE_LOCATION);
		setX(x);
		setY(y);
	}
	public void moveShot(){
		rectangle = new Rectangle(x.intValue(), y.intValue(), width, height);
		setX(getX() + 10);
	}
}
