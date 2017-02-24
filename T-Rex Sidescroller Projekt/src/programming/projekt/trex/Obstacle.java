package programming.projekt.trex;

import java.awt.*;

/**
 * Created by ricoklimpel on 23.02.17.
 */

/**
 * Obstacle Description
 */
public class Obstacle extends GameObject {

    final int movePerTick = 1;

    final int defaultHeight = 80;
    final int defaultWidth = 20;

    public Obstacle (int paneWidth, int paneHeight){

        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth;
        y = paneHeight - height;

    }

    public void moveLeft() {

        x -= movePerTick;

        //System.out.println("obstacle x: " + x);
        //System.out.println("obstacle y: " + y);
    }

    public Boolean checkOutisde(){
        return (x+width)<0;
    }
}
