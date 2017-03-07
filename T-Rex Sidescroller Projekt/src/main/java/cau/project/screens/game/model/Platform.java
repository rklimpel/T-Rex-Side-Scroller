package main.java.cau.project.screens.game.model;

import javafx.scene.paint.Color;
import main.java.cau.project.R;


public class Platform extends GameObject {

    GameModel gameModel;
    int type;
    int yOffset;

    Boolean playerOnPlatform = false;

    public Platform(int type, GameModel gameModel, int paneWidth, int paneHeight, int yOffset) {

        this.type = type;
        this.gameModel = gameModel;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.yOffset = yOffset;
        System.out.println("test");
        setMeasure(type);

        this.color = Color.BLACK;

    }

    public void setMeasure(int type) {
        System.out.println("Plattform type: " + type);


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
