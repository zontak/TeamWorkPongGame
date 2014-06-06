package PongGame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	InputHandler IH;
	
	private static AudioClip sound = Applet.newAudioClip(GetSoundUrl());

	JFrame frame; // Window of the game
	public final int WIDTH = 1024; // Width of window
	public final int HEIGHT = 768; // Height of window
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT); // Condense WIDTH & HEIGHT into 1 variable
	public final String TITLE = "Pong InDev";

	BufferedImage image =(BufferedImage)getPicture("src\\IMAGES\\game_background_su.png");
	
	public Image getPicture(String path) {
		Image img = null;
		try {
			File file = new File(path);
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Error reading background file");
		}
		return img;
	}

	static boolean gameRunning = false; // Whether the game is running

	int p1Score, p2Score;

	public void run() {

		while (gameRunning) { // If gameRunning = true
			tick();
			render();

			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	} // End start method

	public static synchronized void stop() {
		gameRunning = false;
		
		//System.exit(0);
	} // End stop method

	public Game() {
		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);

		this.requestFocus();
		
		IH = new InputHandler(this);

		player = new PlayerPaddle(10, 60);
		ai = new AIPaddle(getWidth() - 20, 60);
		ball = new Ball(getWidth() / 2, getHeight() / 2);
	}

	public void tick() {
		player.tick(this);
		ai.tick(this);
		ball.tick(this);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("TimesRoman",Font.BOLD, 70));
		g.drawString("" + p1Score, getWidth() / 2 - 100, 75);
		g.drawString("" + p2Score, getWidth() / 2 + 65, 75);

		
		if (p1Score == 5) {

			g.setFont(new Font("TimesRoman",Font.BOLD, 30));
			g.drawString("Player 1 wins!! ", WIDTH / 2 - 400, HEIGHT / 2 - 90);
			sound.play();
			stop();
			player.render(g);
			ai.render(g);
			ball.render(g);
			g.dispose();
			bs.show();
			return;
		}
		
		if (p2Score == 5) {
			
			g.setFont(new Font("TimesRoman",Font.BOLD, 30));
			g.drawString("Player 2 wins!! ",  WIDTH / 2 + 200, HEIGHT / 2 - 90);
			sound.play();
			stop();
			player.render(g);
			ai.render(g);
			ball.render(g);
			g.dispose();
			bs.show();
			return;
		}
		
		player.render(g);
		ai.render(g);
		ball.render(g);

		g.dispose();
		bs.show();
	}
	
	public static URL GetSoundUrl() {
		try {
			return new URL("file:src/SOUNDS/WinWholeGame.wav");

		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}		
	}
	
}