package com.game.thebattlecamp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.game.thebattlecamp.entity.Boss;
import com.game.thebattlecamp.entity.EnemyCanon;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.entity.Shot;
import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private boolean isPlaying = Boolean.TRUE;

	private Thread gameThread = null;
	
	private PlayerCanon player = null;
	
	private List<EnemyCanon> enemiesList = null;
	
	private List<Integer> rightShots = null;
	
	private List<Integer> enemiesDefeated = null;
	
	private static int GAME_SPEED = Constants.DEFAULT_GAME_SPEED;
	
	private Integer score = 0 ;

	private boolean paused = Boolean.FALSE;
	
	private Integer enemiesKilled = 0;
	
	public Game(List<EnemyCanon>  listaDeInimigos, PlayerCanon player){
		this.enemiesList = listaDeInimigos;
		this.player = player;
		this.enemiesDefeated = new Vector<Integer>();
		this.rightShots = new Vector<Integer>();
		generateEnemiesList();
        if (gameThread == null || !isPlaying) {
            gameThread = new Thread(this);
            gameThread.start();
        }
	}
		
    private void generateEnemiesList() {
    	for(int i=0;i < GameUtils.randomizeEnemiesAmount();i++){
			EnemyCanon enemyCanon = new EnemyCanon();
			enemiesList.add(enemyCanon);
    	}
	}

	public synchronized void gameOver()
	{
		Graphics g  = this.getGraphics();
        setBackground(Color.black);
        g.fillRect(Constants.START_POSITION_X, Constants.START_POSITION_Y, 
        		Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Constants.CANVAS_WIDTH/2 - 30, Constants.CANVAS_WIDTH-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Constants.CANVAS_WIDTH/2 - 30, Constants.CANVAS_WIDTH-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(Constants.GAME_OVER, (Constants.CANVAS_WIDTH - metr.stringWidth(Constants.GAME_OVER))/2, 
        		Constants.CANVAS_HEIGHT/2);
    }
	
    @Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(Constants.START_POSITION_X, Constants.START_POSITION_Y,
				Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
		if(isPlaying){
			drawBackground(g);
			drawPlayer(g);
			drawEnemiesCanons(g);
			drawShots(g);
			drawScore(g);
			drawBoss(g);
		}
		
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	}
	private void drawBackground(Graphics g){
		URL url = GameUtils.extractURLFromString(Constants.BACKGROUND_IMG_LOCATION);
		ImageIcon icon = new ImageIcon(url);
		g.drawImage(icon.getImage(), 0, 0, this);
	}
	private void drawScore(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Score: "+ score.toString(), 10, 30); 
		
	}

	private void drawShots(Graphics g) {
		try {
			for (Shot shot : player.listOfShots) {
				if(player.listOfShots.size() > 0){
					if(shot.isVisible()){
						g.drawImage(shot.getImage(), shot.getX().intValue(), shot.getY().intValue(), this);
					}
				}
			}	
		}catch(Exception e){
			e.getMessage();
		}
	}

	private void drawPlayer(Graphics g) {
		if(player.isVisible()){
			g.drawImage(player.getImage(), player.getX().intValue(), player.getY().intValue(), this);
		}
	}

	private void drawEnemiesCanons(Graphics g) {
		for (EnemyCanon enemy : enemiesList) {
			if(enemiesList.size() > 0){
				if(enemy.isVisible()){
					g.drawImage(enemy.getImage(), enemy.getX().intValue(), enemy.getY().intValue(), this);
				}
			}
		}
	}
	
	private void drawBoss(Graphics g) {
		Boss boss = new Boss();
		//if(boss.isVisible()){
			g.drawImage(boss.getImage(), boss.getX().intValue(), boss.getY().intValue(), this);
		//}
	}
	
	public synchronized void startLooping(Graphics g)  {
		while(isPlaying){
			repaint();
			animationContext(g);
			
			if(player.life < 0){
				isPlaying = Boolean.FALSE;
			}
			if(!paused){
				sleep();
			}
		}
		gameOver();
	}

	private  synchronized void animationContext(Graphics g) {
		if(Constants.LIFE_END.equals(player.life)){
			isPlaying = Boolean.FALSE; 
			return;
		}
		for (EnemyCanon enemyCanon : enemiesList) {
			if(enemyCanon.isVisible()){
				enemyCanon.moveFromRightToLeft();
			}
		}
		
		for (int j= 0; j < player.listOfShots.size();j++) {
			Shot shot = player.listOfShots.get(j);
			if(shot.isVisible()){
				shot.moveShot();
				for(int i = 0  ; i < enemiesList.size();i++){
					EnemyCanon enemy = enemiesList.get(i);
					if(enemy.isVisible()){
						if(enemy.colision(shot)){
						enemiesDefeated.add(i);
						rightShots.add(j);
						enemiesKilled ++;
						score +=100;
						}
					}
					
				}
			}
			
		}
		if(enemiesDefeated.size() > 0) {
			for (Integer i: enemiesDefeated) {
				enemiesList.remove(i);
			}
		}
		if(rightShots.size() > 0){
			for (Integer i: rightShots) {
				Shot shot = player.listOfShots.get(i);
				if(!shot.isVisible()){
					player.listOfShots.remove(i);
				}
			}
		}
		if(score == 600) {
		
		}
		enemiesDefeated.clear();
		
		if(player.lastKeyPressed == 'U' || player.lastKeyPressed == 'D') player.moveY();
		if(player.lastKeyPressed == 'L' || player.lastKeyPressed == 'R') player.moveX();
		if(player.lastKeyPressed == '9' ||  player.lastKeyPressed == '3' ||
				player.lastKeyPressed == '1' || player.lastKeyPressed == '7'){
			player.moveX();
			player.moveY();
		}
		if(enemiesKilled == enemiesList.size() - 2) generateEnemiesList();
		if(enemiesKilled == enemiesList.size()) isPlaying = Boolean.FALSE;
		
		
	}


	private synchronized void sleep() {
		try{ 
			Thread.sleep(GAME_SPEED);
		} catch( InterruptedException ae){
			System.out.println();
		}
	}

	@Override
	public synchronized void run() {
		Graphics g  = this.getGraphics();
		startLooping(g);
	}
}
