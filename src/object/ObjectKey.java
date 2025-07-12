package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectKey extends SuperObject{
	public ObjectKey(GamePanel gp, String name) {
		super(gp);
		this.name = name;
		prepImage(this.name);
	}

	@Override
	void prepImage(final String name) {
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/" + name + ".png")));
			image = UtilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
