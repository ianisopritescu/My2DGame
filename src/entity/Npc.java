package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Npc extends Entity{

	public Npc(GamePanel gp) {
		super(gp);

		setDefaultValues();
		getNpcImage();
	}

	void setDefaultValues() {
		direction = "down";
	}

	void getNpcImage() {
		up1 = setupEntityImage("up1");
		up2 = setupEntityImage("up2");
		down1 = setupEntityImage("down1");
		down2 = setupEntityImage("down2");
		left1 = setupEntityImage("left1");
		left2 = setupEntityImage("left2");
		right1 = setupEntityImage("right1");
		right2 = setupEntityImage("right2");
	}

	public void draw(Graphics2D g2d, GamePanel gp) {
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			g2d.drawImage(down1, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
