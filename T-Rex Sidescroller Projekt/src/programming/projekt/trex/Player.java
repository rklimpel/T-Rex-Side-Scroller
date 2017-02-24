package programming.projekt.trex;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player extends GameObject {

    final int defaultHeight = 80;
    final int defaultWidth = 40;

    public Player(int paneWidth, int paneHeight) {

        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth / 8;
        y = paneHeight - (defaultHeight);

    }

    public void jump() {
        
    }

}
