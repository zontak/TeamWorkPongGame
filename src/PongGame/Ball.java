package PongGame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

public class Ball {
	private static AudioClip sound = Applet.newAudioClip(GetSoundUrl());
	int x, y;
	int size = 40;
	int speed = 5;
	int vx, vy;

	Rectangle boundingBox;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		vx = speed;
		vy = speed;

		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(this.x, this.y, this.size, this.size);
	}

	public void tick(Game game) {
		boundingBox.setBounds(x, y, size, size);

		if (x <= 0) {
			game.p2Score++;
			vx = speed;
		} else if (x + size >= game.getWidth()) {
			game.p1Score++;
			vx = -speed;
		}

		if (y <= 0) {
			vy = speed;
		} else if (y + size >= game.getHeight()) {
			vy = -speed;
		}

		x += vx;
		y += vy;

		paddleCollide(game);
	}

	private void paddleCollide(Game game) {

		if (boundingBox.intersects(game.player.boundingBox)) {
			sound.play();
			vx = speed;
		} else if (boundingBox.intersects(game.ai.boundingBox)) {
			sound.play();
			vx = -speed;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);

	}

	public static URL GetSoundUrl() {
		try {
			return new URL("file:src/SOUNDS/Ball.wav");

		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}		
	}

}
