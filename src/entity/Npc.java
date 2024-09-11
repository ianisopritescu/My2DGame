package entity;

import main.GamePanel;
import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Npc extends Entity{

	public Npc(GamePanel gp) {
		super(gp);

		solidArea = new Rectangle();
		solidArea.x = 4 * gp.scale;
		solidArea.y = 8 * gp.scale;
		solidArea.width = 8 * gp.scale;
		solidArea.height = 7 * gp.scale;

		setDefaultValues();
		getNpcImage();
	}

	void setDefaultValues() {
		speed = 1;
		direction = "down";
	}

	void getNpcImage() {
		up1 = setupEntityImage("up1");
		up2 = setupEntityImage("up2");
		down1 = setupEntityImage("down1");
		down2 = setupEntityImage("down2");
		left1 = setupEntityImage("left1");
		left2 = setupEntityImage("left2");
		right1 = setupEntityImage("right1");
		right2 = setupEntityImage("right2");
	}

	public void draw(Graphics2D g2d, GamePanel gp) {
		if (uTool.isObjectVisibleInScreen(worldX, worldY, gp)) {

			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

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


	int actionLockCounter = 0;
	public void setAction() {
		actionLockCounter++;

		if (actionLockCounter == 120) {

			Random randNum = new Random();
			int i = randNum.nextInt(100) + 1;

			if (i < 25) {
				direction = "up";
			} else if (i < 50) {
				direction = "left";
			} else if (i < 75) {
				direction = "down";
			} else if (i < 100) {
				direction = "right";
			}

			actionLockCounter = 0;
		}

	}


	public int objIndexColliding = 999; // interacts with nothing (999)
	public void update () {
		setAction();

		int lastColliding = objIndexColliding;
		objIndexColliding = gp.cChecker.checkObject(this, true);
		interactObject(objIndexColliding, lastColliding);

		collisionTilesOn = false;
		gp.cChecker.checkTile(this);

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

	public void interactObject(int currObjIndex, int lastObjIndex) {
		System.out.println("Index of Npc: " + currObjIndex);
		if (currObjIndex == 999) {
			if (lastObjIndex != 999 && gp.obj.get(lastObjIndex).name.equals("door")) {
				gp.obj.get(lastObjIndex).isActive = true;
			}
			return;
		}
		if (gp.obj.get(currObjIndex).name.equals("door")) {
			gp.obj.get(currObjIndex).isActive = false;
		}
	}
}
