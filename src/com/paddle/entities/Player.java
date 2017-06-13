package com.paddle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.paddle.main.Game;

public class Player extends Entity {
	
	private int velX;

	public Player(int x, int y) {
		super(x, y);
	}

	protected void init() {
		x = 750 - 30;
		y = 750;
		width = 100;
		height = 20;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	public void update() {
		if (x < 0) {
			x = 0;
		}
		if (x > Game.WIDTH - width) {
			x = Game.WIDTH - width;
		}
		x += velX;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

}
