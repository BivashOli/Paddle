package com.paddle.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.paddle.states.GameState;
import com.paddle.states.MenuState;
import com.paddle.states.PauseState;
import com.paddle.states.State;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = -8876109909063605256L;

	public static final int WIDTH = 1500, HEIGHT = 800;
	public static final String TITLE = "Paddle";
	public static int score = 0;
	private boolean running = false;

	private JFrame frame;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;

	private State state = State.MENU;
	private MenuState menustate;
	private GameState gamestate;
	private PauseState pausestate;

	public Game() {
		window();
		addMouseListener(this);
		addKeyListener(this);
	}

	private void init() {
		menustate = new MenuState();
		gamestate = new GameState();
		pausestate = new PauseState();
	}

	private void window() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame = new JFrame(TITLE);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		setFocusable(true);
		frame.setVisible(true);
	}

	public void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (state == State.MENU) {
			menustate.render(g);
		} else if (state == State.GAME) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 25));
			g.drawString("Score: " + String.valueOf(score), 25, 25);
			gamestate.render(g);
		} else if (state == State.PAUSE) {
			pausestate.render(g);
		}
		bs.show();
		g.dispose();
	}

	public void update() {
		if (state == State.GAME) {
			gamestate.update();
		}
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_D) {
			gamestate.getPlayer().setVelX(15);
		}
		if (keycode == KeyEvent.VK_A) {
			gamestate.getPlayer().setVelX(-15);
		}
		if (keycode == KeyEvent.VK_P) {
			state = State.PAUSE;
		}
	}

	public void keyReleased(KeyEvent e) {
		gamestate.getPlayer().setVelX(0);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (state == State.MENU) {
			if (x >= menustate.getPlayX() && x <= menustate.getPlayX() + 200) {
				if (y >= menustate.getPlayY() && y <= menustate.getPlayY() + 100) {
					state = State.GAME;
				}
			}
			if (x >= menustate.getExitX() && x <= menustate.getExitX() + 200) {
				if (y >= menustate.getExitY() && y <= menustate.getExitY() + 100) {
					System.exit(1);
				}
			}

		} else if (state == State.PAUSE) {
			if (x >= pausestate.getResumeX() && x <= pausestate.getResumeX() + 400) {
				if (y >= pausestate.getResumeY() && x <= pausestate.getResumeY() + 200) {
					state = State.GAME;
				}
			}
			if (x >= pausestate.getMenuX() && x <= pausestate.getMenuX() + 400) {
				if (y >= pausestate.getMenuY() && y <= pausestate.getMenuY() + 200) {
					state = State.MENU;
					gamestate.playerReset();
					System.out.println("d");
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
}
