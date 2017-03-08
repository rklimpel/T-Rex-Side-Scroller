package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

import java.util.Random;

/**
 * class which creates the backgroundobjects
 */
public class BackgroundObject {
    Random rand= new Random();

   private double x;
   private double y;
   int paneWidth;
   int paneHeight;
   int width;
   int height;

    /**
     * constructor for the backgroundobjcet which calculates the position
     * the size of the object is given in R-class
     *
     * @param paneWidth current pane width
     * @param paneHeight current pane height
     */
    public BackgroundObject(int paneWidth, int paneHeight) {
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
        this.setX(paneWidth);
        this.setY(randInt(paneHeight/2, paneHeight- R.groundLvL-50));
        this.setHeight(R.backgroundObjectHeight);
        this.setWidth(R.backgroundObjectWidth);

    }

    /**
     * generates a random number between the two min and max values
     * @param min bottom of the amount of numbers to chose from
     * @param max top of the amount of numbers to chose from
     *
     * @return the randomly choosen number
     */
    private int randInt(int min, int max) {
        //setting limits for the case min is smaller zero and max is smaller zero
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
