package ui;

import main.GamePanel;
import main.UtilityTool;
import object.ObjectKey;
import object.SuperObject;

import javax.imageio.ImageIO;
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
//	public ArrayList<SuperObject> inventory = new ArrayList<>(5);
	public final byte capacity = 5;
	public byte size = 0;
	public SuperObject[] inventory = new SuperObject[5];

	public Hotbar(GamePanel gp) {
		this.gp = gp;
		UtilityTool uTool = new UtilityTool();
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/frames/hotbar.png")));
			image = uTool.scaledImage(image, gp.scale * image.getWidth(), gp.scale * image.getHeight());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		inventory[0] = new ObjectKey(gp,"red_key");
		inventory[1] = new ObjectKey(gp,"yellow_key");
		inventory[2] = new ObjectKey(gp,"green_key");
		inventory[3] = new ObjectKey(gp,"purple_key");
		size += 4;
	}

	public void dropItem(int itemNum) {
		SuperObject item = inventory[itemNum];
		inventory[itemNum] = null;
		item.worldX = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize * gp.tileSize;
		item.worldY = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize * gp.tileSize;
		gp.obj.add(item);
	}
}
