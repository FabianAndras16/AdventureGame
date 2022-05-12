package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Hero;
import tile.TileManager;

public class Display extends JPanel implements Runnable {

    // SCREEN SETTINGS

    // Tile = originalTileSize x originalTileSize pixels
    final int originalTileSize = 16;
    final int scale = 4;

    // Real tileSize = scale*originalTileSize x scale*originalTileSize pixels
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);

    KeyInput keyInput = new KeyInput();

    Thread gameThread;

    Hero hero = new Hero(this, keyInput);

    public Display() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    // CALLED WHEN startGameThread();
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;

        // FPS counter
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            // System.out.println("Game loop is running.");

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            // FPS counter
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {

                // UPDATE
                update();

                // DRAW
                repaint();

                delta--;
                // FPS counter
                drawCount++;
            }

            // FPS counter
            if (timer >= 1000000000) {

                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        hero.update();
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);

        hero.draw(graphics2D);

        graphics2D.dispose();
    }
}