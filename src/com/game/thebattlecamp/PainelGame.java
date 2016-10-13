package com.game.thebattlecamp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.game.thebattlecamp.entity.EnemyCanon;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.util.ComandosTeclado;
import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class PainelGame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public PainelGame() {
		GameUtils.playSound(Constantes.BEGIN_SOUND);
		List<EnemyCanon>listaInimigos = new ArrayList<EnemyCanon>();
		PlayerCanon player = new PlayerCanon();
		add(new Game(listaInimigos,player));
		addKeyListener(new ComandosTeclado(player));
        setTitle(Constantes.MAIN_TITLE);
        setSize(Constantes.CANVAS_WIDTH, Constantes.CANVAS_HEIGHT);
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


