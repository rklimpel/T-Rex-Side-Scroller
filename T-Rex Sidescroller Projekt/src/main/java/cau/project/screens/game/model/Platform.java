package main.java.cau.project.screens.game.model;

import javafx.scene.paint.Color;
import main.java.cau.project.R;


public class Platform extends GameObject {

   GameModel gameModel;
   int type;

   public Platform(int type, GameModel gameModel, int paneWidth, int paneHeight, int yOffset) {

      this.type = type;
      this.gameModel = gameModel;
      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;

      this.height = R.platformHeight;
      this.width = R.platformWidth;

      this.y = paneHeight - height - yOffset;
      this.x = paneWidth;

      this.color = Color.BLACK;

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

}
