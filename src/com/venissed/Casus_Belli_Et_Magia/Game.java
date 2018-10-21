package com.venissed.Casus_Belli_Et_Magia;

import javax.swing.JFrame;

import com.venissed.Casus_Belli_Et_Magia.entity.mob.Player;
import com.venissed.Casus_Belli_Et_Magia.graphics.Screen;
import com.venissed.Casus_Belli_Et_Magia.graphics.Sprite;
import com.venissed.Casus_Belli_Et_Magia.graphics.SpriteSheet;
import com.venissed.Casus_Belli_Et_Magia.input.Keyboard;
import com.venissed.Casus_Belli_Et_Magia.input.Mouse;
import com.venissed.Casus_Belli_Et_Magia.level.Level;
import com.venissed.Casus_Belli_Et_Magia.level.RandomLevel;
import com.venissed.Casus_Belli_Et_Magia.level.SpawnLevel;
import com.venissed.Casus_Belli_Et_Magia.level.TileCoordinate;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// --------------------------------------
	// --------------Variables---------------
	// --------------------------------------

	private static int width = 300;
	private static int height = width * 9 / 16;
	private static int scale = 3;
	public static String title = "Casus Belli Et Magia";

	private Thread display;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();

	
	// --------------------------------------
	// ---------------Methods----------------
	// --------------------------------------

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(Game.width, Game.height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(28,37);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	public synchronized void start() {
		display = new Thread(this, "Display");
		display.start();
		this.running = true;
	}

	public synchronized void stop() {
		running = false;
		try {
			display.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		final int targetFPS = 60;
		final int TARGET_DELTA = 1000 / targetFPS; // fps counting
		long startTime = System.currentTimeMillis();
		int frameCount = 0; // It counts each frame - update plus render
		int fps = 0;
		requestFocus();
		while (running) {
			long before = System.currentTimeMillis();
			update();
			render();
			// Basically it creates desired span of waiting in between each frame and it
			// compares it to the real time of frame.
			// Then it checks whether the real time is smaller than the desired one it stops
			// for the amount of time that is needed to fill this gap
			frameCount++;
			long after = System.currentTimeMillis();
			int delta = (int) (after - before); // how much time update + render took
			if (after - startTime >= 1000) {
				startTime += 1000;
				fps = frameCount;
				frameCount = 0;
			}
			this.frame.setTitle(Game.title + " | delta: " + delta + " fps:" + fps);
			// if update + render took less time than the delta we want for 60fps (around
			// 16ms)
			// we pause the thread to free the CPU
			if (TARGET_DELTA > delta) {
				try {
					Thread.sleep(TARGET_DELTA - delta);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*public void ChernoRun() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta>= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(this.title + " | ups: " + updates + ", fps: " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}*/

	public void update() {
		key.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			// We have 3 screens - Our, buffer and buffer working while updating the our
			createBufferStrategy(3);
			return;
		}

		// different class of temporary image of screen
		this.screen.clear();
		double xScroll = player.getX() - screen.getWidth()/2;
		double yScroll = player.getY() - screen.getHeight()/2;
		this.level.render((int)xScroll, (int)yScroll, screen);
		
		//screen.renderSheet(40, 40, SpriteSheet.player_up, false);
		
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = this.screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(view, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 40));
		//if(Mouse.getButton() != -1) g.drawString("Button: " + Mouse.getButton(), 80, 80);
		
		// removing the graphics
		g.dispose();
		bs.show();
	}

	// --------------------------------------
	// ----------------Main------------------
	// --------------------------------------

	public static void main(String[] args) {
		System.out.println("hello");
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		// Adding our game component to the frame because it is a canvas
		game.frame.add(game);
		// Resizing the frame according to component (Dimension size)
		game.frame.pack();
		// If we hit the X button we end the process
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// It center our game window
		game.frame.setLocationRelativeTo(null);
		// It obviously makes our window visible
		game.frame.setVisible(true);

		game.start();
	}
}
