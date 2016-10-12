package com.game.thebattlecamp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.game.thebattlecamp.entity.EnemyCanon;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.util.Constantes;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private boolean isPlaying = Boolean.TRUE;

	private Thread gameThread = null;
	
	private PlayerCanon player = null;
	
	private List<EnemyCanon> listaInimigos = null;
	
	private Long lastLoopingTime = null;
	
	private int sleepTime = Constantes.DEFAULT_SLEEP_TIME;

	private static int gameSpeed = Constantes.DEFAULT_GAME_SPEED;
	
	private int score = 0 ;

	private boolean paused = Boolean.FALSE;

	private String message;
	
	public Game() {
		addKeyListener(new ComandsFromKeyboard());
		requestFocusInWindow();
		
		buildFrame();
        if (gameThread == null || !isPlaying) {
            gameThread = new Thread(this);
            gameThread.start();
        }
	}
	
	private void buildFrame() {
		listaInimigos = new ArrayList<EnemyCanon>();
		player = new PlayerCanon();
	}
	
    public void gameOver()
    {
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(Constantes.START_POSITION_X, Constantes.START_POSITION_Y, 
        		Constantes.CANVAS_WIDTH, Constantes.CANVAS_HEIGHT);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Constantes.CANVAS_WIDTH/2 - 30, Constantes.CANVAS_WIDTH-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Constantes.CANVAS_WIDTH/2 - 30, Constantes.CANVAS_WIDTH-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Constantes.CANVAS_WIDTH - metr.stringWidth(message))/2, 
        		Constantes.CANVAS_HEIGHT/2);
    }
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(Constantes.START_POSITION_X, Constantes.START_POSITION_Y,
				Constantes.CANVAS_WIDTH, Constantes.CANVAS_HEIGHT);
		if(isPlaying){
			drawPlayer(g);
		}
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	}
	
	private void drawPlayer(Graphics g) {
		if(player.isVisible()){
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
	}
	
	public void startLooping()  {
		
		lastLoopingTime = System.currentTimeMillis();
		
		
		while(isPlaying){
			repaint();
			contextoDeAnimacao();
			long diff = System.currentTimeMillis() - lastLoopingTime;
			
			if(diff == 0){
				sleepTime = 10;
			}
			
			if(player.life < 0){
				isPlaying = Boolean.FALSE;
			}
			if(!paused){
				sleep();
			}
			lastLoopingTime = System.currentTimeMillis();
		}
		gameOver();
	}

	private void contextoDeAnimacao() {
		if(Constantes.LIFE_END.equals(player.life)){
			isPlaying = Boolean.FALSE; 
			return;
		}
		player.move();
	}

	private void sleep() {
		try{ 
			Thread.sleep(sleepTime);
		} catch( InterruptedException ae){
			
		}
	}
	

	

	
	public class ComandsFromKeyboard extends KeyAdapter {
	
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Key Pressed");
		}
		public void keyReleased(KeyEvent e) {
			System.out.println("Key Pressed");
		}
		
	}





	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
