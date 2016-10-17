package com.game.thebattlecamp.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		setBackground(Color.black);
		setLayout(null);
		setVisible(true);
		initializeComponents();
	}

	public void initializeComponents() {
		JLabel logo = new JLabel("The BattleCamp");
		JLabel backImage = new JLabel();
		URL url = GameUtils.extractURLFromString(Constants.BACKGROUND_IMG_LOCATION);
		ImageIcon icon = new ImageIcon(url);
		backImage.setIcon(icon);
		backImage.setBounds(0, 0, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
		Font fontTitle = new Font("Courier 10 Pitch", Font.BOLD,45);
		Font fontButtons = new Font("Courier 10 Pitch", Font.BOLD,20);
		Font fontCredits = new Font("Courier 10 Pitch", Font.BOLD,20);
		JButton start  = new JButton("Start");
		start.setBounds(150, 300, 300, 40);
		start.setBackground(Color.white);
		start.setFont(fontButtons);
		JButton credits  = new JButton("Credits");
		credits.setBounds(150, 300, 300, 40);
		credits.setBackground(Color.white);
		credits.setFont(fontCredits);
		JButton exit = new JButton("Exit");
		exit.setFont(fontButtons);
		exit.setBackground(Color.white);
		
		exit.setBounds(150, 350, 300, 40);
		logo.setBounds(110, 150, 400, 40);
		logo.setFont(fontTitle);
		logo.setForeground(Color.white);
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PainelGame();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(logo);
		add(start);
		add(exit);
		add(backImage);
		
	}

}
