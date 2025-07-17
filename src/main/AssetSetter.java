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
		createObject(new ObjectDoor(gp, "door"), 15, 14);
		createObject(new ObjectDoor(gp, "door"), 20, 14);
		createObject(new ObjectDoor(gp, "door"), 25, 14);
		createObject(new ObjectDoor(gp, "door"), 30, 14);
		createObject(new ObjectDoor(gp, "door"), 26, 53);
		createObject(new ObjectDoor(gp, "door"), 26, 54);
		createObject(new ObjectDoor(gp, "door"), 31, 56);
		createObject(new ObjectDoor(gp, "door"), 26, 53);
		createObject(new ObjectDoor(gp, "door"), 29, 57);
		createObject(new ObjectDoor(gp, "door"), 29, 59);
		createObject(new ObjectDoor(gp, "door"), 50, 49);
		createObject(new ObjectDoor(gp, "door"), 50, 48);

		createObject(new ObjectDoor(gp, "purple_door"), 36, 17);
		createObject(new ObjectDoor(gp, "purple_door"), 36, 16);
		createObject(new ObjectDoor(gp, "purple_door"), 40, 57);
		createObject(new ObjectDoor(gp, "purple_door"), 41, 57);
		createObject(new ObjectDoor(gp, "purple_door"), 59, 35);
		createObject(new ObjectDoor(gp, "purple_door"), 59, 34);

		createObject(new ObjectDoor(gp, "yellow_door"), 49, 18);
		createObject(new ObjectDoor(gp, "yellow_door"), 50, 34);
		createObject(new ObjectDoor(gp, "yellow_door"), 19, 34);
		createObject(new ObjectDoor(gp, "yellow_door"), 32, 8);
		createObject(new ObjectDoor(gp, "yellow_door"), 56,  62);

		createObject(new ObjectDoor(gp, "green_door"), 11, 30);
		createObject(new ObjectDoor(gp, "green_door"), 7, 29);
		createObject(new ObjectDoor(gp, "green_door"), 13, 24);
		createObject(new ObjectDoor(gp, "green_door"), 16, 34);
		createObject(new ObjectDoor(gp, "green_door"), 56, 60);
		createObject(new ObjectDoor(gp, "green_door"), 60, 57);

		createObject(new ObjectDoor(gp, "red_door"), 9, 12);
		createObject(new ObjectDoor(gp, "red_door"), 2, 19);
		createObject(new ObjectDoor(gp, "red_door"), 5, 40);
		createObject(new ObjectDoor(gp, "red_door"), 10, 46);

		createObject(new ObjectDesk(gp), 41, 22);
		createObject(new ObjectDesk(gp), 46, 22);
		createObject(new ObjectDesk(gp), 51, 22);
		createObject(new ObjectDesk(gp), 41, 30);
		createObject(new ObjectDesk(gp), 46, 30);
		createObject(new ObjectDesk(gp), 51, 30);
		createObject(new ObjectDesk(gp), 33, 12);
		createObject(new ObjectDesk(gp), 28, 12);
		createObject(new ObjectDesk(gp), 23, 12);
		createObject(new ObjectDesk(gp), 18, 12);

		createObject(new ObjectToilet(gp), 41, 21);
		createObject(new ObjectToilet(gp), 46, 21);
		createObject(new ObjectToilet(gp), 51, 21);
		createObject(new ObjectToilet(gp), 41, 31);
		createObject(new ObjectToilet(gp), 46, 31);
		createObject(new ObjectToilet(gp), 51, 31);
		createObject(new ObjectToilet(gp), 33, 11);
		createObject(new ObjectToilet(gp), 28, 11);
		createObject(new ObjectToilet(gp), 23, 11);
		createObject(new ObjectToilet(gp), 18, 11);
	}

	private void createObject(final SuperObject newObject,
							  final int x,
							  final int y) {
		newObject.worldX = x * gp.tileSize;
		newObject.worldY = y * gp.tileSize;
		gp.objMap.put(new Point(x, y), newObject);
	}

	public void setEntities() {
		createEntity(new Npc(gp), "Pr. Lincoln", 40, 22);
		createEntity(new Npc(gp), "Pr. T-Bag",50, 22);
		createEntity(new Npc(gp), "Pr. Mahone",45, 22);
		createEntity(new Npc(gp), "Pr. Sucre",50, 30);
		createEntity(new Npc(gp), "Pr. Abruzzi", 40, 30);
	}

	void createEntity(Entity entity, String name, int x, int y) {
		entity.worldX = x * gp.tileSize;
		entity.worldY = y * gp.tileSize;
		entity.name = name;
		gp.entities.put(name, entity);
	}
}
