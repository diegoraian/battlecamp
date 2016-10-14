package com.game.thebattlecamp.entity;

import java.awt.Rectangle;

import com.game.thebattlecamp.util.Constants;

public class Shot extends Sprite{
	
	public Shot(Double x, Double y) {
		setImagem(Constants.SHOT_SPRITE_LOCATION);
		setX(x);
		setY(y);
	}
	public void moveShot(){
		rectangle = new Rectangle(x.intValue(), y.intValue(), width, height);
		setX(getX() + 10);
	}
}
