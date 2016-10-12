package com.game.thebattlecamp;

import java.awt.Color;

import javax.swing.JFrame;

import com.game.thebattlecamp.util.Constantes;

public class PainelGame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public PainelGame() {
		add(new Game());
        setTitle(Constantes.MAIN_TITLE);
        setSize(Constantes.CANVAS_HEIGHT, Constantes.CANVAS_WIDTH);
        setLocationRelativeTo(null);
        setFocusable(true);
        setBackground(Color.black);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new PainelGame();
	}
}


