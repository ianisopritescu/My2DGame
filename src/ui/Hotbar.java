package ui;

import main.GamePanel;
import main.UtilityTool;
import object.ObjectDoor;
import object.ObjectKey;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Hotbar {
	GamePanel gp;
	public BufferedImage image;

	public final int maxSlotCol = 4;
	public final int maxSlotRow = 3;
	public int slotSelected = 0;
	public final byte capacity = 5;
	public byte size = 0;
	public SuperObject[] inventory = new SuperObject[5];

	public Hotbar(GamePanel gp) {
		this.gp = gp;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/frames/hotbar.png")));
			image = UtilityTool.scaledImage(image, gp.scale * image.getWidth(), gp.scale * image.getHeight());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		inventory[0] = new ObjectKey(gp,"red_key");
		inventory[1] = new ObjectKey(gp,"yellow_key");
		inventory[2] = new ObjectKey(gp,"green_key");
//		inventory[3] = new ObjectKey(gp,"purple_key");
		inventory[3] = new ObjectDoor(gp, "door");
		size += 4;
	}

	public void dropItem(int itemNum) {
		SuperObject item = inventory[itemNum];
		inventory[itemNum] = null;

		int x = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int y = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

		item.worldX = x * gp.tileSize;
		item.worldY = y * gp.tileSize;

		gp.objMap.put(new Point(x, y), item);
	}
}
