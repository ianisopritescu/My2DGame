package entity;

import main.GamePanel;
import main.UtilityTool;
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
		speed = 2;
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

	public void draw(Graphics2D g2d) {
		if (UtilityTool.isObjectVisibleInScreen(worldX, worldY, gp)) {
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
	int randValReset = 120;
	public void setAction() {
		if (onPath) {
			int goalCol = 45;
			int goalRow = 25;
			searchPath(goalRow, goalCol);
		} else {
			actionLockCounter++;
			if (actionLockCounter == randValReset) {

				Random randNum = new Random();
				int i = randNum.nextInt(4) + 1;
				randValReset = randNum.nextInt(100) + 50;

				switch (i) {
					case 1:
						direction = "up";
						break;
					case 2:
						direction = "left";
						break;
					case 3:
						direction = "down";
						break;
					case 4:
						direction = "right";
						break;
				}

				actionLockCounter = 0;
			}
		}
	}

	public void searchPath(int goalRow, int goalCol) {
		int startCol = (worldX + solidArea.x) / gp.tileSize;
		int startRow = (worldY + solidArea.y) / gp.tileSize;

		gp.pathFinder.setNodes(startRow, startCol, goalRow, goalCol);

		if (gp.pathFinder.search()) {
			// Next worldX & worldY
			int nextX = gp.pathFinder.pathList.getFirst().col * gp.tileSize;
			int nextY = gp.pathFinder.pathList.getFirst().row * gp.tileSize;

			// Entity's solidArea position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;

			gp.pathFinder.pathList.removeFirst();

			if ((enTopY > nextY && enLeftX > nextX && enRightX + 20 < nextX + gp.tileSize)) {
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX > nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if (enTopY > nextY && enBottomY < nextY + gp.tileSize) {
				if (enLeftX > nextX) {
					direction = "left";
				} else if (enLeftX < nextX) {
					direction = "right";
				}
			} else if (enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn) {
					direction = "left";
				}
			} else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn) {
					direction = "right";
				}
			} else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn) {
					direction = "left";
				}
			} else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn) {
					direction = "right";
				}
			}

			// if reaches the goal, stop the search
			int nextCol = gp.pathFinder.pathList.getFirst().col;
			int nextRow = gp.pathFinder.pathList.getFirst().row;
			if (nextCol == goalCol && nextRow == goalRow) {
				onPath = false;
			}
		}
	}

	void checkCollision() {
		// check tile collision
		collisionOn = false;
		gp.cChecker.checkTile(this);

		// Npc interacted with a tile
		if (collisionOn)
			return;

		// check object collision
		Point lastColliding = objPointColliding;
		objPointColliding = gp.cChecker.checkObject(this, true);
		interactObject(objPointColliding, lastColliding);
	}

	void interactObject(Point currObjPoint, Point lastObjPoint) {
		// check if last object was a door, and now is not colliding with it, to close it
		if (lastObjPoint != null &&
				!lastObjPoint.equals(currObjPoint) &&
				gp.objMap.get(lastObjPoint).name.contains("door")) {
			gp.objMap.get(lastObjPoint).isActive = true;
		}

		if (currObjPoint == null) {
			return;
		}

		String objName = gp.objMap.get(currObjPoint).name;
		switch (objName) {
			case "door":
				gp.objMap.get(currObjPoint).isActive = false;
				collisionOn = false;
				break;
			case "Desk":
				break;
		}
	}

	public void update() {
		setAction();
		checkCollision();
		move();
		changeSprite();
	}
}
