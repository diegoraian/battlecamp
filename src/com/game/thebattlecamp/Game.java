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
import com.game.thebattlecamp.entity.Meteor;
import com.game.thebattlecamp.entity.PlayerCanon;
import com.game.thebattlecamp.entity.Shot;
import com.game.thebattlecamp.util.Constants;
import com.game.thebattlecamp.util.GameUtils;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private boolean isPlaying = Boolean.TRUE;

	private Thread gameThread = null;
	
	private Long nextWave = 0l;
	
	private PlayerCanon player = null;
	
	private Boss boss = null;
	
	private Integer counterMonstersKilled = 1;
	
	private List<EnemyCanon> enemiesList = null;
	
	private List<Integer> enemiesDefeated = null;
	
	private List<Meteor> meteorsList = null;
	
	private List<Integer> meteorsDestroyedList = null;
	
	private List<Integer> rightShots = null;
	
	private static int GAME_SPEED = Constants.DEFAULT_GAME_SPEED;
	
	private Integer score = 0 ;

	private boolean paused = Boolean.FALSE;
	
	private Integer enemiesKilled = 0;

	public Game(List<EnemyCanon>  listaDeInimigos, PlayerCanon player){
		this.enemiesList = listaDeInimigos;
		this.player = player;
		this.boss = new Boss();
		this.meteorsList = new Vector<Meteor>();
		this.meteorsDestroyedList = new Vector<Integer>();
		this.enemiesDefeated = new Vector<Integer>();
		this.rightShots = new Vector<Integer>();
		generateEnemiesList();
		generateMeteorsList();
        if (gameThread == null || !isPlaying) {
            gameThread = new Thread(this);
            gameThread.start();
        }
	}
		
    private void generateEnemiesList() {
    	for(int i=0;i < GameUtils.randomizeEnemiesAmount();i++){
			EnemyCanon enemyCanon = new EnemyCanon();
			enemiesList.add(enemyCanon);
			if(i>0) {
				if(enemiesList.get(i).colision(enemiesList.get(i-1))){
					enemiesList.remove(i);
				}
			}
    	}
	}
    private void generateMeteorsList() {
    	Integer amount = GameUtils.randomizeEnemiesAmount();
    	while(amount <= 0 || amount >=5){
    		 amount = GameUtils.randomizeEnemiesAmount();
    	}
    	for(int i=0;i < GameUtils.randomizeEnemiesAmount();i++){
			Meteor meteor = new Meteor();
			meteorsList.add(meteor);
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
        g.drawString(Constants.GAME_OVER , (Constants.CANVAS_WIDTH - metr.stringWidth(Constants.GAME_OVER))/2, 
        		Constants.CANVAS_HEIGHT/2);
        g.drawString(Constants.POINTS_DONE + score , (Constants.CANVAS_WIDTH - metr.stringWidth(Constants.POINTS_DONE))/2, 
        		Constants.CANVAS_HEIGHT/2 + 20);
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
			drawMeteors(g);
			drawTopBar(g);
			//if(enemiesKilled == Constants.MAXIMUM_MONSTERS_TO_KILL){
				//drawBoss(g);
			//}
		}
		
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	}
	
    private void drawBackground(Graphics g){
		URL url = GameUtils.extractURLFromString(Constants.BACKGROUND_IMG_LOCATION);
		ImageIcon icon = new ImageIcon(url);
		g.drawImage(icon.getImage(), 0, 0, this);
	}

	private void drawTopBar(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
    	g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Score: "+ score.toString(), 10, 30);

        g.drawString("Killed: "+ counterMonstersKilled,
        		Constants.CANVAS_WIDTH/2 - 50 , 30);
        
        if(player.life < 50){
        	g.setColor(Color.red);
        }else if(player.life < 80){
        	g.setColor(Color.yellow);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString("Life: "+ player.life +"%", Constants.CANVAS_WIDTH - 90, 30);
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

	private void drawMeteors(Graphics g) {
		for(Meteor meteoro : meteorsList){
			if(meteoro.isVisible()){
				g.drawImage(meteoro.getImage(), meteoro.getX().intValue(), meteoro.getY().intValue(), this);
				if(meteoro.isVisible()){
					if(meteoro.getLife() == 3){
						g.setColor(Color.green);
					}if(meteoro.getLife() == 2){
						g.setColor(Color.yellow);
					}else if(meteoro.getLife() == 1){
						g.setColor(Color.red);
					}
					 g.fillRect(meteoro.getX().intValue()+30, meteoro.getY().intValue()+30, meteoro.getLife()*20, 2);
				}
			}
		}
	}
	
	private void drawEnemiesCanons(Graphics g) {
		try{
			for (EnemyCanon enemy : enemiesList) {
				if(enemiesList.size() > 0){
					if(enemy.isVisible()){
						g.drawImage(enemy.getImage(), enemy.getX().intValue(), enemy.getY().intValue(), this);
					}
				}
			}
		}catch(Exception ae){
			ae.getMessage();
		}
	}
	
	private void drawBoss(Graphics g) {
		if(boss.isVisible()){
			g.drawImage(boss.getImage(), boss.getX().intValue(), boss.getY().intValue(), this);
		}
	}
	
	public synchronized void startLooping(Graphics g)  {
		
		while(isPlaying){
			repaint();
			animationContext(g);
			nextWave += GAME_SPEED;
			if(player.life < 0){
				isPlaying = Boolean.FALSE;
			}
			if(!paused){
				sleep();
			}
		}
		gameOver();
	}

	private  void animationContext(Graphics g) {
		if(Constants.LIFE_END.equals(player.life)){
			isPlaying = Boolean.FALSE; 
			return;
		}
		//Movements

		//Enemies moves
		for (EnemyCanon enemyCanon : enemiesList) {
			if(enemyCanon.isVisible()){
				if(enemyCanon.moveFromRightToLeft() < 11) player.life -= 5;
			}
		}
		//Meteors moves
		for(Meteor meteor : meteorsList) {
			meteor.move();
			if(meteor.isVisible()){
				if(player.colision(meteor) ){
					if(player.life == 0){
						isPlaying = false;
						return;
					}
				}
			}
		}
		// keyboard movements
		if(player.lastKeyPressed == 'U' || player.lastKeyPressed == 'D') player.moveY();
		if(player.lastKeyPressed == 'L' || player.lastKeyPressed == 'R') player.moveX();
		if(player.lastKeyPressed == '9' ||  player.lastKeyPressed == '3' ||
			player.lastKeyPressed == '1' || player.lastKeyPressed == '7'){
			player.moveX();
			player.moveY();
		}
		
		
		
		// Intersection between a meteor and the player; 
	
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
							counterMonstersKilled +=1;
							enemiesKilled ++;
							score +=10;
						}
						if(player.colision(enemy) ){
							if(player.life == 0){
								isPlaying = false;
								return;
							}
						}
						for(Meteor meteor : meteorsList) {
							if(meteor.isVisible()){
								if(meteor.colision(shot) ){
									Integer life = meteor.getLife();
									rightShots.add(j);
									meteor.setLife(life--);
									if(life == 0) score +=100;
									break;
								}
							}
						}
					}
				}
			}
		}
		
		// intersection between a meteor and the player; 
		
		//clean objects
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
		
		//create enemies
		if(nextWave  >  GAME_SPEED*300){ 
			generateEnemiesList();
			nextWave = 0l;
		}
		
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
