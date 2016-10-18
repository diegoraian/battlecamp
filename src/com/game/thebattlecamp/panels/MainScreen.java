package com.game.thebattlecamp.panels;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class MainScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainScreen() {
		GameUtils.playSound(Constants.BEGIN_SOUND);
		add(new MainPanel());
		setVisible(true);
		setResizable(Boolean.FALSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
	}
	public static void main(String[] args) throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		new MainScreen();
	}
}

