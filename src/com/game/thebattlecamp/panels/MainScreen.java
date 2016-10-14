package com.game.thebattlecamp.panels;

import javax.swing.JFrame;

import com.game.thebattlecamp.util.Constants;

public class MainScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public MainScreen() {
		add(new MainPanel());
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
	}
	public static void main(String[] args) {
		new MainScreen();
	}
}

