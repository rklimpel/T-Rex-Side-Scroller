package main.java.cau.project.screens.game.model;

import javafx.scene.paint.Color;
import main.java.cau.project.R;

/**
 * class for the powerup which defines this kind of gameobject
 */
public class Powerup extends GameObject{

   int powerupType;

   /**
    * constructor for the powerup
    *
    * @param type type of the powerup
    * @param paneWidth width of the current pane
    * @param paneHeight height of the current pane
    * @param yOffset y-position of the powerup above the ground-level
    */
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

}
