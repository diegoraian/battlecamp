package com.game.thebattlecamp.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		setBackground(Color.black);
		setLayout(null);
		setVisible(true);
		initializeComponents();
	}

	private void initializeComponents() {
		JButton iniciar  = new JButton("Iniciar");
		JButton sair = new JButton("Sair");
		iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PainelGame();
			}
		});
		add(iniciar);
		add(sair);		
	}

}
