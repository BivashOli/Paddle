package com.paddle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.paddle.main.Game;

public class Ball extends Entity {

	private int velX = -6, velY = -6;

	public Ball(int x, int y) {
		super(x, y);
	}

	protected void init() {
		width = 16;
		height = 16;
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
	}

	public void update() {
		if (x < 0 || x > Game.WIDTH - width) {
			velX = -velX;
			Game.score++;
		}
		if (y < 0) {
			velY = -velY;
		}
		if (y > Game.HEIGHT - height) {
			velY = -velY;
			Game.score = 0;
			x = 720;
			y = 700;
		}
		x += velX;
		y += velY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

}
