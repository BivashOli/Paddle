package com.paddle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	
	protected int x, y, width, height;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
		init();
	}

	protected abstract void init();
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	
}
