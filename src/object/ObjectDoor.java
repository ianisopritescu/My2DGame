package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject {
	public ObjectDoor(final GamePanel gp,
					  final String name) {
		super(gp);
		this.collision = true;
		this.name = name;
		prepImage(name);
	}

	@Override
	void prepImage(final String name) {
		try {
			this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/" + name + ".png")));
			this.image = UtilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void draw(Graphics2D g2d, GamePanel gp) {
		if (UtilityTool.isObjectVisibleInScreen(worldX, worldY, gp) && isActive) {
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
