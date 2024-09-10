package main;

import entity.Entity;
import entity.Npc;
import object.*;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		createObject(new ObjectDoor(gp, "door"), 38, 24);
		createObject(new ObjectDoor(gp, "door"), 43, 24);
		createObject(new ObjectDoor(gp, "door"), 48, 24);
		createObject(new ObjectDoor(gp, "door"), 38, 28);
		createObject(new ObjectDoor(gp, "door"), 43, 28);
		createObject(new ObjectDoor(gp, "door"), 48, 28);
		createObject(new ObjectDoor(gp, "door"), 56, 19);
		createObject(new ObjectDoor(gp, "door"), 59, 19);
		createObject(new ObjectDoor(gp, "door"), 56, 23);

		createObject(new ObjectDoor(gp, "purple_door"), 37, 17);
		createObject(new ObjectDoor(gp, "purple_door"), 37, 16);

		createObject(new ObjectDoor(gp, "yellow_door"), 49, 18);
		createObject(new ObjectDoor(gp, "yellow_door"), 50, 34);

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

//		createObject(new ObjectKey(gp, "yellow_key"), 32, 22);
//		createObject(new ObjectKey(gp, "red_key"), 38, 22);
//		createObject(new ObjectKey(gp, "green_key"), 40, 28);

	}

	void createObject (SuperObject object, int x, int y) {

		gp.obj.add(object);
		int lastIndex = gp.obj.size() - 1;
		gp.obj.get(lastIndex).worldX = x * gp.tileSize;
		gp.obj.get(lastIndex).worldY = y * gp.tileSize;

//		if (object.getClass() == ObjectDoor.class) {
//
//		}
	}

	public void setEntity() {
		createEntity(new Npc(gp), 43, 26);
		//
	}

	void createEntity(Entity entity, int x, int y) {
		gp.entities.add(entity);
		int lastIndex = gp.entities.size() - 1;
		gp.entities.get(lastIndex).worldX = x * gp.tileSize;
		gp.entities.get(lastIndex).worldY = y * gp.tileSize;
	}
}
