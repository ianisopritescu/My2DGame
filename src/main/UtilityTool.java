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
}
