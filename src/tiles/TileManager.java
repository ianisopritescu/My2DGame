package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
	private final GamePanel gp;
	private final UtilityTool uTool;
	public Tile[] tile;
	public int[][] mapTileNum;

//	boolean drawPath = true;

	public TileManager(GamePanel gp){
		this.gp = gp;
		this.uTool = new UtilityTool();
		this.tile = new Tile[20];
		this.mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

		getTileImage();
		loadMap("resources/maps/map2.txt");
	}

	private void getTileImage() {
		setupTileImage(0, "grass", false);
		setupTileImage(1, "wall", true);
		setupTileImage(2, "water", true);
		setupTileImage(3, "blank", true);
		setupTileImage(4, "interior_ground", false);
		setupTileImage(5, "tree", true);
		setupTileImage(7, "horizontal_wall", true);
		setupTileImage(8, "vertical_wall", true);
		setupTileImage(9, "bottom_left_corner", true);
		setupTileImage(10, "bottom_right_corner", true);
		setupTileImage(11, "top_right_corner", true);
		setupTileImage(12, "top_left_corner", true);
		setupTileImage(13, "top_middle_corner", true);
		setupTileImage(14, "bottom_middle_corner", true);
		setupTileImage(15, "bars", true);
	}

	private void setupTileImage(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(
					Objects.requireNonNull(
							getClass().getResourceAsStream("/tiles/" + imagePath +".png")
					)
			);
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void loadMap(final String filepath) {
		try {
			InputStream is = new FileInputStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			for (int row = 0; row < gp.maxWorldRow; row ++) {
				String line = br.readLine();
				String[] numbers = line.split(" ");
				for (int col = 0; col < gp.maxWorldCol; col++) {
					mapTileNum[row][col] = Integer.parseInt(numbers[col]);
				}
			}

			br.close();
			is.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void draw(Graphics2D g2d) {
		for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
			for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
				int tileNum = mapTileNum[worldRow][worldCol];

				int worldX = worldCol * gp.tileSize;
				int worldY = worldRow * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;

				if (uTool.isObjectVisibleInScreen(worldX, worldY, gp)) {
					g2d.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
			}
		}

//		if (drawPath) {
//			g2d.setColor(new Color(255, 0, 0, 70));
//
//			for (int i = 0; i < gp.pathFinder.pathList.size(); i ++) {
//				int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
//				int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
//
//				int screenX = worldX - gp.player.worldX + gp.player.screenX;
//				int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//				g2d.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//			}
//		}
	}
}
