package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Display;

public class TileManager {

    Display display;
    Tile[] tile;

    int mapTileNumber[][];

    public TileManager(Display display) {

        this.display = display;

        tile = new Tile[10];
        mapTileNumber = new int[display.maxScreenCol][display.maxScreenRow];

        getTileImage();

        loadMap("/maps/map_template.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].bufferedImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].bufferedImage = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].bufferedImage = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath) {

        try {

            InputStream inputStream = getClass().getResourceAsStream(mapFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < display.maxScreenCol &&
                    row < display.maxScreenRow) {

                String line = bufferedReader.readLine();

                while (col < display.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;
                }

                if (col == display.maxScreenCol) {

                    col = 0;
                    row++;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < display.maxScreenCol &&
                row < display.maxScreenRow) {

            int tileNumber = mapTileNumber[col][row];

            graphics2D.drawImage(tile[tileNumber].bufferedImage, x, y, display.tileSize, display.tileSize, null);

            col++;
            x += display.tileSize;

            if (col == display.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += display.tileSize;
            }
        }

    }
}
