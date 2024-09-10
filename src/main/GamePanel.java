package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import entity.Entity;
import entity.Npc;
import entity.Player;
import object.SuperObject;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	public final int originalTileSize = 16; // 16 x 16 pixels
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 22;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// WORLD SETTINGS
	public final int maxWorldCol = 36 * 2 + 1;
	public final int maxWorldRow = 25 * 2 + 1;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;

	// Game Status
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int deskState = 3;
	public final int toiletState = 4;



	Thread gameThread;
	public KeyHandler keyHandler = new KeyHandler(this);
	public UI ui = new UI(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyHandler);
	public TileManager tileM = new TileManager(this);
	public ArrayList<SuperObject> obj = new ArrayList<>();
	public ArrayList<Entity> entities = new ArrayList<>();

	public GamePanel () {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		aSetter.setEntity();
		aSetter.setObject();
		gameState = titleState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	int FPS = 60;
//  // First Method - GameLoop
//	@Override
//	public void run() {
//		double drawInterval = 1000000000.0 / FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//
//		while (gameThread != null) {
//			update();
//			repaint();
//
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime /= 1000000; // time in milliseconds
//
//				if (remainingTime <= 0) {
//					remainingTime = 0;
//				}
//
//				Thread.sleep((long)remainingTime);
//
//				nextDrawTime += drawInterval;
//
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}

	// Delta Method - Game Loop
	@Override
	public void run() {
		double drawInterval = 1000000000.0 / FPS;
		double delta = 0;
		double currentTime;
		double lastTime = System.nanoTime();

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;

			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		if (gameState == playState)
			player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Debug
		long drawStart = 0;
		if (keyHandler.showDebugText) {
			drawStart = System.nanoTime();
		}

		if (gameState == titleState) {
			ui.draw(g2d);
		} else {
			// Tiles
//			tileM.draw(g2d);

			// Objects
//			for (SuperObject superObject : obj) {
//				if (superObject != null && superObject.isActive) {
//					superObject.draw(g2d, this);
//				}
//			}

			// Entities
			for (Entity entity : entities) {
				if (entity != null) {
					if (entity instanceof Npc) {
						((Npc) entity).draw(g2d, this);
					}
				}
			}

			// Players
			player.draw(g2d);

			// UI
			ui.draw(g2d);

		}



		// Debug
		if (keyHandler.showDebugText) {
			// Coords
			g2d.setFont(new Font("Arial", Font.PLAIN, 20));
			g2d.setColor(Color.WHITE);
			int x = 10;
			int y = 400;

			g2d.drawString("WorldX - " + player.worldX, x, y);
			y += 20;
			g2d.drawString("WorldY - " + player.worldY, x, y);
			y += 20;
			g2d.drawString("Col - " + (player.worldX + player.solidArea.x) / tileSize, x, y);
			y += 20;
			g2d.drawString("Row - " + (player.worldY + player.solidArea.y) / tileSize, x, y);
			y += 20;

			// Draw Time
			long drawEnd = System.nanoTime();
			long passedTime = drawEnd - drawStart;
			g2d.drawString("Draw Time: " + passedTime, x, y);
			// System.out.println("Draw Time: " + passedTime);
		}

		g2d.dispose();
	}

}
