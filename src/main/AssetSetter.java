package main;

import entity.Entity;
import entity.Npc;
import object.*;

import java.awt.*;

public class AssetSetter {
	private final GamePanel gp;
	public AssetSetter(final GamePanel gp) {
		this.gp = gp;
	}

	public void setObjects() {
		createObject(new ObjectDoor(gp, "door"), 38, 24);
		createObject(new ObjectDoor(gp, "door"), 43, 24);
		createObject(new ObjectDoor(gp, "door"), 48, 24);
		createObject(new ObjectDoor(gp, "door"), 38, 28);
		createObject(new ObjectDoor(gp, "door"), 43, 28);
		createObject(new ObjectDoor(gp, "door"), 48, 28);
		createObject(new ObjectDoor(gp, "door"), 56, 19);
		createObject(new ObjectDoor(gp, "door"), 59, 19);
		createObject(new ObjectDoor(gp, "door"), 56, 23);
		createObject(new ObjectDoor(gp, "door"), 24, 36);
		createObject(new ObjectDoor(gp, "door"), 26, 39);

		createObject(new ObjectDoor(gp, "purple_door"), 37, 17);
		createObject(new ObjectDoor(gp, "purple_door"), 37, 16);

		createObject(new ObjectDoor(gp, "yellow_door"), 49, 18);
		createObject(new ObjectDoor(gp, "yellow_door"), 50, 34);
		createObject(new ObjectDoor(gp, "yellow_door"), 19, 34);

		createObject(new ObjectDesk(gp), 41, 22);
		createObject(new ObjectDesk(gp), 46, 22);
		createObject(new ObjectDesk(gp), 51, 22);
		createObject(new ObjectDesk(gp), 41, 30);
		createObject(new ObjectDesk(gp), 46, 30);
		createObject(new ObjectDesk(gp), 51, 30);

		createObject(new ObjectToilet(gp), 41, 21);
		createObject(new ObjectToilet(gp), 46, 21);
		createObject(new ObjectToilet(gp), 51, 21);
		createObject(new ObjectToilet(gp), 41, 31);
		createObject(new ObjectToilet(gp), 46, 31);
		createObject(new ObjectToilet(gp), 51, 31);
	}

	private void createObject(final SuperObject newObject,
							  final int x,
							  final int y) {
		newObject.worldX = x * gp.tileSize;
		newObject.worldY = y * gp.tileSize;
		gp.objMap.put(new Point(x, y), newObject);
	}

	public void setEntities() {
//		createEntity(new Npc(gp), "Lincoln", 61, 36);
//		createEntity(new Npc(gp), "T-Bag",50, 22);
//		createEntity(new Npc(gp), "Mahone",45, 22);
		createEntity(new Npc(gp), "Sucre",50, 30);
		createEntity(new Npc(gp), "Abruzzi", 40, 30);
	}

	void createEntity(Entity entity, String name, int x, int y) {
		entity.worldX = x * gp.tileSize;
		entity.worldY = y * gp.tileSize;
		gp.entities.put(name, entity);
	}
}
