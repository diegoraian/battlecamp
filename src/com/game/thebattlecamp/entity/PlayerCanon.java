package com.game.thebattlecamp.entity;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class PlayerCanon extends Sprite{
	
	public int life = 100;
	
	public PlayerCanon()  {
			URL resource;
		 	ClassLoader classLoader = getClass().getClassLoader();
			 resource = classLoader.getResource(Constantes.PLAYER_SPRITE_LOCATION);			 
			ImageIcon icon = new ImageIcon(resource);
			width = icon.getImage().getWidth(null);
			height = icon.getImage().getHeight(null);
			setImage(icon.getImage());
			int index = GameUtils.randomizeStartPlayerPosition();
			setX(Constantes.PLAYER_POSITIONS_X[index]);
			setY(Constantes.PLAYER_POSITIONS_Y[index]);
			super.setVisible(true);
	}
	
	public void move(){
        x += dx;
        if (x <= 2) x = 2;
        if (x >= Constantes.CANVAS_WIDTH - 2*width) x = Constantes.CANVAS_WIDTH - 2*width;
        y += dy;
        if (y <= 2) y = 2;
        if (y >= Constantes.CANVAS_HEIGHT - 2*height) y = Constantes.CANVAS_HEIGHT - 2*height;
	}
	
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) dx = -2;

        if (key == KeyEvent.VK_RIGHT) dx = 2;
        
        if (key == KeyEvent.VK_UP) dy = 2;
        
        if (key == KeyEvent.VK_DOWN) dy = -2;
        
        move();

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) dx = 0;

        if (key == KeyEvent.VK_RIGHT) dx = 0;
        
        if (key == KeyEvent.VK_UP) dy = 2;
        
        if (key == KeyEvent.VK_DOWN) dy = -2;
        
        move();
    }
}
