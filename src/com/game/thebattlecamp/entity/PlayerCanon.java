package com.game.thebattlecamp.entity;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.game.thebattlecamp.util.Constantes;

public class PlayerCanon extends Sprite{
	
	public int life = 100;
	
	public long timePressed =0l;
	
	public List<Shot> listOfShots = new ArrayList<Shot>();
	
	public PlayerCanon()  {
			atribuirImagem(Constantes.PLAYER_SPRITE_LOCATION);
			//spriteSheetArray = GameUtils.extractImagesFromPlayerSpriteSheet(Constantes.PLAYER_SPRITE_SHEET_LOCATION);
			//Image imagem = spriteSheetArray[spriteState];
			
//			setImage(imagem);
			setX(10.0);
			setY(Constantes.CANVAS_HEIGHT.doubleValue() / 2);
            super.setVisible(true);
	}
	

	public void moveUpDown(){
        y += dy;
        if (y <= 2) y = 2.0;
        if (y >= Constantes.CANVAS_HEIGHT - 2*height) y = Constantes.CANVAS_HEIGHT - 2.0*height;
        
	}
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP) {
        	dy = -10;
        	moveUpDown();
        }
        
        if (key == KeyEvent.VK_DOWN){
        	dy = 10;
        	moveUpDown();
        }

        if (key == KeyEvent.VK_SPACE){
        	useGun();
        }        
    }

	private void useGun() {
		Shot shot = new Shot(getX(), getY());
		listOfShots.add(shot);
	}

}
