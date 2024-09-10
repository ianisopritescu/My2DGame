package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectToilet extends SuperObject{
	public ArrayList<SuperObject> inventory = new ArrayList<>(20);
	BufferedImage invImage;
	public final int maxSlotCol = 2	;
	public final int maxSlotRow = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	public ObjectToilet(GamePanel gp) {
		super(gp);
		collision = true;
		name = "toilet";
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/toilet.png")));
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
		for (int i = 0; i < inventory.size(); i++) {
			g2d.drawImage(inventory.get(i).image, slotX, slotY, null);
			slotX += gp.tileSize + 4 * gp.scale;
			if (i % maxSlotCol == 0 && i != 0) {
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
//	public void getItemFromHotbar() {
//		if (gp.ui.hb.slotSelected != 0) {
//			int invIndex = slotCol + slotRow * maxSlotCol;
//			SuperObject object = gp.ui.hb.inventory.get(gp.ui.hb.slotSelected - 1);
//			gp.ui.hb.inventory.remove(gp.ui.hb.slotSelected - 1);
//			inventory.add(invIndex, object);
//		}
//	}
}
