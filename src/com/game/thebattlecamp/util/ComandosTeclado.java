package com.game.thebattlecamp.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.thebattlecamp.entity.PlayerCanon;

public class ComandosTeclado extends KeyAdapter{
	PlayerCanon player;
	public ComandosTeclado() {
	}
	
	public ComandosTeclado(PlayerCanon pc) {
		this.player = pc;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	
	
}

