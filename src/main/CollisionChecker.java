package main;

import entity.Entity;
import entity.Npc;
import entity.Player;
import object.SuperObject;

import java.awt.*;

public class CollisionChecker {
	private final GamePanel gp;
	public CollisionChecker(final GamePanel gp) {
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

		int tileNum1, tileNum2;

		switch (entity.direction) {
			case "up":
				entityTopRow = (int)((entityTopWorldY - entity.speed) / gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = (int)((entityBottomWorldY + entity.speed) / gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = (int)((entityLeftWorldX - entity.speed)/ gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = (int)((entityRightWorldX + entity.speed)/ gp.tileSize);
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
		}
	}

	public Point checkObject(Entity entity, boolean isPlayer) {

		double entityLeftWorldX = entity.worldX + entity.solidArea.x;
		double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		double entityTopWorldY = entity.worldY + entity.solidArea.y;
		double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = (int)(entityLeftWorldX / gp.tileSize);
		int entityRightCol = (int)(entityRightWorldX / gp.tileSize);
		int entityTopRow = (int)(entityTopWorldY / gp.tileSize);
		int entityBottomRow = (int)(entityBottomWorldY / gp.tileSize);

		Point point1 = null, point2 = null;

		switch (entity.direction) {
			case "up":
				entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize);
				point1 = new Point(entityLeftCol, entityTopRow);
				point2 = new Point(entityRightCol, entityTopRow);
				break;
			case "down":
				entityBottomRow = (int)((entityBottomWorldY + entity.speed) / gp.tileSize);
				point1 = new Point(entityLeftCol, entityBottomRow);
				point2 = new Point(entityRightCol, entityBottomRow);
				break;
			case "left":
				entityLeftCol = (int)((entityLeftWorldX - entity.speed)/ gp.tileSize);
				point1 = new Point(entityLeftCol, entityTopRow);
				point2 = new Point(entityLeftCol, entityBottomRow);
				break;
			case "right":
				entityRightCol = (int)((entityRightWorldX + entity.speed)/ gp.tileSize);
				point1 = new Point(entityRightCol, entityTopRow);
				point2 = new Point(entityRightCol, entityBottomRow);
				break;
		}

		boolean collision1 = gp.objMap.containsKey(point1) &&  gp.objMap.get(point1).collision;
		boolean collision2 = gp.objMap.containsKey(point2) &&  gp.objMap.get(point2).collision;

		if (collision1 || collision2) {
			entity.collisionOn = true;
			if (isPlayer) {
				if (gp.objMap.containsKey(point1))
					return point1;
				else
					return point2;
			}
		}

		// there is no object to interact with
		return null;
	}

	public int checkNpc(Player player) {

		for (int i = 0; i < gp.entities.size(); i++) {

			if (gp.entities.get(i) != null) {

				Npc npc = (Npc) gp.entities.get(i);

				// Get entity's solid area position
				int playerX = player.worldX + player.solidArea.x;
				int playerY = player.worldY + player.solidArea.y;
				Rectangle playerRect = new Rectangle(playerX, playerY, player.solidArea.width, player.solidArea.height);

				// Get object's solid area position
				int npcX = npc.worldX + npc.solidArea.x;
				int npcY = npc.worldY + npc.solidArea.y;
				Rectangle npcRect = new Rectangle(npcX, npcY, npc.solidArea.width, npc.solidArea.height);

				switch (player.direction) {
					case "up":
						playerRect.y -= player.speed;
						if (playerRect.intersects(npcRect)) {
							player.collisionOn = true;
							return i;
						}
						break;
					case "down":
						playerRect.y += player.speed;
						if (playerRect.intersects(npcRect)) {
							player.collisionOn = true;
							return i;
						}
						break;
					case "left":
						playerRect.x -= player.speed;
						if (playerRect.intersects(npcRect)) {
							player.collisionOn = true;
							return i;
						}
						break;
					case "right":
						playerRect.x += player.speed;
						if (playerRect.intersects(npcRect)) {
							player.collisionOn = true;
							return i;
						}
						break;
				}
			}
		}

		return 999;

	}

}
