package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

public class Obstacle extends GameObject {

   int defaultHeight;
   int defaultWidth;

   public Obstacle(int type, int paneWidth, int paneHeight, int yOffset) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;

      setMeasures(type);

      width = defaultWidth;
      height = defaultHeight;

      x = paneWidth;
      y = paneHeight - height - yOffset;

   }

   public Obstacle(int type, int paneWidth, int paneHeight) {
      this(type, paneWidth, paneHeight, 0);
   }

   public Obstacle(int type) {
      this(type, 0, 0);
   }

   /**
    * Set Height and Width
    *
    * @param type
    */
   private void setMeasures(int type){
      switch (type){
         case 0:
            defaultHeight = R.obstacleHeight;
            defaultWidth = R.obstacleWidth;
            break;
      }
   }

}
