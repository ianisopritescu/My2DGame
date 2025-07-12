package main;

import object.*;

public class AssetSetter {
	private final GamePanel gp;
	public AssetSetter(final GamePanel gp) {
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
	}

	private void createObject(final SuperObject object,
							  final int x,
							  final int y) {

		gp.obj.add(object);
		int lastIndex = gp.obj.size() - 1;
		gp.obj.get(lastIndex).worldX = x * gp.tileSize;
		gp.obj.get(lastIndex).worldY = y * gp.tileSize;
	}

//	public void setEntity() {
//		createEntity(new Npc(gp), 61, 36);
//		createEntity(new Npc(gp), 50, 22);
//		createEntity(new Npc(gp), 45, 22);
//		createEntity(new Npc(gp), 50, 30);
//		createEntity(new Npc(gp), 40, 30);
//		createEntity(new Npc(gp), 45, 30);
//	}
//
//	void createEntity(Entity entity, int x, int y) {
//		gp.entities.add(entity);
//		int lastIndex = gp.entities.size() - 1;
//		gp.entities.get(lastIndex).worldX = x * gp.tileSize;
//		gp.entities.get(lastIndex).worldY = y * gp.tileSize;
//	}
}
