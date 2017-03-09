package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

public class Obstacle extends GameObject {

   int defaultHeight;
   int defaultWidth;
   int yOffset;
   int type;

   public Obstacle(int type, int paneWidth, int paneHeight, int yOffset) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;
      this.yOffset = yOffset;
      this.type = type;

      setMeasures(type);

   }

   /**
    * Set Height and Width
    *
    * @param type
    */
   private void setMeasures(int type){

      switch (type){
         case 0:
            defaultHeight = R.obstacleHeight0;
            defaultWidth = R.obstacleWidth0;
            break;
         case 1:
            defaultHeight = R.obstacleHeight1;
            defaultWidth = R.obstacleWidth1;
            break;
         case 2:
            defaultHeight = R.obstacleHeight2;
            defaultWidth = R.obstacleWidth2;
            break;
         case 3:
            defaultHeight = R.obstacleHeight3;
            defaultWidth = R.obstacleWidth3;
            break;
         case 4:
            defaultHeight = R.obstacleHeight4;
            defaultWidth = R.obstacleWidth4;
            break;
      }

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


   public int getType() {
      return type;
   }
}
