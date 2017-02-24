package programming.projekt.trex;

import java.awt.*;

/**
 * Created by janek on 24.02.2017.
 */
public class GameObject {

    int width;
    int height;
    int x;
    int y;

    Color color;

    int paneWidth;
    int paneHeight;

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
