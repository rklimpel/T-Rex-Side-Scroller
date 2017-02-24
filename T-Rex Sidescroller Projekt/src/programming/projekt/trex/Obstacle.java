package programming.projekt.trex;

import java.awt.*;

/**
 * Created by ricoklimpel on 23.02.17.
 */

/**
 * Obstacle Description
 */
public class Obstacle extends GameObject {





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
