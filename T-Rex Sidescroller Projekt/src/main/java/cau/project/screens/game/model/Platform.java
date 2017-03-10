package main.java.cau.project.screens.game.model;

import javafx.scene.paint.Color;
import main.java.cau.project.R;

/**
 * the class for the platforms, here the platform-type is set.
 * As well the checking if the player is running on a platform or not
 */
public class Platform extends GameObject {

    GameModel gameModel;
    int type;
    int yOffset;

    Boolean playerOnPlatform = false;

    /**
     * constructor for the platform, the shape and type are set here
     * @param type what kind of object(small,medium, long)
     * @param gameModel
     * @param paneWidth width of the current pane
     * @param paneHeight height of the current pane
     * @param yOffset
     */
    public Platform(int type, GameModel gameModel, int paneWidth, int paneHeight, int yOffset) {

        this.type = type;
        this.gameModel = gameModel;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.yOffset = yOffset;

        setMeasure(type);

        this.color = Color.BLACK;

    }

    /**
     * switching between the different types
     * the cases are the different types and there the parameters are
     * loaded from the R-class
     * @param type
     */
    public void setMeasure(int type) {

        switch (type) {
            case 200:
                defaultHeight = R.platformHeight0;
                defaultWidth = R.platformWidth0;
                break;
            case 201:
                defaultHeight = R.platformHeight1;
                defaultWidth = R.platformWidth1;
                break;
            case 202:
                defaultHeight = R.platformHeight2;
                defaultWidth = R.platformWidth2;
                break;
        }

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth;
        y = paneHeight - height - yOffset;

    }

    /**
     * method for checking if the player is on a platform first in a
     * horizontal way, so if the player is between the two ends of the platform is
     * then is the vertical position checked
     * @param object object to check for(player)
     * @return
     */
    public Boolean checkOnPlatform(GameObject object) {
        if (((object.getX() >= this.getX()
                && object.getX() <= this.getX() + this.getWidth())
                || (object.getX() + object.getWidth() >= this.getX()
                && object.getX() + object.getWidth() <= this.getX() + this.getWidth()))
                && (object.getY() + object.getHeight() >= this.getY() - 2
                && object.getY() + object.getHeight() <= this.getY() + this.getHeight() / 2)) {
            return true;
        }
        return false;
    }

    /**
     * this method checks if the player is above a platform.
     * Therefor the x-value is checked and if they-value is lower than the
     * one of the player
     * @param object object to check for(player)
     * @return
     */
   public Boolean checkOverPlatform(GameObject object){
      if((object.getX() >= this.getX()
              && object.getX() <= this.getX() + this.getWidth())
              || (object.getX() + object.getWidth() >= this.getX()
              && object.getX() + object.getWidth() <= this.getX() + this.getWidth())
              && object.getY() <= this.getY()){
         return true;
      }
      return false;
   }

}
