package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
	final private int speedDefaultValue = 4;
	GamePanel gp;

	private int worldCol, worldRow;
	public int worldX, worldY;
	public int speed = speedDefaultValue;

	public String name;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public boolean collisionOn;

	public boolean onPath = false;

	public Point objPointColliding = null;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public BufferedImage setupEntityImage (String imagePath) {
		BufferedImage image;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/" + imagePath +".png")));
			image = UtilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return image;
	}


	public void move() {
		// If collision with tiles or objects is false, player can move
		if (!collisionOn) {
			switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
			}
		}
	}

	public void changeSprite() {
		spriteCounter ++;
		if (spriteCounter > 9) {
			if (spriteNum == 1)
				spriteNum = 2;
			else if (spriteNum == 2)
				spriteNum = 1;
			spriteCounter = 0;
		}
	}

	public int getWorldCol() {
		return (worldX + solidArea.x) / gp.tileSize;
	}

	public int getWorldRow() {
		return (worldY + solidArea.y) / gp.tileSize;
	}

	void interactObject(Point currObjPoint, Point lastObjPoint) {}
	public void draw(Graphics2D g2d) {}
	public void setAction() {}
	public void update() {}
}
