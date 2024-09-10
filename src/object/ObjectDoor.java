package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject {
	public ObjectDoor(GamePanel gp, String name) {
		super(gp);
		collision = true;
		this.name = name;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/" + name + ".png")));
			image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
