package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
	public GamePanel gp;
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea;
	public boolean isActive = true;
	public UtilityTool uTool = new UtilityTool();

	public SuperObject(GamePanel gp) {
		this.gp = gp;
		solidArea = new Rectangle(0, 0, 16 * gp.scale, 16 * gp.scale);
	}

	public void draw(Graphics2D g2d, GamePanel gp) {
		double screenX = worldX - gp.player.worldX + gp.player.screenX;
		double screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			g2d.drawImage(image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
