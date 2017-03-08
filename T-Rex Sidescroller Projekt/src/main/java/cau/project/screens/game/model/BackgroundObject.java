package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

import java.util.Random;

/**
 * Created by janek on 08.03.2017.
 */
public class BackgroundObject {
    Random rand= new Random();

   private double x;
   private double y;
   int paneWidth;
   int paneHeight;
   int width;
   int height;

    public BackgroundObject(int paneWidth, int paneHeight) {
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
        this.setX(paneWidth);
        this.setY(randInt(paneHeight/2, paneHeight- R.groundLvL-50));
        this.setHeight(R.backgroundObjectHeight);
        this.setWidth(R.backgroundObjectWidth);

    }
    private int randInt(int min, int max) {

        if (min < 0) {
            min = 1;
        }
        if (max < 0) {
            max = 2;
        }

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
