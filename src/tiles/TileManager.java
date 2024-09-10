package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;

	public TileManager(GamePanel gp){
		this.gp = gp;
		tile = new Tile[20];
		mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

		getTileImage();
		loadMap("resources/maps/map2.txt");
	}

	public void getTileImage() {
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

	public void setupTileImage(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath +".png")));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void loadMap(String filepath) {
		try {
			InputStream is = new FileInputStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[row][col] = num;
					col++;
				}

				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}

			br.close();
			is.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void draw(Graphics2D g2d) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldRow][worldCol];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			double screenX = worldX - gp.player.worldX + gp.player.screenX;
			double screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2d.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
