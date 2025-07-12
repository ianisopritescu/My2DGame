package entity;

import main.Consts;
import main.KeyHandler;
import main.GamePanel;
import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;

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
		worldX = 45 * gp.tileSize;
		worldY = 25 * gp.tileSize;
//		worldX = gp.worldWidth / 2 - gp.tileSize / 2;
//		worldY = gp.worldHeight / 2 - gp.tileSize / 2;
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
//		int npcIndex = gp.cChecker.checkNpc(this);
//		interactNpc(npcIndex);

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
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// Player interacted with a tile
			if (collisionOn)
				return;

			// Check object collision
			int lastColliding = objIndexColliding;
			objIndexColliding = gp.cChecker.checkObject(this, true);
			interactObject(objIndexColliding, lastColliding);

			// Player interacted with an object
			if (collisionOn)
				return;

			move();
			changeSprite();
		}
	}

	void interactObject(int currObjIndex, int lastObjIndex) {
		// last object is a door, and now player doesn't collide with the same door
		if (lastObjIndex != Consts.NO_OBJECT &&
				lastObjIndex != currObjIndex &&
				gp.obj.size() > lastObjIndex &&
				gp.obj.get(lastObjIndex).name.contains("door")) {
				gp.obj.get(lastObjIndex).isActive = true;
		}

		if (currObjIndex == Consts.NO_OBJECT) {
			return;
		}

		String objName = gp.obj.get(currObjIndex).name;
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
							gp.obj.get(currObjIndex).isActive = false;
							collisionOn = false;
							break;
						}
					}
				} else {
					gp.obj.get(currObjIndex).isActive = false;
					collisionOn = false;
				}
				break;

			case "green_key":
			case "red_key":
			case "yellow_key":
			case "purple_key":
				if (gp.keyHandler.spacePressed && gp.ui.hb.size != 5) {
					gp.obj.remove(currObjIndex);
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

//		System.out.println(lastObjIndex + " " + currObjIndex);

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
