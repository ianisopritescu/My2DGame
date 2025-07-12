package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	public static BufferedImage scaledImage(final BufferedImage origImg,
											final int newWidth,
											final int newHeight) {
		BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, origImg.getType());
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.drawImage(origImg, 0, 0, newWidth, newHeight, null);
		g2d.dispose();

		return scaledImage;
	}

	public static boolean isObjectVisibleInScreen(final int worldX,
												  final int worldY,
												  final GamePanel gp) {
		return worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
	}
}
