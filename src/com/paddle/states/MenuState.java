package com.paddle.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.paddle.main.Game;

public class MenuState {
	
	private int playX = Game.WIDTH/2-330, playY = Game.HEIGHT/2+100;
	private int exitX = Game.WIDTH/2+170, exitY = Game.HEIGHT/2+100;
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("Serif", Font.PLAIN, 100)); 
		g.drawString("Paddle", Game.WIDTH/2-130, Game.HEIGHT/2-100);
		
		g.drawRect(playX, playY, 200, 100);
		g.drawRect(exitX, exitY, 200, 100);
		
		g.setFont(new Font("Serif", Font.PLAIN, 50)); 
		g.drawString("Play", playX+60, playY+60);
		g.drawString("Exit", exitX+60, exitY+60);
	}

	public int getPlayX() {
		return playX;
	}

	public void setPlayX(int playX) {
		this.playX = playX;
	}

	public int getPlayY() {
		return playY;
	}

	public void setPlayY(int playY) {
		this.playY = playY;
	}

	public int getExitX() {
		return exitX;
	}

	public void setExitX(int exitX) {
		this.exitX = exitX;
	}

	public int getExitY() {
		return exitY;
	}

	public void setExitY(int exitY) {
		this.exitY = exitY;
	}
}
