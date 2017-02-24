package programming.projekt.trex;

import java.awt.*;

/**
 * Created by ricoklimpel on 23.02.17.
 */

public class Obstacle {

    int width;
    int height;
    int x;
    int y;
    Color color;

    int paneWidth;
    int paneHeight;

    final int movePerTick = 1;

    final int defaultHeight = 100;
    final int defaultWidth = 40;

    public Obstacle (int paneWidth, int paneHeight){

        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;

        width = defaultWidth;
        height = defaultHeight;
        x = paneWidth - width;
        y = paneHeight - height;

    }

    public void moveLeft() {
        x -= movePerTick;

        //System.out.println("obstacle x: " + x);
        //System.out.println("obstacle y: " + y);
    }
}
