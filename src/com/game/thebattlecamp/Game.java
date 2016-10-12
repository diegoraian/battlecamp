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
import com.game.thebattlecamp.entity.Shot;
import com.game.thebattlecamp.util.Constantes;
import com.game.thebattlecamp.util.GameUtils;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private boolean isPlaying = Boolean.TRUE;

	private Thread gameThread = null;
	
	private PlayerCanon player = null;
	
	private List<EnemyCanon> enemiesList = null;
	
	private Long lastLoopingTime = null;
	
	private int sleepTime = Constantes.DEFAULT_SLEEP_TIME;

	private static int gameSpeed = Constantes.DEFAULT_GAME_SPEED;
	
	private Integer score = 0 ;

	private boolean paused = Boolean.FALSE;
	
	private String message;
	
	public Game(List<EnemyCanon>  listaDeInimigos, PlayerCanon player){
		this.enemiesList = listaDeInimigos;
		this.player = player;
		generateEnemiesList();
        if (gameThread == null || !isPlaying) {
            gameThread = new Thread(this);
            gameThread.start();
        }
	}
		
    private void generateEnemiesList() {
    	for(int i=0;i < 3;i++){
			EnemyCanon enemyCanon = new EnemyCanon();
			enemiesList.add(enemyCanon);
    	}
	}

	public void gameOver(Graphics g)
	{
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
        g.drawString(Constantes.GAME_OVER, (Constantes.CANVAS_WIDTH - metr.stringWidth(message))/2, 
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
			drawEnemiesCanons(g);
			drawShots(g);
			drawScore(g);
		}
		
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	}
	
	private void drawScore(Graphics g) {
		
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Score: "+ score.toString(), 10, 30); 
		
	}

	private void drawShots(Graphics g) {
		for (Shot shot : player.listOfShots) {
			if(player.listOfShots.size() > 0){
				if(shot.isVisible()){
					g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
				}
			}
		}
		
	}

	private void drawPlayer(Graphics g) {
		if(player.isVisible()){
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
	}

	private void drawEnemiesCanons(Graphics g) {
		for (EnemyCanon enemy : enemiesList) {
			if(enemiesList.size() > 0){
				if(enemy.isVisible()){
					g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
				}
			}
		}
	}
	
	
	public void startLooping(Graphics g)  {
		
		lastLoopingTime = System.currentTimeMillis();
		
		
		while(isPlaying){
			repaint();
			animationContext();
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
		gameOver(g);
	}

	private void animationContext() {
		if(Constantes.LIFE_END.equals(player.life)){
			isPlaying = Boolean.FALSE; 
			return;
		}
		
		for (EnemyCanon enemyCanon : enemiesList) {
			if(enemyCanon.isVisible()){
				enemyCanon.moveFromRightToLeft();
			}
		}
		
		for (Shot shot : player.listOfShots) {
			if(shot.isVisible()){
				shot.moveShot();
				for(EnemyCanon enemy: enemiesList){
					enemy.colision(shot);;
				}
			}
		}
		
		
		
	}

	private void sleep() {
		try{ 
			Thread.sleep(sleepTime);
		} catch( InterruptedException ae){
			
		}
	}
	

	@Override
	public void run() {
		Graphics g  = this.getGraphics();
		startLooping(g);		
	}
}
