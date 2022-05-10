package entity;

// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Display;
import main.KeyInput;

public class Hero extends Entity {

    Display display;
    KeyInput keyInput;

    public Hero(Display display, KeyInput keyInput) {

        this.display = display;
        this.keyInput = keyInput;

        setDefaultValues();
        getHeroImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getHeroImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/blue_knight/blue_knight_right_2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyInput.upPressed ||
            keyInput.downPressed ||
            keyInput.leftPressed ||
            keyInput.rightPressed) {

            if (keyInput.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyInput.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyInput.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if (keyInput.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D graphics2D) {

        // graphics2D.setColor(Color.white);
        // graphics2D.fillRect(x, y, display.tileSize, display.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }

        graphics2D.drawImage(image, x, y, display.tileSize, display.tileSize, null);

    }

}
