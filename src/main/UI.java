package main;

import object.ObjectDesk;
import object.ObjectToilet;
import ui.Hotbar;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UI {
	GamePanel gp;
	Graphics2D g2d;
	int commandNum = 0;
	public Hotbar hb;

	public UI(GamePanel gp) {
		this.gp = gp;
		hb = new Hotbar(gp);
	}

	public void draw(Graphics2D g2d) {
		this.g2d = g2d;

		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if (gp.gameState == gp.playState) {
			drawHotbar();
		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if (gp.gameState == gp.deskState) {
			((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).drawInventory(g2d);
			drawHotbar();
		}
		if (gp.gameState == gp.toiletState) {
			((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).drawInventory(g2d);
			drawHotbar();
		}
	}
	public void drawPauseScreen() {
		g2d.setColor(new Color(0, 0,0, 20));
		g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2d.setFont(new Font("Consolas", Font.PLAIN, 30));
		g2d.setColor(Color.white);
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int x = (gp.screenWidth - fm.stringWidth("PAUSE")) / 2;
		int y = (gp.screenHeight - fm.getHeight()) / 2 + fm.getAscent();
		g2d.drawString("PAUSE", x, y);
	}
	public void drawTitleScreen() {
		// Background Color
		Color cPink = new Color(111, 7, 104);
		GradientPaint gradient1 = new GradientPaint(0, 0, cPink, (float) gp.screenWidth, (float) gp.screenHeight, Color.black);
		g2d.setPaint(gradient1);
		g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Title text
		Font sSFont;
		try {
			sSFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/something_strange.ttf"));
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
		sSFont = sSFont.deriveFont(Font.BOLD, 70);
		g2d.setFont(sSFont);

		String text = "The Escapists 3";

		FontMetrics fm = g2d.getFontMetrics(sSFont);
		int x = (gp.screenWidth - fm.stringWidth(text)) / 2;
		int y = (gp.screenHeight - fm.getHeight()) / 2 - 20;

		g2d.setFont(sSFont);

		g2d.setColor(new Color(72, 72, 72));
		g2d.drawString(text, x + 3, y + 3);

		g2d.setColor(Color.white);
		g2d.drawString(text, x, y);

		// Menu Items
		sSFont = sSFont.deriveFont(Font.BOLD, 45);
		g2d.setFont(sSFont);
		g2d.setColor(Color.white);

		text = "NEW GAME";
		x = (gp.screenWidth - g2d.getFontMetrics(sSFont).stringWidth(text)) / 2;
		y += 2 * fm.getAscent() + gp.originalTileSize;
		g2d.drawString(text, x, y);
		if (commandNum == 0) {
			g2d.drawString(">", x - 35, y);
		}

		text = "LOAD GAME";
		x = (gp.screenWidth - g2d.getFontMetrics(sSFont).stringWidth(text)) / 2;
		y += g2d.getFontMetrics(sSFont).getAscent() + gp.originalTileSize;
		g2d.drawString(text, x, y);
		if (commandNum == 1) {
			g2d.drawString(">", x - 35, y);
		}

		text = "QUIT";
		x = (gp.screenWidth - g2d.getFontMetrics(sSFont).stringWidth(text)) / 2;
		y += g2d.getFontMetrics(sSFont).getAscent() + gp.originalTileSize;
		g2d.drawString(text, x, y);
		if (commandNum == 2) {
			g2d.drawString(">", x - 35, y);
		}
	}
	public void drawHotbar() {
		int frameX = gp.screenWidth - hb.image.getWidth() - 6 * gp.scale;
		int	frameY = (gp.screenHeight - hb.image.getHeight()) / 2;
		int frameWidth = hb.image.getWidth();
		int frameHeight = hb.image.getHeight();

		g2d.drawImage(hb.image, frameX, frameY, frameWidth, frameHeight, null);

		// Slots
		final int slotStartX = frameX + 9;
		final int slotStartY = frameY + 9;
		int slotX = slotStartX;
		int slotY = slotStartY;

		// Draw Items - temporary
		for (int i = 0; i < 5; i++) {
			if (hb.inventory[i] != null) {
				g2d.drawImage(hb.inventory[i].image, slotX, slotY, null);
			}
			slotY += gp.tileSize + 6 * gp.scale;
		}

		// Cursor
		int cursorX = slotStartX - 3;
		int cursorY = slotStartY + (gp.tileSize + 6 * gp.scale) * (hb.slotSelected - 1) - 3;
		int cursorWidth = gp.tileSize + 3;
		int cursorHeight = gp.tileSize + 3;

		// Draw Cursor
		if (hb.slotSelected > 0) {
			g2d.setColor(Color.white);
			g2d.setStroke(new BasicStroke(3f));
			g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		}
	}
}
