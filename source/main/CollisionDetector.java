package main;

import entity.Entity;

public class CollisionDetector {

    Display display;

    public CollisionDetector(Display display) {

        this.display = display;
    }

    public void checkTile(Entity entity) {

        int entityLeftMapX = entity.mapX +
                entity.solidArea.x;

        int entityRightMapX = entity.mapX +
                entity.solidArea.x +
                entity.solidArea.width;

        int entityTopMapY = entity.mapY +
                entity.solidArea.y;

        int entityBottomMapY = entity.mapY +
                entity.solidArea.y +
                entity.solidArea.height;

        int entityLeftCol = entityLeftMapX / display.tileSize;
        int entityRightCol = entityRightMapX / display.tileSize;
        int entityTopRow = entityTopMapY / display.tileSize;
        int entityBottomRow = entityBottomMapY / display.tileSize;

        int tileNumber1;
        int tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopMapY - entity.speed) / display.tileSize;

                tileNumber1 = display.tileManager.mapTileNumber[entityLeftCol][entityTopRow];

                tileNumber2 = display.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                if (display.tileManager.tile[tileNumber1].collision ||
                        display.tileManager.tile[tileNumber2].collision) {

                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomMapY + entity.speed) / display.tileSize;

                tileNumber1 = display.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                tileNumber2 = display.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (display.tileManager.tile[tileNumber1].collision ||
                        display.tileManager.tile[tileNumber2].collision) {

                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftMapX - entity.speed) / display.tileSize;

                tileNumber1 = display.tileManager.mapTileNumber[entityLeftCol][entityTopRow];

                tileNumber2 = display.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];

                if (display.tileManager.tile[tileNumber1].collision ||
                        display.tileManager.tile[tileNumber2].collision) {

                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightMapX + entity.speed) / display.tileSize;

                tileNumber1 = display.tileManager.mapTileNumber[entityRightCol][entityTopRow];

                tileNumber2 = display.tileManager.mapTileNumber[entityRightCol][entityBottomRow];

                if (display.tileManager.tile[tileNumber1].collision ||
                        display.tileManager.tile[tileNumber2].collision) {

                    entity.collisionOn = true;
                }
                break;
        }
    }
}
