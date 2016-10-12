package com.game.thebattlecamp.entity;

import javax.swing.ImageIcon;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class EnemyCanon extends Sprite {
	
	public EnemyCanon(){
		ImageIcon icon = new ImageIcon(this.getClass().getResource(Constantes.PLAYER_SPRITE_LOCATION));
		setImage(icon.getImage());
		int index = GameUtils.randomizeStartPlayerPosition();
		setX(Constantes.PLAYER_POSITIONS_X[index]);
		setY(Constantes.PLAYER_POSITIONS_Y[index]);
	}
	
	
}
