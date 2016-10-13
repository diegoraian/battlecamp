package com.game.thebattlecamp.entity;

import java.awt.Rectangle;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class Boss extends EnemyCanon{

	public Boss() {
		setVisible(Boolean.TRUE);
		spriteSheetArray = GameUtils.extractImagesFromSpriteSheet();
		setImage(spriteSheetArray[1]);
		setX((double) Constants.ENEMY_POSITIONS_X[1]);
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
}
