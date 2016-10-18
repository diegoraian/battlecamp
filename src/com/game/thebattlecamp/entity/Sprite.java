package com.game.thebattlecamp.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

import com.game.thebattlecamp.util.GameUtils;

public abstract class Sprite {
        protected boolean visible;
        private Image image;
        protected Double x;
        protected Double y;
        protected int dx;
        protected int dy;
        protected int width ;
    	protected int height;
    	protected int spriteState = 0;
    	protected Rectangle rectangle = new Rectangle(); ;
    	protected BufferedImage[] spriteSheetArray;
    	protected AffineTransform at = new AffineTransform();
        public Sprite() {
            visible = true;
        }

        public void die() {
            visible = false;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public void setY(Double y) {
            this.y = y;
        }
        public Double getY() {
            return y;
        }

        public Double getX() {
            return x;
        }
        
        
		public void setImagem(String caminhoImagem){
			URL resource = GameUtils.extractURLFromString(caminhoImagem);
			ImageIcon icon = new ImageIcon(resource);
			width = icon.getImage().getWidth(null);
			height = icon.getImage().getHeight(null);
			setImage(icon.getImage());				
		}
		public void setLimits(){
			rectangle.setBounds(x.intValue(), y.intValue(), width , height);
		}
		
		public Boolean colision(Sprite sprite){
			if(rectangle.intersects(sprite.rectangle)) {
				sprite.setVisible(Boolean.FALSE);
				setVisible(false);
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}
}
