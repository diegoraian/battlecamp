package com.game.thebattlecamp.entity;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;

import com.game.thebattlecamp.util.GameUtils;

public class Sprite {
        private boolean visible;
        private Image image;
        protected int x;
        protected int y;
        protected boolean dying;
        protected int dx;
        protected int dy;
        protected int width ;
    	protected int height;
    	protected int spriteState = 0;
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

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
        
        
		public void atribuirImagem(String caminhoImagem){
			URL resource = GameUtils.extractURLFromString(caminhoImagem);
			ImageIcon icon = new ImageIcon(resource);
			width = icon.getImage().getWidth(null);
			height = icon.getImage().getHeight(null);
			setImage(icon.getImage());				
		}
}
