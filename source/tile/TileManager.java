package tile;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Display;

public class TileManager {

    Display display;
    public Tile[] tile;

    public int mapTileNumber[][];

    public TileManager(Display display) {

        this.display = display;

        tile = new Tile[10];
        mapTileNumber = new int[display.maxMapCol][display.maxMapRow];

        getTileImage();

        loadMap("/maps/worldmap_template.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].bufferedImage = 
            ImageIO.read(getClass().getResourceAsStream("/tiles/road.png"));
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

            while (col < display.maxMapCol &&
                    row < display.maxMapRow) {

                String line = bufferedReader.readLine();

                while (col < display.maxMapCol) {

                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;
                }

                if (col == display.maxMapCol) {

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

        int mapCol = 0;
        int mapRow = 0;

        while (mapCol < display.maxMapCol &&
                mapRow < display.maxMapRow) {

            int tileNumber = mapTileNumber[mapCol][mapRow];

            int mapX = mapCol * display.tileSize;
            int mapY = mapRow * display.tileSize;
            int screenX = mapX - display.hero.mapX + display.hero.screenX;
            int screenY = mapY - display.hero.mapY + display.hero.screenY;

            if (mapX + display.tileSize> display.hero.mapX - display.hero.screenX &&
                    mapX - display.tileSize < display.hero.mapX + display.hero.screenX &&
                    mapY + display.tileSize > display.hero.mapY - display.hero.screenY &&
                    mapY - display.tileSize < display.hero.mapY + display.hero.screenY) {

                graphics2D.drawImage(
                        tile[tileNumber].bufferedImage,
                        screenX,
                        screenY,
                        display.tileSize,
                        display.tileSize, null);
            }

            mapCol++;

            if (mapCol == display.maxMapCol) {
                mapCol = 0;
                mapRow++;
            }
        }
    }
}
