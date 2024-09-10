package main;

import entity.Entity;

import java.awt.*;

public class CollisionChecker {

	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		double entityLeftWorldX = entity.worldX + entity.solidArea.x;
		double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		double entityTopWorldY = entity.worldY + entity.solidArea.y;
		double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = (int)(entityLeftWorldX / gp.tileSize);
		int entityRightCol = (int)(entityRightWorldX / gp.tileSize);
		int entityTopRow = (int)(entityTopWorldY / gp.tileSize);
		int entityBottomRow = (int)(entityBottomWorldY / gp.tileSize);

		int tileNum1;
		int tileNum2;

		switch (entity.direction) {
			case "up":
				entityTopRow = (int)((entityTopWorldY - entity.speed) / gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionTilesOn = true;
				}
				break;
			case "down":
				entityBottomRow = (int)((entityBottomWorldY + entity.speed) / gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionTilesOn = true;
				}
				break;
			case "left":
				entityLeftCol = (int)((entityLeftWorldX - entity.speed)/ gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionTilesOn = true;
				}
				break;
			case "right":
				entityRightCol = (int)((entityRightWorldX + entity.speed)/ gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionTilesOn = true;
				}
				break;
		}
	}

	public int checkObject(Entity entity, boolean isPlayer) {

		for (int i = 0; i < gp.obj.size(); i++) {

			if (gp.obj.get(i) != null) {

				// Get entity's solid area position
				int entityX = entity.worldX + entity.solidArea.x;
				int entityY = entity.worldY + entity.solidArea.y;
				Rectangle entityRect = new Rectangle(entityX, entityY, entity.solidArea.width, entity.solidArea.height);

				// Get object's solid area position
				int objectX = gp.obj.get(i).worldX + gp.obj.get(i).solidArea.x;
				int objectY = gp.obj.get(i).worldY + gp.obj.get(i).solidArea.y;
				Rectangle objectRect = new Rectangle(objectX, objectY, gp.obj.get(i).solidArea.width, gp.obj.get(i).solidArea.height);

				switch (entity.direction) {
					case "up":
						entityRect.y -= entity.speed;
						if (entityRect.intersects(objectRect)) {
							if (gp.obj.get(i).collision) {
								entity.collisionObjOn = true;
							}
							if (isPlayer) {
								return i;
							}
						}
						break;
					case "down":
						entityRect.y += entity.speed;
						if (entityRect.intersects(objectRect)) {
							if (gp.obj.get(i).collision) {
								entity.collisionObjOn = true;
							}
							if (isPlayer) {
								return i;
							}
						}
						break;
					case "left":
						entityRect.x -= entity.speed;
						if (entityRect.intersects(objectRect)) {
							if (gp.obj.get(i).collision) {
								entity.collisionObjOn = true;
							}
							if (isPlayer) {
								return i;
							}
						}
						break;
					case "right":
						entityRect.x += entity.speed;
						if (entityRect.intersects(objectRect)) {
							if (gp.obj.get(i).collision) {
								entity.collisionObjOn = true;
							}
							if (isPlayer) {
								return i;
							}
						}
						break;
				}

				gp.obj.get(i).isActive = true;
				entity.collisionObjOn = false;
			}
		}

		return 999;
	}

}
