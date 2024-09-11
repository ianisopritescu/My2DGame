package entity;

import main.KeyHandler;
import main.GamePanel;
import main.UtilityTool;
import object.ObjectKey;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public int objIndexColliding = 999;

	public Player (GamePanel gp, KeyHandler keyH) {
		super(gp);

		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;

		solidArea = new Rectangle();
		solidArea.x = 4 * gp.scale;
		solidArea.y = 8 * gp.scale;
		solidArea.width = 8 * gp.scale;
		solidArea.height = 7 * gp.scale;

		setDefaultValues();
		getPlayerImage();
	}

	void setDefaultValues() {
		worldX = gp.worldWidth / 2 - gp.tileSize / 2;
		worldY = gp.worldHeight / 2 - gp.tileSize / 2;
		direction = "down";
	}

	void getPlayerImage() {
		up1 = setupEntityImage("up1");
		up2 = setupEntityImage("up2");
		down1 = setupEntityImage("down1");
		down2 = setupEntityImage("down2");
		left1 = setupEntityImage("left1");
		left2 = setupEntityImage("left2");
		right1 = setupEntityImage("right1");
		right2 = setupEntityImage("right2");
	}

	public void update() {
		// Check object collision
		objIndexColliding = gp.cChecker.checkObject(this, true);
		interactObject(objIndexColliding);

		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				direction = "up";
			}
			if (keyH.downPressed) {
				direction = "down";
			}
			if (keyH.leftPressed) {
				direction = "left";
			}
			if (keyH.rightPressed) {
				direction = "right";
			}

			// Check tile collision
			collisionTilesOn = false;
			gp.cChecker.checkTile(this);

			// If collision with tiles or objects is false, player can move
			if (canMove(objIndexColliding)) {
				switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}

			spriteCounter ++;
			if (spriteCounter > 9) {
				if (spriteNum == 1)
					spriteNum = 2;
				else if (spriteNum == 2)
					spriteNum = 1;
				spriteCounter = 0;
			}
		}
	}

	void interactObject(int objIndex) {
		if (objIndex == 999) {
			return;
		}

		String objName = gp.obj.get(objIndex).name;
		switch (objName) {
			case "yellow_door":
			case "red_door":
			case "green_door":
			case "purple_door":
			case "door":
				String key = "";
				if (objName.contains("_")) {
					key = objName.substring(0, objName.indexOf("door"));
				}
				key = key + "key";
				if (!objName.equals("door")) {
					for (int i = 0; i < gp.ui.hb.size; i++) {
						if (gp.ui.hb.inventory[i].name.contains(key)) {
//							if (gp.obj.get(objIndex).isActive) {
//								gp.ui.hb.inventory.remove(i);
//							}
							gp.obj.get(objIndex).isActive = false;
							break;
						}
					}
				} else {
					gp.obj.get(objIndex).isActive = false;
				}
				break;

			case "green_key":
			case "red_key":
			case "yellow_key":
			case "purple_key":
				if (gp.keyHandler.spacePressed && gp.ui.hb.size != 5) {
					gp.obj.set(objIndex, null);
					for (int i = 0; i < gp.ui.hb.capacity; i++) {
						if (gp.ui.hb.inventory[i] == null) {
							gp.ui.hb.inventory[i] = new ObjectKey(gp, objName);
							break;
						}
					}
				}
				break;
			case "Desk":
				break;
		}
	}

	public void draw(Graphics2D g2d) {

		BufferedImage image = null;
		switch (direction) {
			case "up":
				if (spriteNum == 1)
					image = up1;
				else if (spriteNum == 2)
					image = up2;
				break;
			case "down":
				if (spriteNum == 1)
					image = down1;
				else if (spriteNum == 2)
					image = down2;
				break;
			case "left":
				if (spriteNum == 1)
					image = left1;
				else if (spriteNum == 2)
					image = left2;
				break;
			case "right":
				if (spriteNum == 1)
					image = right1;
				else if (spriteNum == 2)
					image = right2;
				break;
		}

		g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
