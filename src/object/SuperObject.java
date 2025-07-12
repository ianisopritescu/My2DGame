package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
	public GamePanel gp;

	public BufferedImage image;
	public String name;

	public boolean collision;

	public int worldX, worldY;
	public Rectangle solidArea;

	public boolean isActive;

	public SuperObject(GamePanel gp) {
		this.gp = gp;
		this.collision = false;
		this.isActive = true;
		this.solidArea = new Rectangle(0, 0, 16 * gp.scale, 16 * gp.scale);
	}

	public void draw(Graphics2D g2d) {
		if (UtilityTool.isObjectVisibleInScreen(worldX, worldY, gp)) {
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

	abstract void prepImage(final String name);
}
