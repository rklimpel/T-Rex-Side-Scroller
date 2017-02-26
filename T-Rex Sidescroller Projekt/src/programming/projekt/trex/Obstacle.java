package programming.projekt.trex;

import java.awt.*;

/**
 * Created by ricoklimpel on 23.02.17.
 */

/**
 * Obstacle Description
 */
public class Obstacle extends GameObject {

    final int defaultHeight = R.obstacleHeight;
    final int defaultWidth = R.obstacleWidth;

    public Obstacle (int paneWidth, int paneHeight){

        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth;
        y = paneHeight - height;

    }

    public void moveLeft() {

        //Move Obstacle one px left
        x -= 1;

        //System.out.println("obstacle x: " + x);
        //System.out.println("obstacle y: " + y);
    }

    public Boolean checkOutisde(){
        return (x+width)<0;
    }
}
