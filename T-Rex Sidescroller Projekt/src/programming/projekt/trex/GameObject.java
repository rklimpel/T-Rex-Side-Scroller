package programming.projekt.trex;


import javafx.scene.paint.Color;

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

    public Boolean checkCollision(GameObject clasher) {
        if (
            //Check Callers left side is inside clasher
            ((this.getX() >= clasher.getX()
                    && this.getX() <= clasher.getX() + clasher.getWidth())
            //Check Callers right side is inside clasher
            || (this.getX() + this.getWidth() >= clasher.getX()
                    && this.getX() + this.getWidth() <= clasher.getX() + clasher.getWidth()))
            //Check Callers Bottom is inside clasher
            &&((this.getY() + this.getHeight() >= clasher.getY()
                    && this.getY() + this.getHeight() <= clasher.getY() + clasher.getHeight())
            //Check Calles Top is inside clasher
            || (this.getY() >= clasher.getY()
                    && this.getY() <= clasher.getY() + clasher.getHeight()))) {

            /*System.out.println("this x: " + this.getX());
            System.out.println("this x + width: " + (this.getX() + this.getWidth()));

            System.out.println("clasher x: " + clasher.getX());
            System.out.println("clasher x + width: " + (clasher.getX() + clasher.getWidth()));

            System.out.println();

            System.out.println("this y: " + this.getX());
            System.out.println("this y + heigth: " + (this.getY() + this.getHeight()));

            System.out.println("clasher y: " + clasher.getY());
            System.out.println("clasher y + height: " + (clasher.getY() + clasher.getHeight()));*/

            return true;
        }
        return false;
    }

    public int getWidth() {
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
