package com.game.thebattlecamp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.game.thebattlecamp.entity.EnemyCanon;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.util.ComandosTeclado;
import com.game.thebattlecamp.util.Constantes;

public class PainelGame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public PainelGame() {
		List<EnemyCanon>listaInimigos = new ArrayList<EnemyCanon>();
		PlayerCanon player = new PlayerCanon();
		add(new Game(listaInimigos,player));
		addKeyListener(new ComandosTeclado(player));
        setTitle(Constantes.MAIN_TITLE);
        setSize(Constantes.CANVAS_HEIGHT, Constantes.CANVAS_WIDTH);
        setLocationRelativeTo(null);
        setFocusable(true);
        setBackground(Color.black);
        setVisible(true);
        requestFocusInWindow();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new PainelGame();
	}
}


