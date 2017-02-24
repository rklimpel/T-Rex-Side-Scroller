package programming.projekt.trex;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player extends GameObject {
    final int defaultHeight = 70;
    final int defaultWidth = 20;

    public Player(int paneWidth, int paneHeight) {
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth * (1 / 3);
        y = paneHeight - (defaultHeight);


    }

    public void jump() {
        
    }

}
