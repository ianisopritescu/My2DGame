package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import algorithms.PathFinder;
import constants.GameState;
import entity.Entity;
import entity.Player;
import io_handler.KeyHandler;
import io_handler.MouseHandler;
import object.SuperObject;
import tiles.TileManager;
import ui.TextBox;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	public final int originalTileSize = 16; // 16 x 16 pixels
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 38;
	public final int maxScreenRow = 20;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// WORLD SETTINGS
	public final int maxWorldCol = 70;
	public final int maxWorldRow = 77;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;

	// Game States
	public GameState gameState;


	private Thread gameThread;
	public MouseHandler mouseHandler = new MouseHandler(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	public UI ui = new UI(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyHandler, "Pr. Michael Scofield");
	public TileManager tileM = new TileManager(this);
	public PathFinder pathFinder = new PathFinder(this);
	public Map<Point, SuperObject> objMap = new HashMap<>();
	public Map<String, Entity> entities = new HashMap<>();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		gameState = GameState.TITLE_STATE;
		aSetter.setEntities();
		aSetter.setObjects();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	private final int FPS = 60;
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
		double drawInterval = 1_000_000_000.0 / FPS;
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
		if (gameState == GameState.PLAY_STATE) {
			entities.values().forEach(Entity::update);
			player.update();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Debug
		long drawStart = 0;
		if (keyHandler.showDebugText) {
			drawStart = System.nanoTime();
		}

		if (gameState != GameState.PLAY_STATE) {
			ui.draw(g2d);
		} else {
			// Tiles
			tileM.draw(g2d);

			// Objects
			objMap.values().forEach(obj -> {
				obj.draw(g2d);
			});

			// Entities
//			entities.values().forEach(entity -> {
//				entity.draw(g2d);
//			});
//
//			// Players
//			player.draw(g2d);

			ArrayList<Map.Entry<String, Entity>> entries = new ArrayList<>(entities.entrySet());
			entries.add(Map.entry("Scofield", player));
			entries.sort(Comparator.comparing(entry -> entry.getValue().worldY));
			entries.forEach(entry -> {
				entry.getValue().draw(g2d);
			});

			// Name tags
			if (mouseHandler.hovering) {
				TextBox.draw(g2d,
						mouseHandler.entityHovered.name,
						mouseHandler.xWindow + 20,
						mouseHandler.yWindow
				);
			}

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
			g2d.drawString("Col - " + player.getWorldCol(), x, y);
			y += 20;
			g2d.drawString("Row - " + player.getWorldRow(), x, y);
			y += 20;

			// Draw Time
			long drawEnd = System.nanoTime();
			long passedTime = drawEnd - drawStart;
			g2d.drawString("Draw Time: " + passedTime + " milliseconds", x, y);
		}

		g2d.dispose();
	}

}
