package com.game.thebattlecamp.entity;

import java.awt.event.KeyEvent;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class PlayerCanon extends Sprite{
	
	public int life = 100;
	
	public long timePressed =0l;
	
	public PlayerCanon()  {
			atribuirImagem(Constantes.PLAYER_SPRITE_LOCATION);
			Integer[] playerPositions = Constantes.PLAYER_POSITIONS_X;
			spriteSheetArray = GameUtils.extractImagesFromSpriteSheet(Constantes.PLAYER_SPRITE_SHEET_LOCATION);
			setImage(spriteSheetArray[spriteState]);
			int index = GameUtils.randomizeStartPosition(playerPositions.length);
			setX(playerPositions[index]);
			setY(playerPositions[index]);
			super.setVisible(true);
	}
	
	public void moveLeftRight(){
        x += dx;
        if (x <= 2) x = 2;
        if (x >= Constantes.CANVAS_WIDTH - 2*width) x = Constantes.CANVAS_WIDTH - 2*width;
        spriteState = 1;
	}

	public void moveUpDown(){
        y += dy;
        if (y <= 2) y = 2;
        if (y >= Constantes.CANVAS_HEIGHT - 2*height) y = Constantes.CANVAS_HEIGHT - 2*height;
        
	}
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	dx = -2;
        	setImage(spriteSheetArray[1]); 
        	moveLeftRight();
        	
        } if (key == KeyEvent.VK_RIGHT) {
        	dx = 2;
        	setImage(spriteSheetArray[2]); 
        	moveLeftRight();
        }
        
        if (key == KeyEvent.VK_UP) {
        	dy = -2;
        	setImage(spriteSheetArray[3]); 
        	moveUpDown();
        }
        
        if (key == KeyEvent.VK_DOWN){
        	dy = 2;
        	setImage(spriteSheetArray[4]); 
        	moveUpDown();
        }

        
    }

	private void useBomb() {
		
	}

}
