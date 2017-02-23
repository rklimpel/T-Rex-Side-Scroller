package programming.projekt.trex;

import com.sun.javafx.geom.Rectangle;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player {

    Rectangle rectanglePlayer;

    public Player (){
        rectanglePlayer = new Rectangle();
        rectanglePlayer.setBounds(20,20,40,100);
    }

    public void moveright() {
        rectanglePlayer.x = rectanglePlayer.x + 1;
    }
}
