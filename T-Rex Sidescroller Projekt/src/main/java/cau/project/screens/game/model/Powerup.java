package main.java.cau.project.screens.game.model;

import javafx.scene.paint.Color;
import main.java.cau.project.R;

/**
 * Created by Rico on 06.03.2017.
 */
public class Powerup extends GameObject{

   int powerupType;

   Powerup(int type, int paneWidth, int paneHeight, int yOffset) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;

      this.width = R.powerupWidth;
      this.height = R.powerupHeight;

      this.y = paneHeight - height - yOffset;
      this.x = paneWidth;

      this.color = Color.GREENYELLOW;

      this.powerupType = type;
   }

   public void moveLeft() {
      //Move Obstacle one px left
      x -= 1;
   }

   public Boolean checkOutisde() {
      return (x + width) < 0;
   }
}
