package com.paddle.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.paddle.main.Game;

public class PauseState {

	private int resumeX = Game.WIDTH/2-450, resumeY = Game.HEIGHT/2;
	private int menuX = Game.WIDTH/2+150, menuY = Game.HEIGHT/2;
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.PLAIN, 200));
		g.drawString("Pause", Game.WIDTH/2-220, Game.HEIGHT/2-200); 
		
		g.drawRect(resumeX, resumeY, 400, 200); 
		g.drawRect(menuX, menuY, 400, 200); 
		
		g.setFont(new Font("Serif", Font.PLAIN, 100));
		g.drawString("Resume", resumeX+30, resumeY+130);
		g.drawString("Menu", resumeX+670, resumeY+130);
	}

	public int getResumeX() {
		return resumeX;
	}

	public void setResumeX(int resumeX) {
		this.resumeX = resumeX;
	}

	public int getResumeY() {
		return resumeY;
	}

	public void setResumeY(int resumeY) {
		this.resumeY = resumeY;
	}

	public int getMenuX() {
		return menuX;
	}

	public void setMenuX(int menuX) {
		this.menuX = menuX;
	}

	public int getMenuY() {
		return menuY;
	}

	public void setMenuY(int menuY) {
		this.menuY = menuY;
	}
	
}
