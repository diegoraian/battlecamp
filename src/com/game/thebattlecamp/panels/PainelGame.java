package com.game.thebattlecamp.panels;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.game.thebattlecamp.Game;
import com.game.thebattlecamp.entity.EnemyCanon;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.util.ComandosTeclado;
import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class PainelGame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public PainelGame() {
		GameUtils.playSound(Constants.BEGIN_SOUND);
		List<EnemyCanon>listaInimigos = new Vector<EnemyCanon>();
		PlayerCanon player = new PlayerCanon();
		add(new Game(listaInimigos,player));
		addKeyListener(new ComandosTeclado(player));
		setIconImage(new ImageIcon(GameUtils.extractURLFromString(Constants.PLAYER_SPRITE_LOCATION)).getImage());
        setTitle(Constants.MAIN_TITLE);
        setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
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


