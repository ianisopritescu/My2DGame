package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
	final private int speedDefaultValue = 4;
	GamePanel gp;

	public int worldX, worldY;
	public int speed = speedDefaultValue;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public boolean collisionTilesOn;
	public boolean collisionObjOn;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public BufferedImage setupEntityImage (String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/" + imagePath +".png")));
			image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return image;
	}

	public void setAction() {}
	public void update() {}
}
