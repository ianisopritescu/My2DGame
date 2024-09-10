package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ObjectDesk extends SuperObject{
	GamePanel gp;

	public final byte capacity = 20;
	public byte size = 0;
	public SuperObject[] inventory = new SuperObject[20];

	BufferedImage invImage;

	public final int maxSlotCol = 4;
	public final int maxSlotRow = 3;
	public int slotCol = 0;
	public int slotRow = 0;

	public ObjectDesk(GamePanel gp) {
		super(gp);
		this.gp = gp;
		collision = true;
		name = "Desk";
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/desk.png")));
			image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

			invImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/frames/desk_inventory.png")));
			invImage = uTool.scaledImage(invImage, gp.scale * invImage.getWidth(), invImage.getHeight() * gp.scale);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void drawInventory(Graphics2D g2d) {
		// General
		int arcWidth = 10;
		int arcHeight = 10;

		// Main Frame
		int frameX = (gp.screenWidth - invImage.getWidth()) / 2 ;
		int frameY = (gp.screenHeight - invImage.getHeight() ) / 2;
		int frameWidth = invImage.getWidth();
		int frameHeight = invImage.getHeight();

		g2d.drawImage(invImage, frameX, frameY, frameWidth, frameHeight, null);

		// Slots
		final int slotStartX = frameX + 20;
		final int slotStartY = frameY + 50;
		int slotX = slotStartX;
		int slotY = slotStartY;

		// Draw Items
		for (int i = 0; i < capacity; i++) {
			if (inventory[i] != null) {
				g2d.drawImage(inventory[i].image, slotX, slotY, null);
				System.out.println(i);
			}
			slotX += gp.tileSize + 4 * gp.scale;
			if (i % (maxSlotCol) == 0 && i != 0) {
				slotX = slotStartX;
				slotY += gp.tileSize + 4 * gp.scale;
			}
		}

		// Cursor
		int cursorX = slotStartX + (gp.tileSize + 4 * gp.scale) * slotCol;
		int cursorY = slotStartY + (gp.tileSize + 4 * gp.scale) * slotRow;
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		// Draw Cursor
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(3f));
		g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, arcWidth, arcHeight);
	}
	public void getItems() {
		int invIndex = slotCol + (slotRow) * 5 + 1;
		if (gp.ui.hb.slotSelected > 0) {
			if (gp.ui.hb.inventory[gp.ui.hb.slotSelected - 1] == null && inventory[invIndex] != null) {
				for (int i = 0; i < gp.ui.hb.capacity; i++) {
					if (gp.ui.hb.inventory[i] == null) {
						gp.ui.hb.inventory[i] = inventory[invIndex];
						inventory[invIndex] = null;
						gp.ui.hb.size ++;
						size --;
						break;
					}
				}
			} else if (gp.ui.hb.inventory[gp.ui.hb.slotSelected - 1] != null) {
				if (inventory[invIndex] == null) {
					size ++;
					gp.ui.hb.size --;
				}
				SuperObject object = gp.ui.hb.inventory[gp.ui.hb.slotSelected - 1];
				gp.ui.hb.inventory[gp.ui.hb.slotSelected - 1] = inventory[invIndex];
				inventory[invIndex] = object;
			}
		} else if (gp.ui.hb.size < gp.ui.hb.capacity && inventory[invIndex] != null) {
			for (int i = 0; i < gp.ui.hb.capacity; i++) {
				if (gp.ui.hb.inventory[i] == null) {
					gp.ui.hb.inventory[i] = inventory[invIndex];
					gp.ui.hb.size ++;
					size --;
					break;
				}
			}
			inventory[invIndex] = null;
		}
	}
}
