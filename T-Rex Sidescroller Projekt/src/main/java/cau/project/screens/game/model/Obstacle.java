package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

/**
 * class for he obstacle, here the type of the obstacle is set
 */
public class Obstacle extends GameObject {

   int defaultHeight;
   int defaultWidth;
   int yOffset;
   int type;

   /**
    * constructor for an obstacle where size ad type are set
    * @param type type of the obstacle
    * @param paneWidth width of the current pane
    * @param paneHeight height of the current pane
    * @param yOffset
    */
   public Obstacle(int type, int paneWidth, int paneHeight, int yOffset) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;
      this.yOffset = yOffset;
      this.type = type;

      setMeasures(type);

   }

   /**
    * setting the type of the obstacle, therefor the values from the lvl-data are
    * decoded and its value loaded from the R-class
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
