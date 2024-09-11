package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	public BufferedImage scaledImage(BufferedImage original, int newWidth, int newHeight) {

		BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, original.getType());
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(original, 0, 0, newWidth, newHeight, null);
		g2d.dispose();

		return scaledImage;
	}

	public boolean isObjectVisibleInScreen(int worldX, int worldY, GamePanel gp) {
		return worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
	}
}
