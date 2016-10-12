package com.game.thebattlecamp.entity;

import java.net.URL;

import javax.swing.ImageIcon;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class EnemyCanon extends Sprite {
	
	public EnemyCanon(){
		atribuirImagem(Constantes.ENEMY_SPRITE_LOCATION);
		int index = GameUtils.randomizeStartPosition(Constantes.ENEMY_POSITIONS_X.length);
		setX(Constantes.ENEMY_POSITIONS_X[index]);
		setY(Constantes.ENEMY_POSITIONS_Y[index]);
	}
	
	
}
