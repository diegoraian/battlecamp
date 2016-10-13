package com.game.thebattlecamp.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.Vector;

public class PlayerCanon extends Sprite{
	
	public int life = 100;
	
	public long timePressed = 0l;
	
	public char lastKeyPressed = ' ';
	
	public Vector vectorVelocity = new Vector(0d, 0d);
	
	public List<Shot> listOfShots = new java.util.Vector<Shot>();
	
	public PlayerCanon()  {
			atribuirImagem(Constants.PLAYER_SPRITE_LOCATION);
			//spriteSheetArray = GameUtils.extractImagesFromPlayerSpriteSheet(Constantes.PLAYER_SPRITE_SHEET_LOCATION);
			//Image imagem = spriteSheetArray[spriteState];
			//setImage(imagem);
			setX(10.0);
			setY(Constants.CANVAS_HEIGHT.doubleValue() / 2);
            super.setVisible(true);
	}
	
	public void moveY(){
		if(lastKeyPressed == 'U' || lastKeyPressed == '9' || lastKeyPressed == '7'){
			y -= vectorVelocity.getY();	
		}else{
			y += vectorVelocity.getY();
		}
		if (y < 10d ) y = 10d;
        if (y >= Constants.CANVAS_HEIGHT - 2*height) y = Constants.CANVAS_HEIGHT - 2.0*height;
        vectorVelocity.setY(Math.exp(-0.5d*vectorVelocity.getY())  - 0.02); 
	}
	
	public void moveX(){
		if(lastKeyPressed == 'L' || lastKeyPressed == '7' || lastKeyPressed == '1' ){
			x -= vectorVelocity.getX();	
		}else{
			x += vectorVelocity.getX();
		}
		if (x < 10d) x = 10d;
        if (x >= Constants.CANVAS_WIDTH - 2*width) x = Constants.CANVAS_WIDTH - 2.0*width;
        vectorVelocity.setX(Math.exp(-0.5d*vectorVelocity.getX()) - 0.02); 
	}
	
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
        	dy = -5;
        	lastKeyPressed = 'U';
        	vectorVelocity.setY((double) dy);
        }
        
        if (key == KeyEvent.VK_DOWN){
        	dy = 5;
        	lastKeyPressed = 'D';
        	vectorVelocity.setY((double) dy);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	dx = 2;
        	lastKeyPressed = 'R';
        	vectorVelocity.setX((double) dx);
        }
        
        if (key == KeyEvent.VK_LEFT){
        	dx = -5;
        	lastKeyPressed = 'L';
        	vectorVelocity.setX((double) dx);
        }

        if (key == KeyEvent.VK_SHIFT){
        	vectorVelocity.setX(10d);
        	vectorVelocity.setY(10d);
        }
        
        if (key == KeyEvent.VK_PAGE_UP  ){
        	dy = -5;
        	dx = 2;
        	lastKeyPressed = '9';
        	vectorVelocity.setX(10d);
        	vectorVelocity.setY(10d);
        }
        
        if (key == KeyEvent.VK_PAGE_DOWN){
        	dy = 5;
        	dx = -2;
        	lastKeyPressed = '3';
        	vectorVelocity.setX(10d);
        	vectorVelocity.setY(10d);
        }
        
        if (key == KeyEvent.VK_END){
        	dy = -5;
        	dx = -2;
        	lastKeyPressed = '1';
        	vectorVelocity.setX(10d);
        	vectorVelocity.setY(10d);
        }
        if (key == KeyEvent.VK_HOME){
        	dy = -5;
        	dx = -5;
        	lastKeyPressed = '7';
        	vectorVelocity.setX(10d);
        	vectorVelocity.setY(10d);
        }
        if (key == KeyEvent.VK_SPACE){
        	useGun();
        }        
        
    }
	
    public void moveUpDown(){
        y += dy;
        if (y <= 2) y = 5.0;
        if (y >= Constants.CANVAS_HEIGHT - 2*height) y = Constants.CANVAS_HEIGHT - 2.0*height;
	}
	
	
	public void moveLeftRight() {
		x += dx;
        if (x <= 2) x = 5.0;
        if (x >= Constants.CANVAS_WIDTH - 2*width) x = Constants.CANVAS_WIDTH - 2.0*width;
	}


	private void useGun() {
		Shot shot = new Shot(getX(), getY());
		listOfShots.add(shot);
	}
	
	public Boolean colision(EnemyCanon enemy){
		if(rectangle.intersects(enemy.rectangle)) {
			enemy.setVisible(Boolean.FALSE);
			setDying(true);
			setVisible(false);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
