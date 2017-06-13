package com.paddle.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.paddle.entities.Ball;
import com.paddle.entities.Player;
import com.paddle.main.Game;

public class GameState{

	private Player player;
	private Ball ball;

	private Rectangle playerRect, ballRect;

	public GameState() {
		init();
	}

	protected void init() { 
		player = new Player(720, 750);
		ball = new Ball(720, 700);
	}

	public void render(Graphics g) {
		player.render(g);
		ball.render(g);
	}

	public void update() {
		player.update();
		ball.update();
		playerRect = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		collision();
	}

	private void collision() {
		if (ballRect.intersects(playerRect)) {
			ball.setVelY(-ball.getVelY()); 
		}
	}
	public void playerReset(){
		player.setX(720);
		player.setY(750);
		Game.score = 0;
	}
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

}
